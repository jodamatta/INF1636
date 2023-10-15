package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ContinenteTest {

    private Continente continente;
    private Jogador jogador;

    @Before
    public void setUp() {
        continente = new Continente("Africa");
        jogador = new Jogador("Joana", CorJogador.Azul);
    }

    @Test
    public void testAddAndGetPais() {
        Territorio territorio = new Territorio("Pais1", jogador); 
        continente.addPais(territorio);
        assertEquals(1, continente.getPaises().size());
        assertEquals("Pais1", continente.getPaises().get(0).getNome());
    }

    @Test
    public void testGetBonus() {
        assertEquals(3, continente.getBonus());
    }

    @Test
    public void testFoiDominado() {
        Territorio territorio1 = new Territorio("Pais1", jogador);
        Territorio territorio2 = new Territorio("Pais2", jogador);
        
        continente.addPais(territorio1);
        continente.addPais(territorio2);

        assertEquals(1, continente.foiDominado(jogador));

        Jogador anotherJogador = new Jogador("Miguel", CorJogador.Vermelho);
        Territorio territorio3 = new Territorio("Pais3", anotherJogador);
        continente.addPais(territorio3);

        assertEquals(0, continente.foiDominado(jogador));
    }
}
