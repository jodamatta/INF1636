package view;

import java.awt.*;

public class JogoDados {

    public static void main(String[] args) {
        Frame frame = new Frame("Jogo de Dados");
        frame.setSize(150, 150);

        PainelDado dado = new PainelDado(3, Color.RED, TipoDado.ATAQUE); // Dado de defesa, por exemplo
        
        frame.add(dado);

        frame.setVisible(true);
    }

    enum TipoDado {
        ATAQUE,
        DEFESA
    }

    static class PainelDado extends Panel {

        private int valorDado;
        private Color corJogador;
        private TipoDado tipoDado;

        public PainelDado(int valorDado, Color corJogador, TipoDado tipoDado) {
            this.valorDado = valorDado;
            this.corJogador = corJogador;
            this.tipoDado = tipoDado;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            Graphics2D g2d = (Graphics2D) g;

            // Desenha o fundo branco
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            // Aumenta a espessura da moldura
            g2d.setStroke(new BasicStroke(20));

            // Desenha a moldura colorida
            g2d.setColor(corJogador);
            g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

            // Carrega a imagem do dado
            String caminhoImagem = (tipoDado == TipoDado.ATAQUE) ? "War\\src\\view\\images\\dado_ataque_" + valorDado + ".png" : "War\\src\\view\\images\\dado_defesa_" + valorDado + ".png";
            Image imagemDado = Toolkit.getDefaultToolkit().getImage(caminhoImagem);

            // Desenha a imagem do dado
            int tamanhoImagem = 80;
            g2d.drawImage(imagemDado, (getWidth() - tamanhoImagem) / 2, (getHeight() - tamanhoImagem) / 2, tamanhoImagem, tamanhoImagem, this);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(100, 100); // Define o tamanho do painel
        }
    }
}
