package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class JanelaJogo extends Frame{
    private GameView gameView;
    private Frame frame;
    private Button btnTerminaRodada;
    private Button btnTrocaCartas;
    private Button btnMover; 
    private Button btnSalvarJogo;
    private Button btnCorJogador;
    private Button btnAtaque;
    private Button btnConfirmaDados;
    private List<Button> btnTerritorios;
    private BufferedImage imagemDeFundo;
    Map<String,  int[]> dictTerritorioPosicao = new HashMap<>();
    Map<String, Color> dictStrCor = new HashMap<>();
    Map<String, Button> dictTerritorioBtn = new HashMap<>();
    Map<Color, Color> dictCorFundoLetra = new HashMap<>();
    private List<BufferedImage> dadosAtaque;
    private List<BufferedImage> dadosDefesa;
    private JComboBox<Integer> dadoAtaqueCB1;
    private JComboBox<Integer> dadoAtaqueCB2;
    private JComboBox<Integer> dadoAtaqueCB3;
    private JComboBox<Integer> dadoDefesaCB1;
    private JComboBox<Integer> dadoDefesaCB2;
    private JComboBox<Integer> dadoDefesaCB3;
    private Button confirmaDados;
    private ItemListener ataqueListener;
    private List<Integer> valoresAtaque;
    private List<Integer> valoresDefesa;
    private boolean isTeste;
    private Button teste;

    public JanelaJogo() {
        gameView = GameView.getInstanciaView();
        try {
        	//C:\\Users\\Murilo\\Desktop\\Projetos\\programacao_orientada_a_objetos\\War\\src\\view\\images
        	//C:\\\\Users\\\\miguel.batista_bigda\\\\Documents\\\\GitHub\\\\programacao_orientada_a_objetos\\\\War\\\\src\\\\view\\\\images\\\\tabuleiro_certo.jpg
            //C:\\Users\\joana\\OneDrive\\Documentos\\GitHub\\programacao_orientada_a_objetos\\War\\src\\view\\images\\tabuleiro_certo.jpg
        	imagemDeFundo = ImageIO.read(new File("C:\\\\\\\\Users\\\\\\\\miguel.batista_bigda\\\\\\\\Documents\\\\\\\\GitHub\\\\\\\\programacao_orientada_a_objetos\\\\\\\\War\\\\\\\\src\\\\\\\\view\\\\\\\\images\\\\\\\\tabuleiro_certo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        };
        
        frame = new Frame("Janela de Jogo do war");
        setSize(1200, 700);
        setLayout(null); 
        initButtons();   // Inicializa os botões
        initTerritorios();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        dadosAtaque = new ArrayList<>();
        dadosDefesa = new ArrayList<>();
        carregarImagemDado("War\\src\\view\\images\\dado_ataque_1.png");
        setVisible(true);
    }

    public void setIsTeste(boolean flag){
        isTeste = flag;
    }

    public void initButtons() {
        btnTerminaRodada = new Button("Terminar rodada");
        btnTerminaRodada.setBounds(1040, 640, 150, 30); // Define posição e tamanho do botão
        btnTerminaRodada.addActionListener(e -> {
            gameView.passaFaseView();
        });
        add(btnTerminaRodada);
        btnTrocaCartas = new Button("Ver cartas");
        btnTrocaCartas.addActionListener(e -> {
        	new JanelaCartas();
        });
        btnTrocaCartas.setBounds(915, 640, 120, 30);
        add(btnTrocaCartas);

        btnSalvarJogo = new Button("Salvar Jogo");
        btnSalvarJogo.addActionListener(e -> {
        	gameView.salvarView();
        });
        btnSalvarJogo.setEnabled(false);
        btnSalvarJogo.setBounds(10, 30, 120, 30);
        add(btnSalvarJogo);

        btnCorJogador = new Button("");
        btnCorJogador.setBounds(150, 30, 30, 30);
        add(btnCorJogador);
        setCorJogadorbtn();
        
        btnConfirmaDados = new Button("Confirmar");
        btnConfirmaDados.setBounds(370,500,60,30);
        add(btnConfirmaDados);
        btnConfirmaDados.setVisible(false);
    }

    {
        dictCorFundoLetra.put(Color.RED, Color.WHITE);
        dictCorFundoLetra.put(Color.BLUE, Color.WHITE);
        dictCorFundoLetra.put(Color.BLACK, Color.WHITE);
        dictCorFundoLetra.put(Color.GREEN, Color.BLACK);
        dictCorFundoLetra.put(Color.YELLOW, Color.BLACK);
        dictCorFundoLetra.put(Color.WHITE, Color.BLACK);
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
    
    public void setLabelPassa(int faseRodada, boolean rodadaInicialFlag){
        if (rodadaInicialFlag) {
            btnTerminaRodada.setLabel("Terminar rodada");
            return;
        }

        if(faseRodada == 0){
            btnTerminaRodada.setLabel("Passar para ataque");
        }
        else if(faseRodada == 1){
            btnTerminaRodada.setLabel("Passar para movimento");
        }
        else if(faseRodada == 2){
            removeDados(getGraphics());
            btnTerminaRodada.setLabel("Terminar rodada");
        }
    }
   
    public void setSalvarBttnState(boolean state) {
    	btnSalvarJogo.setEnabled(state);
    }
    
    public void setCorJogadorbtn(){
        String corJogador = gameView.getCorJogadorAtualView();
        btnCorJogador.setBackground(dictStrCor.get(corJogador));
        btnCorJogador.setForeground(dictCorFundoLetra.get(dictStrCor.get(corJogador)));
        String numExercitosDisponiveis = String.valueOf(gameView.getNumSoldadosDisponiveisView());
        btnCorJogador.setLabel(numExercitosDisponiveis);
    }

    public Button criaBtnTerritorio(String nome){
        String numExercitos = String.valueOf(gameView.getNumSoldadosView(nome));
        int[] coordenadas = dictTerritorioPosicao.get(nome);
        Button btnTerritorio = new Button(numExercitos);
        btnTerritorio.setBounds(coordenadas[0], coordenadas[1], 30, 30);
        
        String corJogador = gameView.getTerritorioCorView(nome);
        btnTerritorio.setBackground(dictStrCor.get(corJogador)); 
        btnTerritorio.setForeground(dictCorFundoLetra.get(dictStrCor.get(corJogador)));
        btnTerritorio.addActionListener(e -> {
            gameView.btnTerritorioController(nome, isTeste);
        });
        return btnTerritorio;
    }

    public void initTerritorios() {
        btnTerritorios = new ArrayList<>();
        Set<String> nomesTerritorios = dictTerritorioPosicao.keySet();
        
        for (String nome : nomesTerritorios) {
            Button btnTerritorio = criaBtnTerritorio(nome);
            dictTerritorioBtn.put(nome, btnTerritorio);
            add(btnTerritorio);
        }
    }

    public void atualizaNumSoldados(String nomeTerritorio){
        Button btnTerritorio = dictTerritorioBtn.get(nomeTerritorio);
        String numExercitos = String.valueOf(gameView.getNumSoldadosView(nomeTerritorio));
        btnTerritorio.setLabel(numExercitos);
        String numExercitosDisponiveis = String.valueOf(gameView.getNumSoldadosDisponiveisView());
        btnCorJogador.setLabel(numExercitosDisponiveis);
    }

    public void atualizaDono(String nomeTerritorio, String nomeJogador){
        Button btnTerritorio = dictTerritorioBtn.get(nomeTerritorio);
        String corJogador = gameView.getTerritorioCorView(nomeJogador);
        btnTerritorio.setBackground(dictStrCor.get(corJogador));
    }

    public void ataqueAlvos(String nomeTerritorio, List<String> alvos){
         
        for (String nome : dictTerritorioBtn.keySet()) {
            if(!alvos.contains(nome)){
                dictTerritorioBtn.get(nome).setVisible(false);
            }
        }
        
        int[] coordenadas = dictTerritorioPosicao.get(nomeTerritorio);     
        btnAtaque = new Button("1");
        btnAtaque.setBounds(coordenadas[0], coordenadas[1], 30, 30);
        String corJogador = gameView.getCorJogadorAtualView();
        btnAtaque.setBackground(dictStrCor.get(corJogador));
        btnAtaque.setForeground(dictCorFundoLetra.get(dictStrCor.get(corJogador)));
        btnAtaque.addActionListener(e -> {
            gameView.btnAtaqueView(nomeTerritorio);
        });
        add(btnAtaque);
    }

    public void movimentoTerritorio(String nomeTerritorio, List<String> vizinhos){
        for (String nome : dictTerritorioBtn.keySet()) {
            if(!vizinhos.contains(nome)){
                dictTerritorioBtn.get(nome).setVisible(false);
            }
        }

        int[] coordenadas = dictTerritorioPosicao.get(nomeTerritorio);     
        btnMover = new Button("1");
        btnMover.setBounds(coordenadas[0], coordenadas[1], 30, 30);
        String corJogador = gameView.getCorJogadorAtualView();
        btnMover.setBackground(dictStrCor.get(corJogador));
        btnMover.setForeground(dictCorFundoLetra.get(dictStrCor.get(corJogador)));
        btnMover.addActionListener(e -> {
            gameView.btnMoverView(nomeTerritorio);
        });
        add(btnMover);
    }
    
    public void voltaTerriorios(){
        String cor;
        for (String nome : dictTerritorioBtn.keySet()) {
            atualizaNumSoldados(nome);
            dictTerritorioBtn.get(nome).setVisible(true);
            cor = gameView.atualizaCor(nome);
            dictTerritorioBtn.get(nome).setBackground(dictStrCor.get(cor));
            dictTerritorioBtn.get(nome).setForeground(dictCorFundoLetra.get(dictStrCor.get(cor)));
        }
        if(btnAtaque != null){
            remove(btnAtaque);
        }
        if(btnMover != null){
            remove(btnMover);
        }
    }

    public int getNumAtacantes() {
        if (btnAtaque != null) {
            try {
                return Integer.parseInt(btnAtaque.getLabel());
            } catch (NumberFormatException e) {
                // Trata a exceção se o texto não for um número válido
                e.printStackTrace(); // ou outra forma de lidar com o erro, se necessário
            }
        }
        return 0; // ou outro valor padrão, dependendo do seu caso
    }

    public int getNumMovimento(){
        if (btnMover != null) {
            try {
                return Integer.parseInt(btnMover.getLabel());
            } catch (NumberFormatException e) {
                // Trata a exceção se o texto não for um número válido
                e.printStackTrace(); // ou outra forma de lidar com o erro, se necessário
            }
        }
        return 0; // ou outro valor padrão, dependendo do seu caso
    }

    public void atualizaBtnAtaque(int numExercitosAtacantes){
        btnAtaque.setLabel(String.valueOf(numExercitosAtacantes));
    }

    public void atualizaBtnMover(int numExercitosMovimento){
        btnMover.setLabel(String.valueOf(numExercitosMovimento));
    }

    public void finalizaJogo(String nomeJogador, String corJogador) {
        exibirVencedor(nomeJogador, corJogador);
        int opcao = JOptionPane.showConfirmDialog(
                this,
                "Deseja continuar jogando?",
                "Continuar?",
                JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            //code block
        } else {
            System.exit(0); // Encerrar o programa
        }
    }

    private void exibirVencedor(String nomeJogador, String corJogador) {
        JOptionPane.showMessageDialog(
                this,
                "Parabéns! O jogador vencedor é: " + nomeJogador + " (Cor: " + corJogador + ")",
                "Vencedor!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void carregarImagemDado(String caminho) {
        for (int i = 1; i < 7; i++) {
            try {
            	//C:\\Users\\Murilo\\Desktop\\Projetos\\programacao_orientada_a_objetos\\War\\src\\view\\images
                BufferedImage imagemDadoAtaque = ImageIO.read(new File("C:\\\\\\\\Users\\\\\\\\miguel.batista_bigda\\\\\\\\Documents\\\\\\\\GitHub\\\\\\\\programacao_orientada_a_objetos\\\\War\\\\src\\\\view\\\\images\\\\dado_ataque_"+ String.valueOf(i) +".png"));
                BufferedImage imagemDadoDefesa = ImageIO.read(new File("C:\\\\\\\\Users\\\\\\\\miguel.batista_bigda\\\\\\\\Documents\\\\\\\\GitHub\\\\\\\\programacao_orientada_a_objetos\\\\War\\\\src\\\\view\\\\images\\\\dado_defesa_"+ String.valueOf(i) +".png"));
                dadosAtaque.add(imagemDadoAtaque);
                dadosDefesa.add(imagemDadoDefesa);
                repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exibiDados(List<Integer> dadosAtaquesnum, List<Integer> dadosDefesasnum){
        paintDados(getGraphics(), dadosAtaquesnum, dadosDefesasnum);
    }

    public void removeDados(Graphics g){
        for (String nome : dictTerritorioBtn.keySet()) {
            dictTerritorioBtn.get(nome).setVisible(false);
        }
        for (String nome : dictTerritorioBtn.keySet()) {
            dictTerritorioBtn.get(nome).setVisible(true);
        }
    }
    
    public void mostraSelecaoDados(int numAtacantes, int numDefensores) {
        selecionaValorDadosAtaque(numAtacantes);
        selecionaValorDadosDefesa(numDefensores);

        btnConfirmaDados.setVisible(true);
        btnConfirmaDados.addActionListener(e -> {
           List<List<Integer>> resultados = retornaDados(numAtacantes, numDefensores);
           //valoresAtaque = resultados.get(0);
           //valoresDefesa = resultados.get(0);
           gameView.avaliaAtaqueView(valoresAtaque, valoresDefesa);
        });
        removeDadosTeste();
    }

    public void removeDadosTeste(){
        remove(dadoAtaqueCB1);
        if (dadoAtaqueCB2 != null) {
            remove(dadoAtaqueCB2);
        }
        if (dadoAtaqueCB3 != null) {
            remove(dadoAtaqueCB3);
        }
        remove(dadoDefesaCB1);
        if (dadoDefesaCB2 != null) {
            remove(dadoDefesaCB2);
        }
        if (dadoDefesaCB3 != null) {
            remove(dadoDefesaCB3);
        }
    }
    
    public List<Integer> retornaValoresAtaque(){
        return valoresAtaque;
    }

    public List<Integer> retornaValoresDefesa(){
        return valoresDefesa;
    }

    public void selecionaValorDadosAtaque(int numDados){
        Integer[] valoresPossiveis = {1, 2, 3, 4, 5, 6};

        /* 
        JComboBox<Integer> abacaxi = new JComboBox<>(valoresPossiveis);
        add(abacaxi);
        abacaxi.setBounds(1000, 300 + (1 * 50), 30, 30);
        abacaxi.setVisible(true);
        */

        JComboBox<Integer> dadoAtaqueCB1 = new JComboBox<>(valoresPossiveis);
        add(dadoAtaqueCB1);
        dadoAtaqueCB1.setBounds(450, 450 + (0 * 50), 30, 30);
        dadoAtaqueCB1.setVisible(true);

        if(numDados >= 2){
            dadoAtaqueCB2 = new JComboBox<>(valoresPossiveis);
            add(dadoAtaqueCB2);
            dadoAtaqueCB2.setBounds(450, 450 + (1 * 50), 30, 30);
            dadoAtaqueCB2.setVisible(true);
        }
        
        if(numDados >= 3){
            dadoAtaqueCB3 = new JComboBox<>(valoresPossiveis);
            add(dadoAtaqueCB3);
            dadoAtaqueCB3.setBounds(450, 450 + (2 * 50), 30, 30);
            dadoAtaqueCB3.setVisible(true);
        }
    }

    public void selecionaValorDadosDefesa(int numDados){
        Integer[] valoresPossiveis = {1, 2, 3, 4, 5, 6};

        System.out.println("Combo box de defesa");
        dadoDefesaCB1 = new JComboBox<>(valoresPossiveis);
        add(dadoDefesaCB1);
        dadoDefesaCB1.setBounds(500, 450 + (0*50), 30, 30);
        dadoDefesaCB1.setVisible(true);


        if(numDados >= 2){
            dadoDefesaCB2 = new JComboBox<>(valoresPossiveis);
            add(dadoDefesaCB2);
            dadoDefesaCB2.setBounds(500, 450 + (1 * 50), 30, 30);
            dadoDefesaCB2.setVisible(true);
        }
        
        if(numDados >= 3){
            dadoDefesaCB3 = new JComboBox<>(valoresPossiveis);
            add(dadoDefesaCB3);
            dadoDefesaCB3.setBounds(500, 450 + (2 * 50), 30, 30);
            dadoDefesaCB3.setVisible(true);
        }
    }

    private List<Integer> resultadoDadosAtaque(List<JComboBox<Integer>> comboBoxArray) {
        List<Integer> selectedValues = new ArrayList<>();
        for (int i = 0; i < comboBoxArray.size(); i++) {
            selectedValues.add((int) comboBoxArray.get(i).getSelectedItem());
        }
        return selectedValues;
    }

    private List<Integer> resultadoDadosDefesa(List<JComboBox<Integer>> comboBoxArray) {
        List<Integer> selectedValues = new ArrayList<>();
        for (int i = 0; i < comboBoxArray.size(); i++) {
            selectedValues.add((int) comboBoxArray.get(i).getSelectedItem());
        }
        return selectedValues;
    }

    public List<List<Integer>> retornaDados(List<JComboBox<Integer>> comboBoxAtaque, List<JComboBox<Integer>> comboBoxDefesa){
        List<List<Integer>> resultados = new ArrayList<>();
        List<Integer> dadosAtaque = resultadoDadosAtaque(comboBoxAtaque);
        List<Integer> dadosDefesa = resultadoDadosDefesa(comboBoxDefesa);

        resultados.add(dadosAtaque);
        resultados.add(dadosDefesa);
        return resultados;
    }

    public void paintDados(Graphics g, List<Integer> dadosAtaquesnum, List<Integer> dadosDefesasnum) {
        int x = 450;
        int y = 450;
        for (int i = 0; i < dadosAtaquesnum.size(); i++) {
            g.drawImage(this.dadosAtaque.get(dadosAtaquesnum.get(i) - 1), x, y, this);
            y += 50;
        }
        x = 500;
        y = 450;
        for (int i = 0; i < dadosDefesasnum.size(); i++) {
            g.drawImage(this.dadosDefesa.get(dadosDefesasnum.get(i) - 1), x, y, this);
            y += 50;
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
