package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TerritorioTest {

    private Territorio territorio;
    private Jogador jogador;

    @Before
    public void setUp() {
        jogador = new Jogador("John", CorJogador.Azul);
        territorio = new Territorio("AFRICA_DO_SUL", jogador);
    }

    @Test
    public void testCreation() {
        assertEquals("AFRICA_DO_SUL", territorio.getNome());
        assertEquals(jogador, territorio.getJogador());
        assertEquals(1, territorio.getNumeroSoldados()); 
    }

    @Test
    public void testAlteraNumSoldados() {
        territorio.alteraNumSoldados(5);
        assertEquals(6, territorio.getNumeroSoldados()); 

        territorio.alteraNumSoldados(-2);
        assertEquals(4, territorio.getNumeroSoldados());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAlteraNumSoldadosNegative() {
        territorio.alteraNumSoldados(-2); 
    }

    @Test
    public void testSetJogador() {
        Jogador anotherJogador = new Jogador("Miguel", CorJogador.Vermelho);
        territorio.setJogador(anotherJogador);
        assertEquals(anotherJogador, territorio.getJogador());
    }

    @Test
    public void testGetContinente() {
        int continenteId = territorio.getContinente();
        assertEquals(0, continenteId);
    }
}
