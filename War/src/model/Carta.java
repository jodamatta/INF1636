package model;


class Carta{
    private String simbolo;
    private String nomeTerritorio;

    public Carta(String nomeTerritorio, String simbolo) {
        this.simbolo = simbolo;
        this.nomeTerritorio = nomeTerritorio;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getTerritorio() {
        return nomeTerritorio;
    }
}

