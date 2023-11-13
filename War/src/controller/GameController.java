package controller;

import model.GameModel;
import view.GameView;
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

    public String getTerritorioCorController(String nomeTerritorio){
        return gameModel.getTerritorioCor(nomeTerritorio);
    }

    public void beginGameController(){
        if (gameModel.getJogadores().size() == 0) {
			throw new IllegalStateException("Nao ha jogadores registrados");
		}
        gameModel.beginGame();
    }

    public void btnTerritorioController(String nomeTerritorio){
        int faseRodada = gameModel.getFaseRodada();
        boolean ataque;
        boolean movimento;
        switch (faseRodada) {
            case 0:
                addExercitoTerritorioController(nomeTerritorio);
                break;
            case 1:
                if(ataqueFlag == 0){
                    ataque = ataqueTerritorioController(nomeTerritorio);
                    if(ataque){
                        ataqueFlag = 1;
                    }
                    
                } else{
                    ataqueFlag = 0;
                    destinoAtaqueController(nomeTerritorio);
                    gameModel.mataAtaque();
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


    public void destinoAtaqueController(String nomeTerritorio){
        int numAtacantes = gameView.getNumAtacantesView();
        gameModel.destinoAtaque(nomeTerritorio, numAtacantes);
        gameView.voltaTerrioriosView();
        ataqueFlag = 0;
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

    public void passaVezController(){
         gameView.passaVezView();
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

    public void passaFaseController(){
        gameModel.passaFase();
        gameView.voltaTerrioriosView();
    }
}


/*
 * JANELA INICIAL
 * 1. Novo Jogo
 *  1.1 nome dos jogadores e suas cores
 *  1.2 começar jogo
 * 2. Continuar jogo (IMPLEMENTAREMOS DEPOIS)
 * 
 * JANELA JOGO
 * 1. Adicionar tropas (fase 1)
 *  1.1 Verificar Continente (em tropas ganhas por conquista de continente)
 * 2. Ataque (fase 2)
 *  2.1 Verificar fronteiras
 * 3. Movimento (fase 3)
 *  3.1 Verificar fronteiras
 * 4. Passar pro proximo jogador
 * 5. Ver Cartas
 *  5.1 Trocar cartas
 * 
 * OBS
 * -> Implementar a logica das fases no controller (passar o inteiro da fase como parametro)
 * 
 * REGRAS QUE ABRIMOS MÃO
 * 1. Botar tropas da conquista de continente só no próprio continente
 */