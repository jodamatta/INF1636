package view;

import model.GameModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


public class JanelaJogo extends Frame{
    private Frame frame;
    private GameModel gameModel;
    private static JanelaInicial janelaInicial;
    private Button btnTerminaRodada;
    private Button btnTrocaCartas;
    private Button btnMover; 
    private Button btnSalvarJogo;
    private BufferedImage imagemDeFundo;

    public JanelaJogo() {
        try {
            imagemDeFundo = ImageIO.read(new File("War\\src\\view\\images\\war_tabuleiro_mapa copy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSize(imagemDeFundo.getWidth(), imagemDeFundo.getHeight());
        setLayout(null); // Desabilita o layout manager para posicionamento manual dos botões
        initButtons();   // Inicializa os botões
        setVisible(true);
    }

    private void initButtons() {
        btnTerminaRodada = new Button("Terminar Rodada");
        btnTerminaRodada.setBounds(890, 700, 120, 30); // Define posição e tamanho do botão
        add(btnTerminaRodada);

        btnTrocaCartas = new Button("Trocar Cartas");
        btnTrocaCartas.setBounds(765, 700, 120, 30);
        add(btnTrocaCartas);

        btnMover = new Button("Mover Exército");
        btnMover.setBounds(640, 700, 120, 30);
        add(btnMover);

        btnSalvarJogo = new Button("Salvar Jogo");
        btnSalvarJogo.setBounds(10, 30, 120, 30);
        add(btnSalvarJogo);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imagemDeFundo, 0, 0, this);
    }

    public static void main(String[] args) {
        new JanelaJogo();
    }
}
