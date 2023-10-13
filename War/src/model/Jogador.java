package model;

class Jogador {
	private CorJogador cor;
	private String nome;
	private ListaObjetivos objetivo;
	
	public Jogador( String nome,CorJogador cor) {
		this.cor = cor;
		this.nome = nome;
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
}
