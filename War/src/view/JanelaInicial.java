package view;

import model.GameModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

class JanelaInicial {
	private Frame frame;
    private Button btnNovoJogo;
    private Button btnContinuaJogo;
    private Button btnContinuar;
    private Button btnIniciaJogo;
    private JTextField[] campoNomeJogadores;
    private JComboBox<String>[] coresComboBox;
    private JComboBox<Integer> numJogadoresComboBox;
    private Label lblNumJogadoresPrompt;
    private GameModel gameModel;
    private int max_jogadores;
    private List<String> availableColors = new ArrayList<>(Arrays.asList(gameModel.getCores()));


    public JanelaInicial() {
    	gameModel = GameModel.getInstancia();
        initUI();
    }
    

    private void initUI() {
    	max_jogadores = gameModel.getMaxJogadores();
        frame = new Frame("Risk Game Start Window");
        frame.setSize(1200, 700); 
        frame.setLayout(null); 
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        // botao novo jogo
        btnNovoJogo = new Button("Novo Jogo");
        btnNovoJogo.setBounds(390, 620, 200, 50);
        btnNovoJogo.addActionListener(e -> {
        	btnNovoJogo.setVisible(false);
        	btnContinuaJogo.setVisible(false);
        	numJogadoresComboBox.setVisible(true);
        	lblNumJogadoresPrompt.setVisible(true);
        	btnContinuar.setVisible(true);
        });
        frame.add(btnNovoJogo);
        
        // botao continua jogo
        btnContinuaJogo = new Button("Continuar Jogo");
        btnContinuaJogo.setBounds(610, 620, 200, 50);
        btnContinuaJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // continua jogo
                System.out.println("Continue Game Button Pressed!");
            }
        });
        frame.add(btnContinuaJogo);
        
        // botao continuar
        btnContinuar = new Button("Continuar");
        btnContinuar.setBounds(630, 500, 100, 30);
        btnContinuar.addActionListener(e -> {
        	lblNumJogadoresPrompt.setVisible(false);
        	numJogadoresComboBox.setVisible(false);
        	btnContinuar.setVisible(false);
        	mostraSetupJogadores((int) numJogadoresComboBox.getSelectedItem());
        });
        frame.add(btnContinuar);
        btnContinuar.setVisible(false);
        
        //label perguntando qtd de jogadores
        lblNumJogadoresPrompt = new Label("Quantos jogadores?");
        lblNumJogadoresPrompt.setBounds(470, 460, 150, 30);
        frame.add(lblNumJogadoresPrompt);
        lblNumJogadoresPrompt.setVisible(false);
        
        // qtd de jogadores
        Integer[] playerOptions = {2, 3, 4, 5, 6};  
        numJogadoresComboBox = new JComboBox<>(playerOptions);
        numJogadoresComboBox.setBounds(500, 500, 100, 30);
        frame.add(numJogadoresComboBox);
        numJogadoresComboBox.setVisible(false);
        campoNomeJogadores = new JTextField[max_jogadores];
        coresComboBox = new JComboBox[max_jogadores];
        
        
        for (int i = 0; i < max_jogadores; i++) {
            campoNomeJogadores[i] = new JTextField();
            campoNomeJogadores[i].setBounds(100, 100 + (i * 60), 200, 30);
            frame.add(campoNomeJogadores[i]);

            coresComboBox[i] = new JComboBox<>(gameModel.getCores());
            coresComboBox[i].setBounds(320, 100 + (i * 60), 150, 30);
            
            // cria um ItemListener pro ComboBox
            coresComboBox[i].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                    	// se selecionamos uma cor, temos que remove-la das opcoes
                        for (JComboBox<String> box : coresComboBox) {
                            if (box != e.getSource()) {  
                                box.removeItem(e.getItem());
                            }
                        }
                    } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    	// se tiramos uma cor, devolvemos ela pras opcoes
                        for (JComboBox<String> box : coresComboBox) {
                            if (box != e.getSource() && box.getItemCount() < gameModel.getCores().length) { 
                                box.addItem((String) e.getItem());
                            }
                        }
                    }
                }
            });

            frame.add(coresComboBox[i]);

            // começam escondidos
            campoNomeJogadores[i].setVisible(false);
            coresComboBox[i].setVisible(false);
        }

        
        btnIniciaJogo = new Button("Start Game");
        btnIniciaJogo.setBounds(550, 500, 200, 50);
        btnIniciaJogo.addActionListener(e -> startGame());
        btnIniciaJogo.setVisible(false); 
        frame.add(btnIniciaJogo);
        
        frame.setVisible(true);
    }
    
    private void mostraSetupJogadores(int numJogadores) {
        btnNovoJogo.setVisible(false);
        btnContinuaJogo.setVisible(false);

        for (int i = 0; i < numJogadores; i++) {
        	campoNomeJogadores[i].setVisible(true);
        	coresComboBox[i].setVisible(true);
        }
        btnIniciaJogo.setVisible(true);
    }
    
    private void startGame() {
    	gameModel.printPlayers();
    }
    
    public static void main(String[] args) {
        new JanelaInicial();
    }
    
}
