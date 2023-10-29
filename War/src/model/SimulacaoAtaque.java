package model;

import java.util.List;
import java.util.Random;

public class SimulacaoAtaque {
    //REMOVER NO FUTURO
    private GameModel gameModel;

    void hardCodedSetup() {
        GameModel model = GameModel.getInstancia();
        // Add 3 jogadores, distribui as cartas e inicializa as tropas:
        this.gameModel = model;
        String nome1 = "Murilo";
        String nome2 = "Joana";
        String nome3 = "Miguel";
        String cor1 = "Amarelo";
        String cor2 = "Vermelho";
        String cor3 = "Verde";
        gameModel.addJogador(nome1, cor1);
        gameModel.addJogador(nome2, cor2);
        gameModel.addJogador(nome3, cor3);
        gameModel.distribuiCartasTerritorio();
        gameModel.inicializaTerritorios();

        // Add tropas aleatoriamente pra simular uma situação de meio de jogo:
        List<Jogador> todosJogadores = gameModel.getJogadores();
        for (Jogador jogador : todosJogadores) {
            for (Territorio t : jogador.getTerritorios()) {

                Random random = new Random();
                int randomNumber = random.nextInt(5);
                t.alteraNumSoldados(randomNumber);

            }
        }
        printTerritorios();
        //Simula uma ação de ataque partindo do jogador 0 - Murilo
        gameModel.ataqueJogador(todosJogadores.get(0));
    }

    void printTerritorios() {
        List<Jogador> todosJogadores = gameModel.getJogadores();
        int k = 0;
        for (Jogador jogador : todosJogadores) {
            System.out.println("\n\nJogador " + k + " - Nome: " + jogador.getNome() + " | Cor: " + jogador.getCor()
                    + " | Objetivo: " + jogador.getObjetivo());
            for (Territorio t : jogador.getTerritorios()) {

                System.out.println("T: " + t.getNome() + " | Ex num: " + t.getNumeroSoldados() + "   | Player:"
                        + t.getJogador().getNome());

            }
            k++;
        }
    }

    public static void main(String[] args) {
        SimulacaoAtaque SimAtaque = new SimulacaoAtaque();
        SimAtaque.hardCodedSetup();
    }
}
