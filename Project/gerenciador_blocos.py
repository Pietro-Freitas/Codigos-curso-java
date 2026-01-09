import cv2
import numpy as np

class GerenciadorBlocos:
    def __init__(self):
        # Lista para armazenar as coordenadas de cada bloco [x, y]
        self.blocos = []
        self.cor_bloco = (255, 200, 0) # Azul/Ciano (formato BGR)
        self.tamanho_bloco = 40
        
        # Variáveis para o sistema de "Arrastar" (Pan)
        self.offset_x = 0
        self.offset_y = 0
        self.ref_x = 0
        self.ref_y = 0
        self.arrastando = False

    def adicionar_bloco(self, x, y):
        # Ajusta a coordenada baseada no offset atual para fixar o bloco no "mundo"
        x_real = x - self.offset_x
        y_real = y - self.offset_y
        
        # Alinha à grade (snap to grid) para ficar certinho igual no vídeo
        snap_x = round(x_real / self.tamanho_bloco) * self.tamanho_bloco
        snap_y = round(y_real / self.tamanho_bloco) * self.tamanho_bloco
        
        # Evita duplicatas na mesma posição
        if [snap_x, snap_y] not in self.blocos:
            self.blocos.append([snap_x, snap_y])

    def iniciar_arrasto(self, x, y):
        # Salva onde a mão "pegou" a tela
        if not self.arrastando:
            self.ref_x = x - self.offset_x
            self.ref_y = y - self.offset_y
            self.arrastando = True

    def atualizar_arrasto(self, x, y):
        if self.arrastando:
            self.offset_x = x - self.ref_x
            self.offset_y = y - self.ref_y

    def parar_arrasto(self):
        self.arrastando = False

    def desenhar_blocos(self, img):
        # Cria uma camada transparente para desenhar
        overlay = img.copy()
        
        for bloco in self.blocos:
            x, y = bloco
            # Aplica o offset atual (movimento da câmera)
            x_final = x + self.offset_x
            y_final = y + self.offset_y
            
            # Desenha o quadrado principal
            cv2.rectangle(overlay, 
                          (x_final, y_final), 
                          (x_final + self.tamanho_bloco, y_final + self.tamanho_bloco), 
                          self.cor_bloco, -1)
            
            # Desenha uma borda para parecer "tijolinho"
            cv2.rectangle(overlay, 
                          (x_final, y_final), 
                          (x_final + self.tamanho_bloco, y_final + self.tamanho_bloco), 
                          (200, 200, 200), 1)

        # Mescla a camada desenhada com a original (transparência)
        alpha = 0.6
        img_nova = cv2.addWeighted(overlay, alpha, img, 1 - alpha, 0)
        return img_nova