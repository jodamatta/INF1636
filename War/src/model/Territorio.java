package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Territorio {
    private String nome;
    private int numeroSoldados;
    private int numeroSoldadosCansados;
    private Jogador jogador;
    private Map<String, Integer> myMap = new HashMap<String, Integer>();
    private Map<String, List<String>> dictFronteiras = new HashMap<String, List<String>>();

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

    {

    dictFronteiras.put("ARGENTINA", new ArrayList<>(Arrays.asList(
    "BRASIL", "PERU")));
    dictFronteiras.put("BRASIL", new ArrayList<>(Arrays.asList(
        "ARGENTINA",  "PERU", "VENEZUELA", "NIGERIA"
    )));
    dictFronteiras.put("PERU",new ArrayList<>(Arrays.asList(
        "ARGENTINA",  "BRASIL", "VENEZUELA"
    )));
    dictFronteiras.put("VENEZUELA",new ArrayList<>(Arrays.asList(
        "ARGENTINA",  "BRASIL","PERU", "MEXICO"
    )));
    dictFronteiras.put("MEXICO",new ArrayList<>(Arrays.asList(
        "VENEZUELA", "TEXAS", "CALIFORNIA"
    )));
    dictFronteiras.put("CALIFORNIA",new ArrayList<>(Arrays.asList(
        "TEXAS", "MEXICO", "VANCOUVER"
    )));
    dictFronteiras.put("TEXAS",new ArrayList<>(Arrays.asList(
        "MEXICO", "VANCOUVER", "CALIFORNIA", "NOVAYORK", "QUEBEC"
    )));
    dictFronteiras.put("NOVAYORK",new ArrayList<>(Arrays.asList(
        "QUEBEC", "TEXAS"
    )));
    dictFronteiras.put("NOVAYORK",new ArrayList<>(Arrays.asList(
        "QUEBEC", "TEXAS"
    )));
    dictFronteiras.put("VANCOUVER",new ArrayList<>(Arrays.asList(
        "QUEBEC", "TEXAS", "CALIFORNIA", "ALASCA", "CALGARY"
    )));
    dictFronteiras.put("ALASCA",new ArrayList<>(Arrays.asList(
        "VANCOUVER", "CALGARY", "SIBERIA"
    )));
    dictFronteiras.put("CALGARY",new ArrayList<>(Arrays.asList(
        "ALASCA", "VANCOUVER", "GROELANDIA"
    )));
    dictFronteiras.put("GROELANDIA",new ArrayList<>(Arrays.asList(
        "CALGARY", "QUEBEC", "REINO_UNIDO"
    )));
    dictFronteiras.put("QUEBEC",new ArrayList<>(Arrays.asList(
        "GROELANDIA", "VANCOUVER", "NOVAYORK", "TEXAS"
    )));
    dictFronteiras.put("REINO_UNIDO",new ArrayList<>(Arrays.asList(
        "GROELANDIA", "FRANCA"
    )));
    dictFronteiras.put("ESPANHA",new ArrayList<>(Arrays.asList(
        "FRANCA", "ARGELIA"
    )));
    dictFronteiras.put("FRANCA",new ArrayList<>(Arrays.asList(
        "ESPANHA", "REINO_UNIDO", "ITALIA", "SUECIA"
    )));
    dictFronteiras.put("ITALIA",new ArrayList<>(Arrays.asList(
        "FRANCA", "ARGELIA", "ROMENIA", "SUECIA", "POLONIA"
    )));
    dictFronteiras.put("SUECIA",new ArrayList<>(Arrays.asList(
        "FRANCA", "ITALIA", "ESTONIA"
    )));
    dictFronteiras.put("POLONIA",new ArrayList<>(Arrays.asList(
        "ROMENIA", "ITALIA", "UCRANIA", "LETONIA"
    )));
    dictFronteiras.put("ROMENIA",new ArrayList<>(Arrays.asList(
            "ITALIA", "UCRANIA", "POLONIA", "EGITO"
    )));
    dictFronteiras.put("UCRANIA",new ArrayList<>(Arrays.asList(
            "ROMENIA",  "POLONIA", "LETONIA", "TURQUIA"
    )));
    dictFronteiras.put("EGITO",new ArrayList<>(Arrays.asList(
            "ROMENIA",  "ARGELIA", "JORDANIA", "NIGERIA", "SOMALIA"
    )));
    dictFronteiras.put("ARGELIA",new ArrayList<>(Arrays.asList(
            "ESPANHA", "EGITO","NIGERIA","ITALIA" 
    )));
    dictFronteiras.put("NIGERIA",new ArrayList<>(Arrays.asList(
            "BRASIL", "ARGELIA","EGITO","SOMALIA", "ANGOLA"
    )));
    dictFronteiras.put("ANGOLA",new ArrayList<>(Arrays.asList( 
        "AFRICA_DO_SUL","SOMALIA", "NIGERIA"
    )));
    dictFronteiras.put("AFRICA_DO_SUL",new ArrayList<>(Arrays.asList( 
        "ANGOLA","SOMALIA"
    )));
    dictFronteiras.put("SOMALIA",new ArrayList<>(Arrays.asList( 
        "ANGOLA","AFRICA_DO_SUL", "NIGERIA", "EGITO", "ARABIA_SAUDITA"
    )));
    dictFronteiras.put("ARABIA_SAUDITA",new ArrayList<>(Arrays.asList( 
        "SOMALIA", "JORDANIA", "IRAQUE"
    )));
    dictFronteiras.put("JORDANIA",new ArrayList<>(Arrays.asList( 
        "EGITO", "ARABIA_SAUDITA", "IRAQUE", "SIRIA"
    )));
    dictFronteiras.put("SIRIA",new ArrayList<>(Arrays.asList( 
        "IRAQUE", "JORDANIA", "IRA", "PAQUISTAO", "TURQUIA"
    )));
    dictFronteiras.put("IRAQUE",new ArrayList<>(Arrays.asList( 
        "SIRIA", "JORDANIA", "IRA", "ARABIA_SAUDITA"
    )));
    dictFronteiras.put("IRA",new ArrayList<>(Arrays.asList( 
        "SIRIA", "IRAQUE", "PAQUISTAO"
    )));
    dictFronteiras.put("PAQUISTAO",new ArrayList<>(Arrays.asList( 
        "SIRIA", "IRA", "TURQUIA", "CHINA", "INDIA"
    )));
    dictFronteiras.put("TURQUIA",new ArrayList<>(Arrays.asList( 
        "SIRIA", "PAQUISTAO", "CHINA", "CAZAQUISTAO", "LETONIA", "UCRANIA"
    )));
    dictFronteiras.put("LETONIA",new ArrayList<>(Arrays.asList( 
        "ESTONIA", "RUSSIA", "CAZAQUISTAO", "TURQUIA", "UCRANIA", "POLONIA"
    )));
    dictFronteiras.put("ESTONIA",new ArrayList<>(Arrays.asList( 
        "LETONIA", "RUSSIA", "SUECIA"
    )));
    dictFronteiras.put("RUSSIA",new ArrayList<>(Arrays.asList( 
        "LETONIA", "ESTONIA", "SIBERIA", "CAZAQUISTAO"
    )));
    dictFronteiras.put("SIBERIA",new ArrayList<>(Arrays.asList( 
        "RUSSIA", "CAZAQUISTAO", "ALASCA"
    )));
    dictFronteiras.put("CAZAQUISTAO",new ArrayList<>(Arrays.asList( 
        "RUSSIA", "SIBERIA", "JAPAO", "MONGOLIA", "TURQUIA", "LETONIA", "CHINA"
    )));
    dictFronteiras.put("MONGOLIA",new ArrayList<>(Arrays.asList( 
        "JAPAO", "CAZAQUISTAO", "CHINA"
    )));
    dictFronteiras.put("JAPAO",new ArrayList<>(Arrays.asList( 
        "MONGOLIA", "CAZAQUISTAO", "COREIA_DO_NORTE"
    )));
    dictFronteiras.put("COREIA_DO_NORTE",new ArrayList<>(Arrays.asList( 
        "COREIA_DO_SUL", "CHINA", "JAPAO"
    )));
    dictFronteiras.put("COREIA_DO_SUL",new ArrayList<>(Arrays.asList( 
        "COREIA_DO_NORTE", "CHINA", "INDIA", "BANGLADESH", "TAILANDIA"
    )));
    dictFronteiras.put("CHINA",new ArrayList<>(Arrays.asList( 
        "COREIA_DO_NORTE", "COREIA_DO_SUL", "INDIA", "PAQUISTAO", "TURQUIA", "CAZAQUISTAO", "MONGOLIA"
    )));
    dictFronteiras.put("INDIA",new ArrayList<>(Arrays.asList( 
    "COREIA_DO_SUL", "CHINA", "PAQUISTAO", "BANGLADESH", "INDONESIA"
    )));
    dictFronteiras.put("BANGLADESH",new ArrayList<>(Arrays.asList( 
    "COREIA_DO_SUL", "INDIA", "INDONESIA", "TAILANDIA"
    )));
    dictFronteiras.put("TAILANDIA",new ArrayList<>(Arrays.asList( 
    "COREIA_DO_SUL", "BANGLADESH"
    )));
    dictFronteiras.put("INDONESIA",new ArrayList<>(Arrays.asList( 
    "INDIA", "BANGLADESH", "AUSTRALIA", "NOVAZELANDIA"
    )));
    dictFronteiras.put("NOVAZELANDIA",new ArrayList<>(Arrays.asList( 
    "AUSTRALIA", "INDONESIA"
    )));
    dictFronteiras.put("AUSTRALIA",new ArrayList<>(Arrays.asList( 
    "NOVAZELANDIA", "INDONESIA", "PERTH"
    )));
    dictFronteiras.put("PERTH",new ArrayList<>(Arrays.asList( 
    "AUSTRALIA"
    )));
    }
    
    public Territorio(String nome, Jogador jogador) {
        this.nome = nome;
        this.numeroSoldados = 1;
        this.jogador = jogador;
    }

    public void descansaExercito() {
        this.numeroSoldados = this.numeroSoldadosCansados + this.numeroSoldados;
        this.numeroSoldadosCansados = 0;
    }

    public void addExercitoCansado(int quantidade) {
        this.numeroSoldadosCansados += quantidade;
    }

    public int getContinente(){
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

    public List<String> getVizinhos() {
        return dictFronteiras.get(this.nome);
    }

    public int getNumeroSoldadosCansados() {
        return numeroSoldadosCansados;
    }
    
    public void alteraNumSoldados(int quantidade) {
        this.numeroSoldados += quantidade;
        //if (this.numeroSoldados < 0) {
          //  throw new IllegalArgumentException("Numero de soldados nao pode ser negativo");
        //}
        //Pode ser negativo agora
    }

    public void setJogador(Jogador outro_jogador) {
        this.jogador = outro_jogador;
    }

}
