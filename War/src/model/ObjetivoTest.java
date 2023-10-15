package model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ObjetivoTest {

    private Objetivo objetivoElimAzul;

    @Before
    public void setUp() {
        objetivoElimAzul = new Objetivo(ListaObjetivos.ELIM_AZUL);
    }

    // teste se o objetivo foi corretamente definido e corretamente retornado
    @Test
    public void testGetObjetivo() {
        assertEquals(ListaObjetivos.ELIM_AZUL, objetivoElimAzul.getObjetivo());
    }
    
    // teste se os nomes estao sendo lidos corretamente
    @Test
    public void testToString() {
        assertEquals("ELIM_AZUL", objetivoElimAzul.toString());
    }
}
