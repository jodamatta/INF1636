package model;

import java.util.Random;

public class Dado {

    private Random random;

    public Dado() {
        random = new Random();
    }

    public int sortearDado() {
        return random.nextInt(6) + 1; // Gera um número aleatório de 1 a 6
    }
}

