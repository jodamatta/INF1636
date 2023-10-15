package model;
import java.util.HashMap;
import java.util.Map;

class Territorio {
    private String nome;
    private int numeroSoldados;
    private Jogador jogador;
    private Map<String, Integer> myMap = new HashMap<String, Integer>();

    {
        myMap.put("AFRICA_DO_SUL", 0);
        myMap.put("ANGOLA", 0);
        myMap.put("ARGELIA", 0);
        myMap.put("EGITO", 0);
        myMap.put("NIGERIA", 0);
        myMap.put("SOMALIA", 0);
        myMap.put("ALASCA", 1);
        myMap.put("CALGARY", 1);
        myMap.put("CALIFORNIA", 1);
        myMap.put("GROELANDIA", 1);
        myMap.put("MEXICO", 1);
        myMap.put("NOVAYORK", 1);
        myMap.put("QUEBEC", 1);
        myMap.put("TEXAS", 1);
        myMap.put("VANCOUVER", 1);
        myMap.put("ARABIA_SAUDITA", 2);
        myMap.put("BANGLADESH", 2);
        myMap.put("CAZAQUISTAO", 2);
        myMap.put("CHINA", 2);
        myMap.put("COREIA_DO_NORTE", 2);
        myMap.put("COREIA_DO_SUL", 2);
        myMap.put("ESTONIA", 2);
        myMap.put("INDIA", 2);
        myMap.put("IRA", 2);
        myMap.put("IRAQUE", 2);
        myMap.put("JAPAO", 2);
        myMap.put("JORDANIA", 2);
        myMap.put("LETONIA", 2);
        myMap.put("MONGOLIA", 2);
        myMap.put("PAQUISTAO", 2);
        myMap.put("RUSSIA", 2);
        myMap.put("SIBERIA", 2);
        myMap.put("SIRIA", 2);
        myMap.put("TAILANDIA", 2);
        myMap.put("TURQUIA", 2);
        myMap.put("ARGENTINA", 3);
        myMap.put("BRASIL", 3);
        myMap.put("PERU", 3);
        myMap.put("VENEZUELA", 3);
        myMap.put("ESPANHA", 4);
        myMap.put("FRANCA", 4);
        myMap.put("ITALIA", 4);
        myMap.put("POLONIA", 4);
        myMap.put("REINO_UNIDO", 4);
        myMap.put("ROMENIA", 4);
        myMap.put("SUECIA", 4);
        myMap.put("UCRANIA", 4);
        myMap.put("AUSTRALIA", 5);
        myMap.put("INDONESIA", 5);
        myMap.put("NOVAZELANDIA", 5);
        myMap.put("PERTH", 5);
    }

    public Territorio(String nome, Jogador jogador) {
        this.nome = nome;
        this.numeroSoldados = 1;
        this.jogador = jogador;
    }

    public int getContinente(){
        System.out.println("Continente: " + this.nome + " | " + myMap.get(this.nome));
        return myMap.get(this.nome);
    }

    public String getNome() {
        return nome;
    }

    public int getNumeroSoldados() {
        return numeroSoldados;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void alteraNumSoldados(int quantidade) {
        this.numeroSoldados += quantidade;
        if (this.numeroSoldados < 0) {
            throw new IllegalArgumentException("Numero de soldados nao pode ser negativo");
        }
    }

    public void setJogador(Jogador outro_jogador) {
        this.jogador = outro_jogador;
    }
}
