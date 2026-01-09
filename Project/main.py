import cv2
import time
from detector_maos import DetectorMaos
from gerenciador_blocos import GerenciadorBlocos

def main():
    # Configuração da Webcam
    cap = cv2.VideoCapture(0)
    cap.set(3, 1280) # Largura
    cap.set(4, 720)  # Altura

    detector = DetectorMaos(max_hands=2, detection_con=0.8)
    mundo = GerenciadorBlocos()

    print("Sistema Iniciado.")
    print("Mão Direita (Pinça): Desenhar")
    print("Mão Esquerda (Punho Fechado): Arrastar a tela")

    while True:
        sucesso, img = cap.read()
        if not sucesso:
            break
        
        # Inverter a imagem para agir como espelho (mais intuitivo)
        img = cv2.flip(img, 1)

        # 1. Detectar Mãos
        img = detector.encontrar_maos(img)
        
        # Precisamos checar quantas mãos tem e processar
        # O MediaPipe não garante ordem (Esq/Dir), então checamos o "Label"
        if detector.results.multi_hand_landmarks:
            for i in range(len(detector.results.multi_hand_landmarks)):
                lm_list, _, label = detector.encontrar_posicao(img, hand_no=i)
                
                # --- LÓGICA DA MÃO DIREITA (DESENHAR) ---
                # No modo espelho (flip), 'Right' no mediapipe aparece como esquerda, 
                # mas vamos assumir que o usuário usa a mão direita física.
                # Ajuste 'Left' ou 'Right' conforme sua câmera.
                if label == 'Left': # Na câmera espelhada, mão direita geralmente é 'Left'
                    # Verificar gesto de pinça (Dedo indicador [8] e Polegar [4])
                    comprimento, img, info = detector.calcular_distancia(4, 8, img, lm_list)
                    
                    # Se a pinça estiver fechada (distância < 30px)
                    if comprimento < 40:
                        # Pega a coordenada do meio da pinça
                        cx, cy = info[4], info[5]
                        # Cria o bloco
                        mundo.adicionar_bloco(cx, cy)
                        cv2.circle(img, (cx, cy), 15, (0, 255, 0), cv2.FILLED)

                # --- LÓGICA DA MÃO ESQUERDA (ARRASTAR) ---
                if label == 'Right': # Mão esquerda física
                    # Verificar se a mão está fechada (todos os dedos abaixados)
                    dedos = detector.dedos_levantados(lm_list)
                    
                    # Ponto de referência: Pulso (0) ou centro da palma (9)
                    cx, cy = lm_list[9][1], lm_list[9][2]
                    
                    # Se todos os dedos estiverem abaixados (Punho fechado) ou apenas polegar
                    if dedos[1] == 0 and dedos[2] == 0 and dedos[3] == 0 and dedos[4] == 0:
                        cv2.circle(img, (cx, cy), 20, (0, 0, 255), cv2.FILLED)
                        mundo.iniciar_arrasto(cx, cy)
                        mundo.atualizar_arrasto(cx, cy)
                    else:
                        mundo.parar_arrasto()

        # 2. Desenhar os blocos na tela
        img = mundo.desenhar_blocos(img)

        # Mostrar FPS e Imagem
        cv2.imshow("Construtor VOXEL Gestual", img)
        
        # Sair com a tecla 'q'
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

    cap.release()
    cv2.destroyAllWindows()

if __name__ == "__main__":
    main()