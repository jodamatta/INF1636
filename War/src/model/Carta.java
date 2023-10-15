package model;

//Miguel

class Carta{
    private int simbolo;
    private Territorio territorio;

    public Carta(int simbolo, Territorio territorio) {
        this.simbolo = simbolo;
        this.territorio = territorio;
    }

    public int getSimbolo() {
        return simbolo;
    }

    public Territorio getTerritorio() {
        return territorio;
    }
}

