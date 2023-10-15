package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

public class JogadorTest {

    private Jogador jogador;

    @Before
    public void setUp() {
        jogador = new Jogador("Joana", CorJogador.Azul);
    }

    @Test
    public void testConstrutorGets() {
        assertEquals("Joana", jogador.getNome());
        assertEquals(CorJogador.Azul, jogador.getCor());
    }

    @Test
    public void testSetObjetivo() {
        jogador.setObjetivo(ListaObjetivos.CONQ_24);
        assertEquals(ListaObjetivos.CONQ_24, jogador.getObjetivo());
    }
    
    @Test
    public void testAddRemoveCarta() {
        Carta carta = new Carta("SomeTerritory", "TRIANGULO");
        
        jogador.addCarta(carta);
        assertTrue(jogador.getCartas().contains(carta));

        jogador.removeCarta(carta);
        assertFalse(jogador.getCartas().contains(carta));
    }

    @Test
    public void testAddRemoveTerritorio() {
        Territorio territorio = new Territorio("SomeTerritory", jogador);
        
        jogador.addTerritorio(territorio);
        assertTrue(jogador.getTerritorios().contains(territorio));

        jogador.removeTerritorio(territorio);
        assertFalse(jogador.getTerritorios().contains(territorio));
    }

    @Test
    public void testVerificaTroca() {
        Carta carta1 = new Carta("Territory1", "TRIANGULO");
        Carta carta2 = new Carta("Territory2", "TRIANGULO");
        Carta carta3 = new Carta("Territory3", "TRIANGULO");
        Carta carta4 = new Carta("Territory4", "CIRCULO");

        assertEquals(0, jogador.verificaTroca());

        jogador.addCarta(carta1);
        jogador.addCarta(carta2);
        assertEquals(0, jogador.verificaTroca());

        jogador.addCarta(carta3);
        assertEquals(1, jogador.verificaTroca());

        jogador.addCarta(carta4);
        assertEquals(1, jogador.verificaTroca());
    }
}