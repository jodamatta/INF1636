package model;


class Territorio {
    private String nome;
    private Exercito exercito;

    public Territorio(String nome, Jogador jogador) {
        this.nome = nome;
        this.exercito = new Exercito(1, jogador);
    }

    public String getNome() {
        return nome;
    }

    public Exercito getExercito() {
        return exercito;
    }

    public void setExercito(Exercito exercito) {
        this.exercito = exercito;
    }
}