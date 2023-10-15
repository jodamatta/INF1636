package model;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SetupPreJogo {
	private GameModel gameModel;
	private Scanner scanner;
	private List<CorJogador> coresDisponiveis;
	
	public SetupPreJogo(GameModel gameModel) {
		this.gameModel = gameModel;
		this.scanner = new Scanner(System.in);
		this.coresDisponiveis = new ArrayList<>(Arrays.asList(CorJogador.values()));
	}
	
	public void startSetup() {
		System.out.println("Bem-vindo ao War!");
		
		int numJogadores;
		do {
			System.out.println("Digite o número de jogadores (3-6): ");
			numJogadores = scanner.nextInt();
			scanner.nextLine();
			if (numJogadores < 3 || numJogadores > 6) {
				System.out.println("Número de jogadores inválido. Digite novamente.");
			}
		} while (numJogadores < 3 || numJogadores > 6);
		
		for (int i = 0; i < numJogadores; i++) {
			System.out.print("Nome do jogador " + i + ": ");
            String nomeJogador = scanner.nextLine();
            
            System.out.println("Cores disponíveis: ");
            for (int j = 0; j < coresDisponiveis.size(); j++) {
                System.out.println(j + ". " + coresDisponiveis.get(j));
            }
            int corEscolhida;
            do {
                System.out.print("Escolha uma cor pelo número: ");
                corEscolhida = scanner.nextInt();
                scanner.nextLine(); 
                if (corEscolhida < 0 || corEscolhida >= CorJogador.values().length) {
    				System.out.println("Cor inválida. Digite novamente.");
    			}
            } while (corEscolhida < 0 || corEscolhida >= CorJogador.values().length);
            CorJogador cor = coresDisponiveis.get(corEscolhida);
            coresDisponiveis.remove(cor);
            
            gameModel.addJogador(nomeJogador, cor);
            System.out.println("\nLista de Jogadores:");
            List<Jogador> todosJogadores = gameModel.getJogadores();
            for (Jogador jogador : todosJogadores) {
                System.out.println("Nome: " + jogador.getNome() + " | Cor: " + jogador.getCor());
            }
        }
		gameModel.sorteiaObjetivos();
		gameModel.setOrdemJogada();
		List<Jogador> todosJogadores = gameModel.getJogadores();
		int k = 0;
		for(Jogador jogador : todosJogadores) {
			System.out.println("Jogador " + k + "\nNome: " + jogador.getNome() + " | Cor: " + jogador.getCor() + " | Objetivo: " + jogador.getObjetivo());
			k++;
		}
		
	}
	public static void main(String[] args) {
    	GameModel model = GameModel.getInstancia();
    	SetupPreJogo setup = new SetupPreJogo(model);
    	setup.startSetup();
    }
}
