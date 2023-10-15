package model;
import java.util.ArrayList;
import java.util.List;

public class DistribuicaoCartas {
	private GameModel gameModel;

    //Hardcode 3 players for now:
    void hardCodedSetup(){
        GameModel model = GameModel.getInstancia();
        this.gameModel = model;
        String nome1 = "Murilo";
        String nome2 = "Joana";
        String nome3 = "Miguel";
        CorJogador cor1 = CorJogador.Amarelo;
        CorJogador cor2 = CorJogador.Vermelho;
        CorJogador cor3 = CorJogador.Verde;
        
        gameModel.addJogador(nome1,cor1);
        gameModel.addJogador(nome2, cor2);
        gameModel.addJogador(nome3, cor3);

        gameModel.distribuiCartasTerritorio();
        gameModel.inicializaTerritorios();

        gameModel.addExercitoTerritorio(gameModel.getJogadores().get(0));

    }


    void printPlayers(){
        List<Jogador> todosJogadores = gameModel.getJogadores();
		int k = 0;
		for(Jogador jogador : todosJogadores) {
			System.out.println("Jogador " + k + "\nNome: " + jogador.getNome() + " | Cor: " + jogador.getCor() + " | Objetivo: " + jogador.getObjetivo());
            System.out.println("Cartas: ");
			for (Carta carta : jogador.getCartas()){
                System.out.println(carta.getTerritorio() +  "   |   "+ carta.getSimbolo());
            }
            for (Territorio t : jogador.getTerritorios()){
                System.out.println("T: " + t.getNome() + " | Ex num: " + t.getExercito().getNumeroSoldados() + "   | Player:" + t.getExercito().getJogador().getNome());
            }
            k++;
		}
    }

    public static void main(String[] args){
        DistribuicaoCartas DC = new DistribuicaoCartas();
        DC.hardCodedSetup();
        DC.printPlayers();
    }

   
}
