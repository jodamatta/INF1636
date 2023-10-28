package view;

import model.GameModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<String> coresDisponiveis;
    private int max_jogadores;
    private ItemListener corBoxListener;


    public JanelaInicial() {
    	gameModel = GameModel.getInstancia();
        initUI();
    }
    

    private void initUI() {
    	coresDisponiveis = new ArrayList<>();
    	coresDisponiveis.add("--Selecione Cor--");
    	coresDisponiveis.addAll(Arrays.asList(gameModel.getCores()));
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
        
        corBoxListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if(!e.getItem().equals("--Selecione Cor--")) {
                    	coresDisponiveis.remove(e.getItem());
                    	//refreshComboBoxes();
                    }
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    if(!e.getItem().equals("--Selecione Cor--")) {
                    	coresDisponiveis.add((String) e.getItem());
                        refreshComboBoxes();
                    }
                }
            }
        };
        
        for (int i = 0; i < max_jogadores; i++) {
            campoNomeJogadores[i] = new JTextField();
            campoNomeJogadores[i].setBounds(100, 100 + (i * 60), 200, 30);
            frame.add(campoNomeJogadores[i]);

            coresComboBox[i] = new JComboBox<>(coresDisponiveis.toArray(new String[0]));
            coresComboBox[i].setSelectedItem("--Selecione Cor--");

            coresComboBox[i].setBounds(320, 100 + (i * 60), 150, 30);
            
            // cria um ItemListener pro ComboBox
            coresComboBox[i].addItemListener(corBoxListener);

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
    
    private void refreshComboBoxes() {
        for (JComboBox<String> box : coresComboBox) {
            box.removeItemListener(corBoxListener);  // Remove listener
            
            Object selected = box.getSelectedItem();
            System.out.println(selected);
            
            box.removeAllItems();
            box.addItem("--Selecione Cor--");
            for (String color : coresDisponiveis) {
                box.addItem(color);
            }
            
            if (coresDisponiveis.contains(selected) || "--Selecione Cor--".equals(selected)) {
                box.setSelectedItem(selected);
            }/* else {
                box.setSelectedItem("--Selecione Cor--");
            }*/
            
            box.addItemListener(corBoxListener);  // Add listener back
        }
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
