
package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class DeckTerritoriosTest {

    private DeckTerritorios deck;

    @Before
    public void setUp() {
        deck = new DeckTerritorios();
    }

    @Test
    public void testDeckSize() {
    	// temos um total de 51 territorios
        assertEquals(51, deck.getSize());  
    }

    @Test
    public void testDrawCard() {
        deck = new DeckTerritorios();
        Carta card = deck.drawCard();
        // garantir que uma carta foi comprada
        assertNotNull(card); 
        assertEquals(50, deck.getSize());
    }

    @Test
    public void testDrawAllCards() {
        deck = new DeckTerritorios();
        for (int i = 0; i < 51; i++) {
            assertNotNull(deck.drawCard()); 
        }
        // garantir que o deck esta vazio
        assertEquals(0, deck.getSize()); 
        assertNull(deck.drawCard()); 
    }

    @Test
    public void testCardSymbols() {
        deck = new DeckTerritorios();
        int triangles = 0;
        int squares = 0;
        int circles = 0;
        
        for (int i = 0; i < 51; i++) {
            Carta card = deck.drawCard();
            if ("TRIANGULO".equals(card.getSimbolo())) {
                triangles++;
            } else if ("QUADRADO".equals(card.getSimbolo())) {
                squares++;
            } else if ("CIRCULO".equals(card.getSimbolo())) {
                circles++;
            }
        }

        assertEquals(19, triangles);
        assertEquals(16, squares);
        assertEquals(16, circles);
    }
}
