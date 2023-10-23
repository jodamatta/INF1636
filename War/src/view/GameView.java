package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class GameView {
    private Frame frame;
    private Canvas canvas;

    public GameView() {
        initFrame();
        initCanvas();
    }

    private void initFrame() {
        frame = new Frame("Jogo de War");
        frame.setSize(1200,700); 
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void initCanvas() {
        canvas = new Canvas();
        canvas.setSize(1200, 700);
        canvas.setBackground(Color.WHITE);
        frame.add(canvas);

        canvas.createBufferStrategy(2); 
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // eventos de click de mouse
            }
        });
    }

    public void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();

        // limpa a tela
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // adicionar codigo de desenho aqui

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        GameView view = new GameView();

        while (true) {
            view.render();
            try {
                Thread.sleep(16);  // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
