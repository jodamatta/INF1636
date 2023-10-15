package model;

import java.util.ArrayList;
import java.util.List;

class Jogador {
	private CorJogador cor;
	private String nome;
	private ListaObjetivos objetivo;
	private List<Carta> cartas; 
	private List<Territorio> territorios; 

	
	public Jogador( String nome,CorJogador cor) {
		this.cor = cor;
		this.nome = nome;
		this.cartas = new ArrayList<>();
		this.territorios = new ArrayList<>();
	}
	
	public String getNome() { 
		return nome; 
	}
    public CorJogador getCor() { 
    	return cor; 
    }
    public ListaObjetivos getObjetivo() { 
    	return objetivo; 
    }
    public void setObjetivo(ListaObjetivos obj) { 
    	this.objetivo = obj; 
    }

	public void addCarta(Carta carta){
		this.cartas.add(carta);
	}

	public List<Carta> getCartas(){
		return this.cartas;
	}

	public void addTerritorio(Territorio t){
		this.territorios.add(t);
	}

	public List<Territorio> getTerritorios(){
		return this.territorios;
	}

}
