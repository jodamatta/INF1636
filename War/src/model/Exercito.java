package model;

//Miguel?

class Exercito {
    private int numeroSoldados;
    private Jogador jogador;
    
    public Exercito(int numeroSoldados, Jogador jogador) {
        this.numeroSoldados = numeroSoldados;
        this.jogador = jogador;
    }

    public int getNumeroSoldados() {
        return numeroSoldados;
    }

    public void setNumeroSoldados(int numeroSoldados) {
        this.numeroSoldados = numeroSoldados;
    }

    public void adicionarSoldados(int quantidade) {
        this.numeroSoldados += quantidade;
    }

    public void removerSoldados(int quantidade) {
        this.numeroSoldados -= quantidade;
        if (this.numeroSoldados < 0) {
            this.numeroSoldados = 0;
        }
    }
}