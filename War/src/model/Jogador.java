package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

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
			return 2;
		}
		else if (quadrado >= 3){
			return 3;
		}
		else if (triangulo >= 1 && circulo >= 1 && quadrado >= 1){
			return 4;
		}
		else{
			return 0;
		}
	}

	public void removerCartas(int tipoRemocao) {
        if (tipoRemocao == 1) {
            removerCartasPorSimbolo("TRIANGULO", 3);
        } else if (tipoRemocao == 2) {
            removerCartasPorSimbolo("CIRCULO", 3);
        } else if (tipoRemocao == 3) {
            removerCartasPorSimbolo("QUADRADO", 3);
        } else if (tipoRemocao == 4) {
            removerCartasPorSimbolo("TRIANGULO",1);
            removerCartasPorSimbolo("QUADRADO",1);
            removerCartasPorSimbolo("CIRCULO",1);
        }
    }

    private void removerCartasPorSimbolo(String simbolo, int quantidade) {
        int removidas = 0;
        Iterator<Carta> iterator = cartas.iterator();

        while (iterator.hasNext() && removidas < quantidade) {
            Carta carta = iterator.next();
            if (carta.getSimbolo().equals(simbolo)) {
                iterator.remove();
                removidas++;
            }
        }
    }	
}
