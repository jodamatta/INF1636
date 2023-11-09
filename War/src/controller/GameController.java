package controller;

import java.util.ArrayList;
import java.util.List;

import model.GameModel;
import view.GameView;

public class GameController {
    private static GameController instance = null;
    private static GameModel gameModel; 
    private static GameView gameView;

    public GameController(){
        gameModel = GameModel.getInstancia();
        gameView = GameView.getInstanciaView();
        initController();
    }

    public static GameController getInstanciaController(){
        if(instance == null) {
			instance = new GameController();
		}
		return instance;
    }

    private void initController(){
        gameView.initUI();
    }

    public void startGame(int numJogadores) {
        List<String> coresEscolhidas = new ArrayList<>();
        List<String> nomesJogadores = new ArrayList<>();
        gameView.lblCoresRepetidas.setVisible(false);
        gameView.lblCorInvalida.setVisible(false);
        gameView.lblNomeInvalido.setVisible(false);

        for(int i = 0; i < numJogadores; i++){
            String nomeJogador = campoNomeJogadores[i].getText().trim();
            String corJogador = (String) coresComboBox[i].getSelectedItem();

            if("--Selecione Cor--".equals(corJogador)){
                lblCoresRepetidas.setVisible(false);
                lblNomeInvalido.setVisible(false);
                lblCorInvalida.setVisible(true);
                return;
            }
            else if("".equals(nomeJogador)){
                lblCorInvalida.setVisible(false);
                lblCoresRepetidas.setVisible(false);
                lblNomeInvalido.setVisible(true);
                return;
            } 
            else if(coresEscolhidas.contains(corJogador)){
                lblCorInvalida.setVisible(false);
                lblNomeInvalido.setVisible(false);
                lblCoresRepetidas.setVisible(true);
                return;
            }
            else {
            nomesJogadores.add(nomeJogador);
            coresEscolhidas.add(corJogador);
            }
        }
    	for (int i = 0; i < numJogadores; i++) {
            gameModel.addJogador(nomesJogadores.get(i), coresEscolhidas.get(i));
        }
        gameModel.beginGame();
        return;
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
 * 1. Exércitos se movimentando 2x por rodada
 */