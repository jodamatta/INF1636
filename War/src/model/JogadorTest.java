package model;

import static org.junit.Assert.assertEquals;
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
}