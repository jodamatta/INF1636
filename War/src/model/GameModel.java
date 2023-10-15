package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameModel {
	private static GameModel instance = null;
	private static final int MAX_JOGADORES = 6;
	private Scanner scanner;
	private int numTrocas = 0;

	// estados do jogo privados
	// exemplo: private List<Territorio> territorios
	private List<Jogador> jogadores = new ArrayList<>();
	private List<Objetivo> objetivosAtivos = new ArrayList<>();
	private List<Continente> continentes = new ArrayList<>(Arrays.asList(
		new Continente("Africa"),
		new Continente("America do Norte"),
		new Continente("Asia"),
		new Continente("America do Sul"),
		new Continente("Europa"),
		new Continente("Oceania")
	)); 
	
	List<String> myList = new ArrayList<>(Arrays.asList("element1", "element2", "element3", "element4", "element5"));
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

	protected boolean objetivoValido(ListaObjetivos objetivo, List<Jogador> jogadores) {
        switch(objetivo) {
            case ELIM_AZUL:
                return jogadores.stream().anyMatch(p -> p.getCor() == CorJogador.Azul);
            case ELIM_VERMELHO:
                return jogadores.stream().anyMatch(p -> p.getCor() == CorJogador.Vermelho);
            case ELIM_VERDE:
                return jogadores.stream().anyMatch(p -> p.getCor() == CorJogador.Verde);
            case ELIM_AMARELO:
                return jogadores.stream().anyMatch(p -> p.getCor() == CorJogador.Amarelo);
            case ELIM_BRANCO:
                return jogadores.stream().anyMatch(p -> p.getCor() == CorJogador.Branco);
            case ELIM_PRETO:
                return jogadores.stream().anyMatch(p -> p.getCor() == CorJogador.Preto);
            default:
                return true;
        }
    }

    protected List<Objetivo> filtraObjetivos() {
        List<Objetivo> objetivos = Arrays.stream(ListaObjetivos.values()).map(Objetivo::new).collect(Collectors.toList());
        return objetivos.stream().filter(obj -> this.objetivoValido(obj.getObjetivo(), jogadores)).collect(Collectors.toList());
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
		while(cartasDistribuidas < 51){
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
				continentes.get(territorio.getContinente()).addPais(territorio);
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
			while (terreno < 0 || terreno > Tnumero - 1){
				System.out.println("Terreno inválido. Escolha um terreno entre 0 e " + (Tnumero-1));
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
			jogador.getTerritorios().get(terreno).alteraNumSoldados(numTropas);;
		}
	}

	protected void addExercitoContinente(Jogador jogador){
		for(Continente c : continentes){
			if(c.foiDominado(jogador) == 1){
				int Tnumero = c.getPaises().size();
				int exercitoNumero = c.getBonus();
				while (exercitoNumero > 0){
					System.out.printf("Voce tem %d exercitos para posicionar e pode posiciona-los nos seguintes territorios:\n",exercitoNumero );
					int k = 0;
					for (Territorio t : c.getPaises()){
						System.out.println(k + "  |   " + t.getNome() );
						k++;
					}
					System.out.println("Escreva para qual terreno voce deseja adicionar tropas: ");
					int terreno;
					terreno = scanner.nextInt();
					scanner.nextLine(); 
					while (terreno < 0 || terreno > Tnumero - 1){
						System.out.println("Terreno inválido. Escolha um terreno entre 0 e " + (Tnumero-1));
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
					c.getPaises().get(terreno).alteraNumSoldados(numTropas);;
				}
			}
		}
	}
	
	protected int querTrocar(Jogador jogador){
		if( jogador.verificaTroca() == 1){
			System.out.println("Voce tem uma troca valida. Voce quer trocar? (S/N)");
			String resposta;
			resposta = scanner.nextLine();
			while (!resposta.equals("S") && !resposta.equals("N")){
				System.out.println("Resposta inválida. Escolha S ou N");
				resposta = scanner.nextLine();
			}
			if(resposta.equals("S")){
				return 1;
			}
		}
		return 0;
	}

	protected void addExercitoCarta(Jogador jogador){
		if(querTrocar(jogador) != 1){
			return;
		}
		//Remove as cartas (implementar)
		int Tnumero = jogador.getTerritorios().size();
		int exercitoNumero = 5*numTrocas;
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
			while (terreno < 0 || terreno > Tnumero - 1){
				System.out.println("Terreno inválido. Escolha um terreno entre 0 e " + (Tnumero - 1));
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
			jogador.getTerritorios().get(terreno).alteraNumSoldados(numTropas);;
		}

	}

	protected void setOrdemJogada() {
		Collections.shuffle(jogadores);
	}
	
	protected void setPrimeirosExercitos() {
		
	}
	// funcao temporaria para teste
	protected List<Jogador> getJogadores(){
		return Collections.unmodifiableList(jogadores);
	}

	protected List<Continente> getContinentes(){
		return Collections.unmodifiableList(continentes);
	}

	protected static void resetInstancia() {
        instance = null;
    }


}
