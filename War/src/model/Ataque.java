package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Ataque {
    private Jogador atacante;
    private Jogador defensor;
    private Territorio paisDeOrigem = null;
    private Territorio paisAvlo = null;
    private int NumExercitoAtaque = 0;
    private int NumExercitoDefesa = 0;
    private Map<String, List<String>> dictFronteiras = new HashMap<String, List<String>>();

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

    public Ataque(Jogador j){
        this.atacante = j;
    }

    public List<Territorio> getOrigemDisponivel(){
        List<Territorio> territoriosDoAtacante = atacante.getTerritorios();
		List<Territorio> territoriosQuePodemFazerAtaques = territoriosDoAtacante.stream()
                .filter(territorio -> territorio.getNumeroSoldados() > 1)
                .collect(Collectors.toList());
        return territoriosQuePodemFazerAtaques;
        }

    List<String> getAlvos(){
        return dictFronteiras.get(this.paisDeOrigem.getNome());
    }

    public void setAlvo(Territorio t){
        this.paisAvlo = t;
    }

    public Territorio getAlvo(){
        return this.paisAvlo;
    }

    public void setPaisOrigem(Territorio t){
        this.paisDeOrigem = t;
    }

}
