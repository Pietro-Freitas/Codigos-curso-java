import cv2
import mediapipe as mp
from mediapipe.python.solutions import hands as mp_hands
from mediapipe.python.solutions import drawing_utils as mp_draw
import math

class DetectorMaos:
    def __init__(self, mode=False, max_hands=2, detection_con=0.5, track_con=0.5):
        self.mode = mode
        self.max_hands = max_hands
        self.detection_con = detection_con
        self.track_con = track_con

        # Inicializa o módulo de mãos do MediaPipe
        self.mp_hands = mp.solutions.hands
        self.hands = self.mp_hands.Hands(
            static_image_mode=self.mode,
            max_num_hands=self.max_hands,
            min_detection_confidence=self.detection_con,
            min_tracking_confidence=self.track_con
        )
        self.mp_draw = mp.solutions.drawing_utils

    def encontrar_maos(self, img, draw=True):
        img_rgb = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        self.results = self.hands.process(img_rgb)

        if self.results.multi_hand_landmarks:
            for hand_lms in self.results.multi_hand_landmarks:
                if draw:
                    self.mp_draw.draw_landmarks(img, hand_lms, self.mp_hands.HAND_CONNECTIONS)
        return img

    def encontrar_posicao(self, img, hand_no=0):
        lm_list = []
        x_min, x_max = float('inf'), float('-inf')
        y_min, y_max = float('inf'), float('-inf')
        
        if self.results.multi_hand_landmarks:
            # Verifica se a mão solicitada existe (ex: mão 0 ou mão 1)
            if hand_no < len(self.results.multi_hand_landmarks):
                my_hand = self.results.multi_hand_landmarks[hand_no]
                
                # Identifica se é esquerda ou direita (label)
                # O MediaPipe inverte (espelho), então 'Left' geralmente é a direita na tela
                hand_label = self.results.multi_handedness[hand_no].classification[0].label
                
                for id, lm in enumerate(my_hand.landmark):
                    h, w, c = img.shape
                    cx, cy = int(lm.x * w), int(lm.y * h)
                    lm_list.append([id, cx, cy])
                    
                    # Para calcular a bounding box (caixa ao redor da mão)
                    if cx < x_min: x_min = cx
                    if cx > x_max: x_max = cx
                    if cy < y_min: y_min = cy
                    if cy > y_max: y_max = cy

                bbox = (x_min, y_min, x_max, y_max)
                return lm_list, bbox, hand_label
                
        return lm_list, None, None

    def dedos_levantados(self, lm_list):
        # Ids das pontas dos dedos: [Polegar, Indicador, Meio, Anelar, Mindinho]
        tip_ids = [4, 8, 12, 16, 20]
        dedos = []

        # Polegar (lógica diferente pois ele se move para o lado)
        # Nota: Dependendo da mão (esq/dir) o sinal muda. Simplificado aqui:
        if lm_list[tip_ids[0]][1] > lm_list[tip_ids[0] - 1][1]:
            dedos.append(1)
        else:
            dedos.append(0)

        # Outros 4 dedos
        for id in range(1, 5):
            if lm_list[tip_ids[id]][2] < lm_list[tip_ids[id] - 2][2]:
                dedos.append(1)
            else:
                dedos.append(0)
        return dedos

    def calcular_distancia(self, p1, p2, img, lm_list, draw=True):
        x1, y1 = lm_list[p1][1], lm_list[p1][2]
        x2, y2 = lm_list[p2][1], lm_list[p2][2]
        cx, cy = (x1 + x2) // 2, (y1 + y2) // 2

        length = math.hypot(x2 - x1, y2 - y1)

        if draw:
            cv2.line(img, (x1, y1), (x2, y2), (255, 0, 255), 3)
            cv2.circle(img, (cx, cy), 10, (255, 0, 255), cv2.FILLED)

        return length, img, [x1, y1, x2, y2, cx, cy]