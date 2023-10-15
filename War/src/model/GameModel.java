package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameModel {
	private static GameModel instance = null;
	private static final int MIN_JOGADORES = 3;
	private static final int MAX_JOGADORES = 6;
	private Scanner scanner;

	// estados do jogo privados
	// exemplo: private List<Territorio> territorios
	private List<Jogador> jogadores = new ArrayList<>();
	private List<Objetivo> objetivosAtivos = new ArrayList<>();
	private List<Carta> CartasTerritorios = new ArrayList<>(); //Miguel
	
	private GameModel() {
		this.scanner = new Scanner(System.in);
	}
	
	public static GameModel getInstancia() {
		if(instance == null) {
			instance = new GameModel();
		}
		return instance;
	}
	
	protected void addJogador(String nome, CorJogador cor) {
		if (jogadores.size()>= MAX_JOGADORES) {
			throw new IllegalStateException("Numero máximo de jogadores atingido.");
		}
		Jogador jogador = new Jogador(nome, cor);
		jogadores.add(jogador);
	}

	protected List<Objetivo> filtraObjetivos() {
		List<Objetivo> objetivos = Arrays.stream(ListaObjetivos.values()).map(Objetivo::new).collect(Collectors.toList());
	
		return objetivos.stream().filter(obj->obj.objetivoValido(jogadores)).collect(Collectors.toList());
	}
	
	protected void sorteiaObjetivos() {
		objetivosAtivos = filtraObjetivos();
		Collections.shuffle(objetivosAtivos);
		for (Jogador jogador:jogadores) {
	        Objetivo objetivoSorteado = objetivosAtivos.remove(0);
	        jogador.setObjetivo(objetivoSorteado.getObjetivo());  // Pass the ListaObjetivos enum
	    }
	}

	protected void distribuiCartasTerritorio() {
		DeckTerritorios DeckTerritorios = new DeckTerritorios();
		int cartasDistribuidas = 0;
		while(cartasDistribuidas < DeckTerritorios.getSize()){
			for (Jogador jogador:jogadores) {
				Carta carta = DeckTerritorios.drawCard();
				 if (carta != null) {
					 jogador.addCarta(carta);
					 cartasDistribuidas++;
				 }
				 else{
					break;
				 }
			}
		}
	}

	protected void inicializaTerritorios(){
		for (Jogador jogador : jogadores){
			for (Carta carta : jogador.getCartas()){
				Territorio territorio = new Territorio(carta.getTerritorio(), jogador);
				jogador.addTerritorio(territorio);
			}
		}
	}

	protected void addExercitoTerritorio(Jogador jogador){
		int Tnumero = jogador.getTerritorios().size();
		int exercitoNumero = Tnumero/2;
		while (exercitoNumero > 0){

			System.out.printf("Voce tem %d exercitos para posicionar e pode posiciona-los nos seguintes territorios:\n",exercitoNumero );
			int k = 0;
			for (Territorio t : jogador.getTerritorios()){
				System.out.println(k + "  |   " + t.getNome() );
				k++;
			}
			System.out.println("Escreva para qual terreno voce deseja adicionar tropas: ");
			int terreno;
			terreno = scanner.nextInt();
			scanner.nextLine(); 
			while (terreno < 0 || terreno > jogador.getTerritorios().size() - 1){
				System.out.println("Terreno inválido. Escolha um terreno entre 0 e " + jogador.getTerritorios().size());
				terreno = scanner.nextInt();
				scanner.nextLine(); 
			}
			System.out.println("Escreva a quantidade de tropas: ");
			int numTropas;
			numTropas = scanner.nextInt();
			scanner.nextLine();
			while (numTropas < 0 || numTropas > exercitoNumero){
				System.out.println("Numero de tropas inválido. Escolha um número entre 0 e " + exercitoNumero);
				terreno = scanner.nextInt();
				scanner.nextLine(); 
			}
			exercitoNumero -= numTropas;
			jogador.getTerritorios().get(terreno).getExercito().adicionarSoldados(numTropas);

		}


	}

	protected void addExercitoContinente(){
		
	}
	
	protected void setOrdemJogada() {
		Collections.shuffle(jogadores);
	}
	
	protected void setPrimeirosExercitos() {
		
	}
	// funcao temporaria para teste
	public List<Jogador> getJogadores(){
		return Collections.unmodifiableList(jogadores);
	}
}
