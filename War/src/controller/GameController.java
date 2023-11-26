package controller;

import model.GameModel;
import view.GameView;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private static GameController instance = null;
    private static GameModel gameModel; 
    private static GameView gameView;
    private int ataqueFlag = 0;
    private int movimentoFlag = 0;
    private int numExercitosAtacantes = 1;
    private String movOrigem = null;
    private String movDestino = null;
    private int numExercitosMovimento = 0;
    private List<Integer> dadosAtaqueTeste;
    private List<Integer> dadosDefesaTeste;
    public GameController(){
    }

    public static GameController getInstanciaController(){
        if(instance == null) {
			instance = new GameController();
		}
		return instance;
    }

    public void initGameController(){
        gameModel = GameModel.getInstancia();
        gameView = GameView.getInstanciaView();
    }

    public void janelaInicialController(){
        gameView.chamaJanelaInicio();
    }

    public void janelaJogoController(){
        gameView.chamaJanelaJogo();
    }
    
    public void removeConfirmaController() {
    	gameView.removeConfirmaView();
    }

    public String[] getCoresController(){
        return gameModel.getCores();
    }

    public void addJogadorControler(String nomeJogador, String corJogador){
        if (gameModel.getJogadores().size()>= gameModel.getMaxJogadores()) {
			throw new IllegalStateException("Numero máximo de jogadores atingido.");
		}
        gameModel.addJogador(nomeJogador, corJogador);
    }

    public int getNumSoldadosController(String nomeTerritorio){
        return gameModel.getNumSoldadosTotal(nomeTerritorio);
    }
    
    public int getNumDefensoreController() {
    	return gameModel.getNumDefensores();
    }

    public String getTerritorioCorController(String nomeTerritorio){
        return gameModel.getTerritorioCor(nomeTerritorio);
    }

    public void beginGameController(){
        if (gameModel.getJogadores().size() == 0) {
			throw new IllegalStateException("Nao ha jogadores registrados");
		}
        gameModel.beginGame();
    }

    public void setIsTesteController(boolean flagTeste){
        gameView.setIsTesteView(flagTeste);
    }

    public boolean getIsTesteController(){
        return gameView.getIsTesteView();
    }
    
    public void btnTerritorioController(String nomeTerritorio, boolean isTeste){
        int faseRodada = gameModel.getFaseRodada();
        boolean ataque;
        boolean movimento;
        switch (faseRodada) {
            case 0:
                addExercitoTerritorioController(nomeTerritorio);
                break;
            case 1:
                if(ataqueFlag == 0){
                	System.out.println("AAAAA ataqueFlag == 0");
                    ataque = ataqueTerritorioController(nomeTerritorio);
                    if(ataque){
                        ataqueFlag = 1;
                    }
                    
                } else{
                	System.out.println("AAAAA ataqueFlag == 1");
                    ataqueFlag = 0;
                    destinoAtaqueController(nomeTerritorio, isTeste);
                    if (!isTeste) { 
                    	gameModel.mataAtaque();
                    }
                }
                break;
            case 2:
                if(movimentoFlag == 0){
                    movimento = setTerritorioMovimento(nomeTerritorio);
                    if(movimento){
                        movOrigem = nomeTerritorio;
                        movimentoFlag = 1;
                    } 
                } else{
                    movimentoFlag = 0;
                    movimentoTerritorio(nomeTerritorio);
                    gameView.voltaTerrioriosView();
                    mataMovimento();
                }
                break;
            default:
                System.out.println("Rodada fora de sincronia");
                break;
        }
    }

    public void mataMovimento(){
        movOrigem = null;
        movDestino = null;
        numExercitosMovimento = 0;
    }

    public void movimentoTerritorio(String nomeTerritorio){
        movDestino = nomeTerritorio;
        int numMovimento = gameView.getNumMovimentoView();
        gameModel.movimentaExercito(numMovimento, movOrigem, movDestino);
    }

    public boolean setTerritorioMovimento(String nomeTerritorio){
        List<String> vizinhos = gameModel.getVizinhosAliados(nomeTerritorio);
        int numExVal = gameModel.getNumSoldadosMovimentoValido(nomeTerritorio);
        if (numExVal == 0) {
            return false;
        }
        if(vizinhos == null || vizinhos.isEmpty()){
            return false;
        }
        gameView.movimentoTerritorioView(nomeTerritorio, vizinhos);
        return true;
    }

    public void destinoAtaqueController(String nomeTerritorio, boolean isTeste){
    	
        int numAtacantes = gameView.getNumAtacantesView();
        List<Integer> listDadosAtaque;
        List<Integer> listDadosDefesa;
        if(isTeste){
        	System.out.println("isTeste destinoAtaqueTeste");
            gameModel.destinoAtaqueTeste(nomeTerritorio, numAtacantes);
        }
        else{
            gameModel.destinoAtaque(nomeTerritorio, numAtacantes);
            listDadosAtaque = gameModel.getDadosAtaque();
            listDadosDefesa = gameModel.getDadosDefesa();
            gameView.voltaTerrioriosView();
            gameView.exibiDados(listDadosAtaque, listDadosDefesa);
            ataqueFlag = 0;
        }
    }

    public void mostraSelecaoDadosController(){
        gameView.mostraSelecaoDadosView();
    }

    public void avaliaAtaqueController(List<Integer> dadosAtaque, List<Integer>dadosDefesa){
        this.dadosAtaqueTeste = dadosAtaque;
        this.dadosDefesaTeste = dadosDefesa;
        gameModel.avaliaAtaqueTeste();
        gameView.voltaTerrioriosView();
        //gameView.exibiDados(dadosAtaqueTeste, dadosDefesaTeste);
        gameModel.mataAtaque();
    }

    public List<List<Integer>> retornaDadosController(){
        List<List<Integer>> retorno = new ArrayList<>();
        retorno.add(dadosAtaqueTeste);
        retorno.add(dadosDefesaTeste);
        return retorno;
    }

    public boolean ataqueTerritorioController(String nomeTerritorio){
        List<String> alvos = gameModel.ataqueTerritorio(nomeTerritorio);
        if(alvos == null || alvos.isEmpty()){
            System.out.println("Sem alvos validos");
            return false;
        }
        gameView.ataqueTerritorioView(nomeTerritorio, alvos);
        return true;
    }

    public void btnAtaqueController(String nomeTerritorio){
        numExercitosAtacantes = numExercitosAtacantes + 1;
        if(numExercitosAtacantes > 3 || numExercitosAtacantes > gameModel.getNumSoldados(nomeTerritorio)-1){
            numExercitosAtacantes = 1;
        }
        gameView.atualizaBtnAtaque(nomeTerritorio, numExercitosAtacantes);
        //gameModel.setTerritorioAtaque(nomeTerritorio);
    }

    public void btnMoverController(String nomeTerritorio){
        numExercitosMovimento = numExercitosMovimento + 1;
        if(numExercitosMovimento > gameModel.getNumSoldadosMovimentoValido(nomeTerritorio)){
            numExercitosMovimento = 0;
        }
        gameView.atualizaBtnMover(nomeTerritorio, numExercitosMovimento);
    }

    public void setCorJogadorController(){
         gameView.setCorJogadorView();
    }

    public void addExercitoTerritorioController(String nomeTerritorio){
        boolean addValido;
        int numExercitosDisponiveis = gameModel.getNumSoldadosDisponiveis();
        if (numExercitosDisponiveis == 0) {
            return;
        }

        addValido = gameModel.addExercitoTerritorio(nomeTerritorio, 1);
        if(addValido){
            gameModel.diminuiNumSoldadosDisponiveis(1);
            gameView.atualizaNumSoldadosView(nomeTerritorio);
            gameView.salvarBttnDisableView();
        }
    }

    public String getCorJogadorAtualController(){
        return gameModel.getCorJogadorAtual();
    }
    
    public int getNumSoldadosDisponiveisController(){
        return gameModel.getNumSoldadosDisponiveis();
    }

    public int getFaseRodadaController(){
        return gameModel.getFaseRodada();
    }

    public boolean passaFaseController(){
        boolean rodadaInicialFlag = gameModel.passaFase();
        int faseRodada = gameModel.getFaseRodada();
        gameView.voltaTerrioriosView();
        if (!rodadaInicialFlag && faseRodada == 0) {        	
        	gameView.salvarBttnEnableView();
        }
        else {
        	gameView.salvarBttnDisableView();
        }
        return rodadaInicialFlag;
    }
    
    public void salvarController() {
    	gameModel.salvar();
    }
    
    public void continuaJogoController() {
    	gameModel.continuaJogo();
    }
    

    public List<String> getCartasJogadorAtualController(){
        return gameModel.getCartasJogadorAtual();
    }

    public void addExercitoCartaController(){
        gameModel.addExercitoCarta();
    }

    public String getObjetivoJogadorAtualController(){
        return gameModel.getObjetivoJogadorAtual();
    }

    public void jogoTesteController(){
        gameModel.jogoTeste();
    }

    public void voltaTerrioriosController(){
        gameView.voltaTerrioriosView();
    }

    public String getCorPorNome(String nomeJogador){
        return gameModel.getCorPorNome(nomeJogador);
    }

    public void janelaFimJogoController(String nomeJogador){
        gameView.chamaJanelaFimJogo(nomeJogador, getCorPorNome(nomeJogador));
    }

}   
