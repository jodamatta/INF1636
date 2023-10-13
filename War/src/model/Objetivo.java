package model;
import java.util.List;

class Objetivo {
	private ListaObjetivos objetivo;
	
	public Objetivo(ListaObjetivos objetivo) {
		this.objetivo = objetivo;
	}
	public ListaObjetivos getObjetivo() {
		return objetivo;
	}
	
	public boolean objetivoValido(List<Jogador> jogadores) {
		switch(this.objetivo) {
		case ELIM_AZUL:
			return jogadores.stream().anyMatch(p->p.getCor() == CorJogador.Azul);
		case ELIM_VERMELHO:
			return jogadores.stream().anyMatch(p->p.getCor() == CorJogador.Vermelho);
		case ELIM_VERDE:
			return jogadores.stream().anyMatch(p->p.getCor() == CorJogador.Verde);
		case ELIM_AMARELO:
			return jogadores.stream().anyMatch(p->p.getCor() == CorJogador.Amarelo);
		case ELIM_BRANCO:
			return jogadores.stream().anyMatch(p->p.getCor() == CorJogador.Branco);
		case ELIM_PRETO:
			return jogadores.stream().anyMatch(p->p.getCor() == CorJogador.Preto);
		default:
			return true;
		}
	}
	
	// existe para imprimir o nome correto do Objetivo, ao inves da representacao default de um objeto em Java.
	public String toString() {
        return objetivo.name(); 
    }
}
