package model;
import java.util.List;
import java.util.ArrayList;

class Continente {
    private String nome;
    private List<Territorio> paises;

    public Continente(String nomeCont){
        this.nome = nomeCont;
        this.paises = new ArrayList<>();
    }

    public String getNome(){
        return this.nome;
    }  

    public void addPais(Territorio pais){
        this.paises.add(pais);
    }

    public List<Territorio> getPaises(){
        return this.paises;
    }
    
    public void printPaises(){
        for(Territorio pais : this.paises){
            System.out.println(pais.getNome());
        }
    }

    public int getBonus(){
        switch(this.nome){
            case "Africa":
                return 3;
            case "America do Norte":
                return 5;
            case "Asia":
                return 7;
            case "America do Sul":
                return 2;
            case "Europa":
                return 5;
            case "Oceania":
                return 2;
            default:
                return 0;
        }
    }

    public int foiDominado(Jogador j){
        for(Territorio pais : this.paises){
            if(pais.getJogador().equals(j)){
                //code block
            }else{
                return 0;
            }
        }
        return 1;
    }
}
