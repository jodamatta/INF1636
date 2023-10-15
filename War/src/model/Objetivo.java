package model;

class Objetivo {
	private ListaObjetivos objetivo;
	
	public Objetivo(ListaObjetivos objetivo) {
		this.objetivo = objetivo;
	}
	
	public ListaObjetivos getObjetivo() {
		return objetivo;
	}
	
	// existe para imprimir o nome correto do Objetivo, ao inves da representacao default de um objeto em Java.
	public String toString() {
        return objetivo.name(); 
    }

}
