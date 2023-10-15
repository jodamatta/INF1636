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

	public void removeTerritorio(Territorio t){
		this.territorios.remove(t);
	}

	public void removeCarta(Carta c){
		this.cartas.remove(c);
	}

	public int verificaTroca(){
		int triangulo = 0;
		int circulo = 0;
		int quadrado = 0;

		for (Carta carta : this.cartas){
			if (carta.getSimbolo() == "TRIANGULO"){
				triangulo++;
			}
			else if (carta.getSimbolo() == "CIRCULO"){
				circulo++;
			}
			else if (carta.getSimbolo() == "QUADRADO"){
				quadrado++;
			}
		}
		
		if (triangulo >= 3){
			return 1;
		}
		else if (circulo >= 3){
			return 1;
		}
		else if (quadrado >= 3){
			return 1;
		}
		else if (triangulo >= 1 && circulo >= 1 && quadrado >= 1){
			return 1;
		}
		else{
			return 0;
		}
	}
}
