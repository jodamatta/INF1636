package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import view.GameView;

public class GameModel {
	private static GameModel instance = null;
	private static GameView instance_view;
	private static final int MAX_JOGADORES = 6;
	private Scanner scanner;
	private int numTrocas = 0;
	private int numJogadorAtual = 0;
	private DeckTerritorios deckCartas = new DeckTerritorios();

	// estados do jogo privados
	private static List<Jogador> jogadores = new ArrayList<>();
	private static List<Territorio> territorios = new ArrayList<>();
	private List<Objetivo> objetivosAtivos = new ArrayList<>();
	private List<Continente> continentes = new ArrayList<>(Arrays.asList(
		new Continente("Africa"),
		new Continente("America do Norte"),
		new Continente("Asia"),
		new Continente("America do Sul"),
		new Continente("Europa"),
		new Continente("Oceania")
	)); 
	
	private GameModel() {
		this.scanner = new Scanner(System.in);
	}
	
	public static GameModel getInstancia() {
		if(instance == null) {
			instance = new GameModel();
		}
		return instance;
	}
	
	public int getMaxJogadores() {
		return MAX_JOGADORES;
	}
	
	public void addJogador(String nome, String cor) {
		if (jogadores.size()>= MAX_JOGADORES) {
			throw new IllegalStateException("Numero máximo de jogadores atingido.");
		}
		CorJogador color = CorJogador.valueOf(cor);
		Jogador jogador = new Jogador(nome, color);
		jogadores.add(jogador);
	}
	
	public String[] getCores() {
		return Arrays.stream(CorJogador.values()).map(Enum::name).toArray(String[]::new);
	}

	public boolean objetivoValido(ListaObjetivos objetivo, List<Jogador> jogadores) {
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

	public List<Objetivo> filtraObjetivos() {
        List<Objetivo> objetivos = Arrays.stream(ListaObjetivos.values()).map(Objetivo::new).collect(Collectors.toList());
        return objetivos.stream().filter(obj -> this.objetivoValido(obj.getObjetivo(), jogadores)).collect(Collectors.toList());
    }

	public void sorteiaObjetivos() {
        objetivosAtivos = filtraObjetivos();
        Collections.shuffle(objetivosAtivos);
        for (Jogador jogador:jogadores) {
            Objetivo objetivoSorteado = objetivosAtivos.remove(0);
            jogador.setObjetivo(objetivoSorteado.getObjetivo());  
        }
    }

	public void distribuiCartasTerritorio() {
		DeckTerritorios deckTerritoriosInical = new DeckTerritorios();
		int cartasDistribuidas = 0;
		while(cartasDistribuidas < 51){
			for (Jogador jogador:jogadores) {
				Carta carta = deckTerritoriosInical.drawCard();
				 if (carta == null) {
					 break;
				 }
				jogador.addCarta(carta);
				cartasDistribuidas++;
			}
		}
	}

	public void inicializaTerritorios(){
		for (Jogador jogador : jogadores){
			for (Carta carta : jogador.getCartas()){
				Territorio territorio = new Territorio(carta.getTerritorio(), jogador);
				territorios.add(territorio);
				jogador.addTerritorio(territorio);
				continentes.get(territorio.getContinente()).addPais(territorio);
			}
		}
	}

	public void addExercitoTerritorio(Jogador jogador){
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

	public void addExercitoContinente(Jogador jogador){
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
	
	public int querTrocar(Jogador jogador){
		if( jogador.verificaTroca() != 0){
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

	public void addExercitoCarta(){
		Jogador jogador = jogadores.get(numJogadorAtual);
		if(jogador.verificaTroca() == 0){
			return;
		}
		jogador.removerCartas(jogador.verificaTroca(), deckCartas);
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

	public void setOrdemJogada() {
		Collections.shuffle(jogadores);
	}

	public static List<Jogador> getJogadores(){
		return Collections.unmodifiableList(jogadores);
	}
	
	public String getNomeJogadorAtual(){
		return jogadores.get(numJogadorAtual).getNome();
	}

	public String getCorJogadorAtual(){
		return jogadores.get(numJogadorAtual).getCor().toString();
	}

	public void passaVez(){
		numJogadorAtual++;
		if (numJogadorAtual == jogadores.size()){
			numJogadorAtual = 0;
		}
	}

	public List<String> getTerritoriosJogadorAtual(){
		return jogadores.get(numJogadorAtual).getTerritorios().stream().map(Territorio::getNome).collect(Collectors.toList());
	}

	public List<String> getCartasJogadorAtual(){
		return jogadores.get(numJogadorAtual).getCartas().stream().map(Carta::getTerritorio).collect(Collectors.toList());
	}
	
	public void printPlayers() {
		System.out.println("Lista de jogadores:");
	    for (Jogador player : jogadores) {
	        System.out.println("Nome: " + player.getNome() + ", Cor: " + player.getCor().name());
	    }
	}

	public List<Continente> getContinentes(){
		return Collections.unmodifiableList(continentes);
	}

	public void ataqueJogador(Jogador j){
		Ataque att = new Ataque(j);
		List<Territorio> territoriosQuePodemFazerAtaques = att.getOrigemDisponivel();
		System.out.println(j.getNome() + ", você pode fazer um ataque a partir dos seguintes territórios:" );
		int k = 0;
		int Tnumero = territoriosQuePodemFazerAtaques.size();
		for (Territorio t : territoriosQuePodemFazerAtaques){
			System.out.println(k + " | " + t.getNome());
			k++;
		}
		System.out.println("Selecine o territorio: ");
		int terreno;
		terreno = scanner.nextInt();
		scanner.nextLine();
		while (terreno < 0 || terreno > Tnumero - 1){
						System.out.println("territorio inválido. Escolha um territorio entre 0 e " + (Tnumero-1));
						terreno = scanner.nextInt();
						scanner.nextLine(); 
					}
		Territorio paisOrigem = territoriosQuePodemFazerAtaques.get(terreno);
		att.setPaisOrigem(paisOrigem);
		System.out.println("Voce escolheu atacar a partir de " + paisOrigem.getNome() );
		System.out.println("Alvos disponiveis: ");
		int l = 0;
		List <String> listaAlvo = att.getAlvos();
		for (String alvo : listaAlvo){
			System.out.println(l + " | " + alvo);
			l++;
		}
		System.out.println("Selecine o alvo: ");
		int alvo;
		alvo = scanner.nextInt();
		scanner.nextLine();
		for (Territorio t : territorios){
			if (listaAlvo.get(alvo) == t.getNome()){
				att.setAlvo(t);
			}
		}
		Territorio Talvo = att.getAlvo();
		if (Talvo != null){
			System.out.println("Alvo: " + Talvo.getNome() + " | " + "Sob controle de : " + Talvo.getJogador().getNome() + " | " + "Número de defensores: " + Talvo.getNumeroSoldados());
		}


	}

	public static void resetInstancia() {
        instance = null;
    }

	public void hardStartGame(){
		instance_view = GameView.getInstanciaView();
		GameModel gameInstance = GameModel.getInstancia();	
		gameInstance.addJogador("Joana", "Azul");
		gameInstance.addJogador("Miguel", "Verde");
		gameInstance.addJogador("Murilo", "Vermelho");
		beginGame();
	}
	
	public void startGame(){
		instance_view = GameView.getInstanciaView();	
		instance_view.chamaInicio();
	}

	public void beginGame(){
		sorteiaObjetivos();
		distribuiCartasTerritorio();
		inicializaTerritorios();
		setOrdemJogada();
		
		instance_view.chamaJogo();
	}

	public String getTerritorioCor(String nomeTerritorio){
		for (Territorio t : territorios){
			if (t.getNome() == nomeTerritorio){
				return t.getJogador().getCor().toString();
			}
		}
		return null;
	}

	public int getNumSoldados(String nomeTerritorio){
		for (Territorio t : territorios){
			if (t.getNome() == nomeTerritorio){
				return t.getNumeroSoldados();
			}
		}
		return 0;
	}

	public String getObjetivoJogadorAtual(){
		return jogadores.get(numJogadorAtual).getObjetivo().toString();
	}

	public void hardCodedSetup() {
        // Add 3 jogadores, distribui as cartas e inicializa as tropas:
        String nome1 = "Murilo";
        String nome2 = "Joana";
        String nome3 = "Miguel";
        String cor1 = "Amarelo";
        String cor2 = "Vermelho";
        String cor3 = "Verde";
        instance.addJogador(nome1, cor1);
        instance.addJogador(nome2, cor2);
        instance.addJogador(nome3, cor3);
        instance.distribuiCartasTerritorio();
        instance.inicializaTerritorios();
		
        // Add tropas aleatoriamente pra simular uma situação de meio de jogo:
        List<Jogador> todosJogadores = instance.getJogadores();

		for (Jogador jogador : todosJogadores){
			jogador.limpaMao();
		}

        for (Jogador jogador : todosJogadores) {
            for (Territorio t : jogador.getTerritorios()) {

                Random random = new Random();
                int randomNumber = random.nextInt(5);
                t.alteraNumSoldados(randomNumber);

            }
        }

		Jogador jogador1 = todosJogadores.get(0);
		jogador1.addCarta(deckCartas.drawCard());
		jogador1.addCarta(deckCartas.drawCard());
		jogador1.addCarta(deckCartas.drawCard());
		jogador1.addCarta(deckCartas.drawCard());
		jogador1.addCarta(deckCartas.drawCard());
		jogador1.addCarta(deckCartas.drawCard());
		jogador1.addCarta(deckCartas.drawCard());
    }
	public static void main(String[] args){
		GameModel gameInstance = GameModel.getInstancia();
		System.out.println('a');
		//gameInstance.hardStartGame();
		gameInstance.startGame();
	}

}
