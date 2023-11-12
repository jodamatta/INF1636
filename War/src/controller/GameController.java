package controller;

import model.GameModel;
import view.GameView;
import java.util.List;

public class GameController {
    private static GameController instance = null;
    private static GameModel gameModel; 
    private static GameView gameView;
    private int ataqueFlag = 0;
    private int numExercitosAtacantes = 1;
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
        return gameModel.getNumSoldados(nomeTerritorio);
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
        switch (faseRodada) {
            case 0:
                addExercitoTerritorioController(nomeTerritorio);
                break;
            case 1:
                if(ataqueFlag == 0){
                    ataqueTerritorioController(nomeTerritorio);
                } else{
                    destinoAtaqueController(nomeTerritorio);
                }
                break;
            case 2:
                //Mecanica de movimento
                break;
            default:
                System.out.println("Rodada fora de sincronia");
                break;
        }
    }

    public void destinoAtaqueController(String nomeTerritorio){
        int numAtacantes = gameView.getNumAtacantesView();
        gameModel.destinoAtaque(nomeTerritorio, numAtacantes);
        gameView.atualizaNumSoldadosView(nomeTerritorio);
        ataqueFlag = 0;
    }

    public void ataqueTerritorioController(String nomeTerritorio){
        List<String> alvos = gameModel.ataqueTerritorio(nomeTerritorio);
        if(alvos == null){
            return;
        }
        gameView.ataqueTerritorioView(nomeTerritorio, alvos);
    }

    public void btnAtaqueController(String nomeTerritorio){
        numExercitosAtacantes = numExercitosAtacantes + 1;
        if(numExercitosAtacantes > 3 || numExercitosAtacantes > gameModel.getNumSoldados(nomeTerritorio)-1){
            numExercitosAtacantes = 1;
        }
        gameView.atualizaBtnAtaque(nomeTerritorio, numExercitosAtacantes);
        //gameModel.setTerritorioAtaque(nomeTerritorio);
    }

    public void passaVezController(){
         gameView.passaVezView();
    }

    public void addExercitoTerritorioController(String nomeTerritorio){
        int numExercitosDisponiveis = gameModel.getNumSoldadosDisponiveis();
        if (numExercitosDisponiveis == 0) {
            return;
        }

        gameModel.addExercitoTerritorio(nomeTerritorio, 1);
        gameModel.diminuiNumSoldadosDisponiveis(1);
        gameView.atualizaNumSoldadosView(nomeTerritorio);
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