package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GameModelTest {

    private GameModel gameModel;

    @Before
    public void setUp() {
        GameModel.resetInstancia();
        gameModel = GameModel.getInstancia();
    }

    // teste de adicionar jogador
    @Test
    public void testAddJogador() {
        gameModel.addJogador("Joana", CorJogador.Azul);
        assertEquals(1, gameModel.getJogadores().size());
    }

    // teste de adicionar mais jogadores que o limite
    @Test(expected = IllegalStateException.class)
    public void testAddJogadorMaxLimit() {
    	for (int i = 0; i < CorJogador.values().length; i++) {
    	    gameModel.addJogador("Jogador" + i, CorJogador.values()[i]);
    	}
    	gameModel.addJogador("JogadorExtra", CorJogador.Azul); 
    }

    // teste do filtro de objetivos
    @Test
    public void testFiltroObjetivos() {
        gameModel.addJogador("Joana", CorJogador.Azul);
        gameModel.addJogador("Miguel", CorJogador.Vermelho);
        
        // garantir que só objetivos válidos estão em jogo
        List<Objetivo> objetivos = gameModel.filtraObjetivos();
        assertFalse(objetivos.contains(new Objetivo(ListaObjetivos.ELIM_AMARELO)));
        assertFalse(objetivos.contains(new Objetivo(ListaObjetivos.ELIM_VERDE)));
        assertFalse(objetivos.contains(new Objetivo(ListaObjetivos.ELIM_BRANCO)));
        assertFalse(objetivos.contains(new Objetivo(ListaObjetivos.ELIM_PRETO)));
    }

    // teste de sorteio de objetivos
    @Test
    public void testSorteiaObjetivos() {
        gameModel.addJogador("Joana", CorJogador.Azul);
        gameModel.addJogador("Miguel", CorJogador.Vermelho);
        gameModel.sorteiaObjetivos();
        
        // garantir que todo jogador tem um objetivo
        for (Jogador jogador : gameModel.getJogadores()) {
            assertNotNull(jogador.getObjetivo());
        }
    }

    // teste de ordem de jogada
    @Test
    public void testSetOrdemJogada() {
        gameModel.addJogador("Joana", CorJogador.Azul);
        gameModel.addJogador("Miguel", CorJogador.Vermelho);
        gameModel.addJogador("Murilo", CorJogador.Verde);

        // pega a ordem antes de sortear
        List<Jogador> initialOrder = new ArrayList<>(gameModel.getJogadores());

        boolean ordemMudou = false;

        // embaralho 10 vezes porque, como é aleatório, podemos ter um caso que a ordem não muda
        for (int i = 0; i < 10; i++) {
            gameModel.setOrdemJogada();
            if (!initialOrder.equals(gameModel.getJogadores())) {
            	ordemMudou = true;
                break;
            }
        }
        assertTrue(ordemMudou);
    }
}