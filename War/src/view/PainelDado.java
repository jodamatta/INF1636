package view;

import java.awt.*;
import javax.swing.*;

public class PainelDado extends JPanel {

    private int valorDado;
    private Color corJogador;

    public PainelDado(int valorDado, Color corJogador) {
        this.valorDado = valorDado;
        this.corJogador = corJogador;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha o fundo colorido do jogador
        g.setColor(corJogador);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Carrega a imagem do dado
        Image imagemDado = Toolkit.getDefaultToolkit().getImage("War\\src\\view\\images\\dado_ataque_1.png");

        // Desenha a imagem do dado
        g.drawImage(imagemDado, 10, 10, this);

        // Desenha o número do dado
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString(Integer.toString(valorDado), 30, 60);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100); // Define o tamanho do painel
    }
}
