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

    public Jogador getJogador() {
        return jogador;
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

    public void setJogador(Jogador outro_jogador) {
        this.jogador = outro_jogador;
        this.jogador.removeTerritorio(null);
    }
}