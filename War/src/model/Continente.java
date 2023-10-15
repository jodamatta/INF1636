package model;
import java.util.List;

class Continente {
    private String nome;
    private List<Territorio> paises;

    public Continente(String nomeCont){
        switch(nomeCont){
            case "AMERICA_DO_NORTE":
                break; 
            case "AMERICA_DO_SUL":
                break; 
            case "AFRICA":
                this.paises.add(0, null);
                break; 
            case "EUROPA":
                break; 
            case "ASIA":
                break; 
            case "OCEANIA":
                break; 
             
        }
    }
}
