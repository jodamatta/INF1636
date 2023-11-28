package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Random;
import java.util.Comparator;

class Ataque {
	private int numMaxExercitoAtacante = 3;
    private Jogador atacante;
    private Jogador defensor;
    private Territorio paisDeOrigem = null;
    private Territorio paisAvlo = null;
    private int numExercitoAtaque = 0;
    private int numExercitoDefesa = 0;
    private int atacantesPerdidos = 0;
    private int defensoresPerdidos =0;
    private int exercitosDeslocados =0;
    private int[] dadosAtaque;
    private int[] dadosDefesa;
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

    public Ataque(Jogador atacante){
        this.atacante = atacante;
    }
    
    public void setNumAtacantes(int numAtacantes) {
    	this.numExercitoAtaque = numAtacantes;
    }
    
    public void setNumDefensores(int numDefensores) {
    	this.numExercitoDefesa = numDefensores;
    }
    
    public int getNumDefensores() {
    	return this.numExercitoDefesa;
    }
    
    public void setJogadorDefensor(Jogador defensor) {
    	this.defensor = defensor;
    }
    
    public void setExercitosDeslocados(int n) {
    	this.exercitosDeslocados = n;
    }
    
    public void rolaDados() {
    	Random random = new Random();
    	this.dadosAtaque = new int[this.numExercitoAtaque];
    	this.dadosDefesa = new int[this.numExercitoDefesa];
    	
        for (int i = 0; i < this.numExercitoAtaque; i++) {
            int randomNumber = random.nextInt(6) + 1;
            this.dadosAtaque[i] = randomNumber;
        }
        
        for (int i = 0; i < this.numExercitoDefesa; i++) {
            int randomNumber = random.nextInt(6) + 1;
            this.dadosDefesa[i] = randomNumber;
        } 
        // Convert to Integer array for descending sort
        Integer[] boxedDadosAtaque = Arrays.stream(this.dadosAtaque).boxed().toArray(Integer[]::new);
        Integer[] boxedDadosDefesa = Arrays.stream(this.dadosDefesa).boxed().toArray(Integer[]::new);

        // Sorting in descending order
        Arrays.sort(boxedDadosAtaque, java.util.Collections.reverseOrder());
        Arrays.sort(boxedDadosDefesa, java.util.Collections.reverseOrder());
        // Convert back to int array
        this.dadosAtaque = Arrays.stream(boxedDadosAtaque).mapToInt(Integer::intValue).toArray();
        this.dadosDefesa = Arrays.stream(boxedDadosDefesa).mapToInt(Integer::intValue).toArray();

        System.out.print("Ataque:\n");
        for (int number : dadosAtaque) {
            System.out.print(number + " ");
        }
        System.out.print("\nDefesa:\n");
        for (int number : dadosDefesa) {
            System.out.print(number + " ");
        }
        System.out.print("\n");
    }
    
    public boolean avaliaAtaque() {
    	//Retorna true se o território alvo for dominado
    	//Se não, retorna false e subtrai os exercitos perdidos
    	int avaliacoes = Math.min(numExercitoAtaque, numExercitoDefesa);
    	
    	for (int i = 0; i < avaliacoes; i++) {
    		if (this.dadosAtaque[i] > this.dadosDefesa[i]) {
    			this.defensoresPerdidos += 1;
    		}
    		else {
    			this.atacantesPerdidos +=1;
    		}
    		
    	}
    	System.out.printf("Defensores perdidos %d\n", this.defensoresPerdidos);
    	System.out.printf("Atacantes perdidos %d\n", this.atacantesPerdidos);
    	
    	int tropasNoTerritorioDefesa = this.paisAvlo.getNumeroSoldados();
    	if (defensoresPerdidos >= tropasNoTerritorioDefesa) {
    		return true;
    	}
    	else {
    		this.paisAvlo.alteraNumSoldados(-this.defensoresPerdidos);
    		this.paisDeOrigem.alteraNumSoldados(-this.atacantesPerdidos);
    		return false;
    	}
    	
    	
    }
    
    public void conquistaAndDeslocamento() {
		this.paisAvlo.alteraNumSoldados(-this.defensoresPerdidos);
		this.paisAvlo.setJogador(this.atacante);
        this.atacante.addTerritorio(paisAvlo);
        this.defensor.removeTerritorio(paisAvlo);
		this.paisDeOrigem.alteraNumSoldados(-this.exercitosDeslocados);
		this.paisAvlo.alteraNumSoldados(this.exercitosDeslocados);
    }
   
    public List<Territorio> getOrigemDisponivel(){
        List<Territorio> territoriosDoAtacante = atacante.getTerritorios();
		List<Territorio> territoriosQuePodemFazerAtaques = territoriosDoAtacante.stream()
                .filter(territorio -> territorio.getNumeroSoldados() > 1)
                .collect(Collectors.toList());
        return territoriosQuePodemFazerAtaques;
        }

    List<String> getAlvos(){
    	List<String> nomesDosTerritoriosDoAtacante = atacante.getTerritorios().stream()
    			.map(Territorio::getNome)  
    			.collect(Collectors.toList());
    	 
    	List<String> fronteira = dictFronteiras.get(this.paisDeOrigem.getNome());
    	
    	List<String> fronteiraFiltrada = fronteira.stream()
                .filter(pais -> !nomesDosTerritoriosDoAtacante.contains(pais))
                .collect(Collectors.toList());
        return fronteiraFiltrada;
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
    
    public List<Integer> getDadosAtaque() {
    	List<Integer> listDadosAtaque = new ArrayList<>();
        for (int valor : dadosAtaque) {
            listDadosAtaque.add(valor);
        }
        return listDadosAtaque;
    }

    public List<Integer> getDadosDefesa() {
    	List<Integer> listDadosDefesa = new ArrayList<>();
        for (int valor : dadosDefesa) {
            listDadosDefesa.add(valor);
        }
        return listDadosDefesa;
    }

    public void setDadosAtaque(List<Integer> dadosAtaque){
        int[] arrayDados = dadosAtaque.stream().mapToInt(Integer::intValue).toArray();
        this.dadosAtaque = arrayDados;
    }

    public void setDadosDefesa(List<Integer> dadosDefesa){
        int[] arrayDados = dadosDefesa.stream().mapToInt(Integer::intValue).toArray();
        this.dadosDefesa = arrayDados;
    }
    
    public Jogador getAtacante() {
    	return this.atacante;
    }
}
