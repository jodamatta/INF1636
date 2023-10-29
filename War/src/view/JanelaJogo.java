package view;

import model.GameModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.*;

public class JanelaJogo extends Frame{
    private Frame frame;
    private GameModel gameModel;
    private static JanelaInicial janelaInicial;
    private Button btnTerminaRodada;
    private Button btnTrocaCartas;
    private Button btnMover; 
    private Button btnSalvarJogo;
    private List<Button> btnTerritorios;
    private BufferedImage imagemDeFundo;
    Map<String,  int[]> dictTerritorioPosicao = new HashMap<>();
    Map<String, Color> dictStrCor = new HashMap<>();


    public JanelaJogo() {
        gameModel = GameModel.getInstancia();
        gameModel.hardCodedSetup();
        try {
            imagemDeFundo = ImageIO.read(new File("War\\src\\view\\images\\tabuleiro_certo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        };
        frame = new Frame("Janela de Jogo do war");
        setSize(1200, 700);
        setLayout(null); // Desabilita o layout manager para posicionamento manual dos botões
        initButtons();   // Inicializa os botões
        initTerritorios();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    private void initButtons() {
        btnTerminaRodada = new Button("Terminar Rodada");
        btnTerminaRodada.setBounds(1070, 640, 120, 30); // Define posição e tamanho do botão
        add(btnTerminaRodada);
        //890
        btnTrocaCartas = new Button("Trocar Cartas");
        btnTrocaCartas.setBounds(945, 640, 120, 30);
        add(btnTrocaCartas);

        btnMover = new Button("Mover Exército");
        btnMover.setBounds(820, 640, 120, 30);
        add(btnMover);

        btnSalvarJogo = new Button("Salvar Jogo");
        btnSalvarJogo.setBounds(10, 30, 120, 30);
        add(btnSalvarJogo);
    }

    {
        dictTerritorioPosicao.put("ARGENTINA", new int[]{310, 510});
        dictTerritorioPosicao.put("BRASIL", new int[]{318, 404});
        dictTerritorioPosicao.put("PERU", new int[]{260, 435});
        dictTerritorioPosicao.put("VENEZUELA", new int[]{218, 389});
        dictTerritorioPosicao.put("MEXICO", new int[]{155, 318});
        dictTerritorioPosicao.put("CALIFORNIA", new int[]{120, 230});
        dictTerritorioPosicao.put("TEXAS", new int[]{177, 250});
        dictTerritorioPosicao.put("NOVAYORK", new int[]{245, 232});
        dictTerritorioPosicao.put("VANCOUVER", new int[]{165,168});
        dictTerritorioPosicao.put("ALASCA", new int[]{100, 121});
        dictTerritorioPosicao.put("CALGARY", new int[]{190, 125});
        dictTerritorioPosicao.put("GROELANDIA", new int[]{365,92});
        dictTerritorioPosicao.put("QUEBEC", new int[]{330,162});
        dictTerritorioPosicao.put("REINO_UNIDO", new int[]{540, 154});
        dictTerritorioPosicao.put("ESPANHA", new int[]{515, 254});
        dictTerritorioPosicao.put("FRANCA", new int[]{560, 220});
        dictTerritorioPosicao.put("ITALIA", new int[]{620, 205});
        dictTerritorioPosicao.put("SUECIA", new int[]{615, 126});
        dictTerritorioPosicao.put("POLONIA", new int[]{668, 168});
        dictTerritorioPosicao.put("ROMENIA", new int[]{668, 229});
        dictTerritorioPosicao.put("UCRANIA", new int[]{700, 201});
        dictTerritorioPosicao.put("EGITO", new int[]{652, 347});
        dictTerritorioPosicao.put("ARGELIA", new int[]{525, 333});
        dictTerritorioPosicao.put("NIGERIA", new int[]{605, 390});
        dictTerritorioPosicao.put("ANGOLA", new int[]{640, 460});
        dictTerritorioPosicao.put("AFRICA_DO_SUL", new int[]{660, 510});
        dictTerritorioPosicao.put("SOMALIA", new int[]{720, 435});
        dictTerritorioPosicao.put("ARABIA_SAUDITA", new int[]{780, 355});
        dictTerritorioPosicao.put("JORDANIA", new int[]{723, 313});
        dictTerritorioPosicao.put("SIRIA", new int[]{760, 252});
        dictTerritorioPosicao.put("IRAQUE", new int[]{785, 299});
        dictTerritorioPosicao.put("IRA", new int[]{830, 300});
        dictTerritorioPosicao.put("PAQUISTAO", new int[]{870, 284});
        dictTerritorioPosicao.put("TURQUIA", new int[]{830, 215});
        dictTerritorioPosicao.put("LETONIA", new int[]{758, 168});
        dictTerritorioPosicao.put("ESTONIA", new int[]{785, 123});
        dictTerritorioPosicao.put("RUSSIA", new int[]{903,133});
        dictTerritorioPosicao.put("SIBERIA", new int[]{1038, 119});
        dictTerritorioPosicao.put("CAZAQUISTAO", new int[]{960, 188});
        dictTerritorioPosicao.put("MONGOLIA", new int[]{1020, 220});
        dictTerritorioPosicao.put("JAPAO", new int[]{1107, 233});
        dictTerritorioPosicao.put("COREIA_DO_NORTE", new int[]{997, 271});
        dictTerritorioPosicao.put("COREIA_DO_SUL", new int[]{1040, 293});
        dictTerritorioPosicao.put("CHINA", new int[]{920, 256});
        dictTerritorioPosicao.put("INDIA", new int[]{927, 340});
        dictTerritorioPosicao.put("BANGLADESH", new int[]{986,347});
        dictTerritorioPosicao.put("TAILANDIA", new int[]{1036,340});
        dictTerritorioPosicao.put("INDONESIA", new int[]{1046, 450});
        dictTerritorioPosicao.put("NOVAZELANDIA", new int[]{1088, 583});
        dictTerritorioPosicao.put("AUSTRALIA", new int[]{1011, 550});
        dictTerritorioPosicao.put("PERTH", new int[]{940, 540});
    }

    {
        dictStrCor.put("Vermelho", Color.RED);
        dictStrCor.put("Azul", Color.BLUE);
        dictStrCor.put("Verde", Color.GREEN);
        dictStrCor.put("Amarelo", Color.YELLOW);
        dictStrCor.put("Rosa", Color.PINK);
        dictStrCor.put("Laranja", Color.ORANGE);
        dictStrCor.put("Preto", Color.BLACK);
        dictStrCor.put("Branco", Color.WHITE);
    }
    
    private void initTerritorios() {
        btnTerritorios = new ArrayList<>();
        Set<String> nomesTerritorios = dictTerritorioPosicao.keySet();
        
        for (String nome : nomesTerritorios) {
            String numExercitos = String.valueOf(gameModel.getNumSoldados(nome));
            //String numExercitos = "1";
            int[] coordenadas = dictTerritorioPosicao.get(nome);
            Button btnTerritorio = new Button(numExercitos);
            btnTerritorio.setBounds(coordenadas[0], coordenadas[1], 30, 30);
            
            String corJogador = gameModel.getTerritorioCor(nome);
            btnTerritorio.setBackground(dictStrCor.get(corJogador)); 
            btnTerritorio.setForeground(Color.WHITE);
            
            btnTerritorios.add(btnTerritorio);
            add(btnTerritorio);
        }
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
