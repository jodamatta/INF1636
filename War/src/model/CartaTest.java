package model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CartaTest {

    private Carta carta;

    @Before
    public void setUp() {
        carta = new Carta("Brasil", "Circulo");
    }

    @Test
    public void testGetSimbolo() {
        assertEquals("Circulo", carta.getSimbolo());
    }

    @Test
    public void testGetTerritorio() {
        assertEquals("Brasil", carta.getTerritorio());
    }
}
