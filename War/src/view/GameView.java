package view;
import controller.GameController;
import java.util.List;

public class GameView {
    private static GameView instance = null;
    private GameController controller;
    private JanelaJogo janelaJogo;

    private GameView(){
        controller = GameController.getInstanciaController();
    }

    public JanelaInicial getJanelaInicial(){
        return new JanelaInicial();
    }

    public GameController getController(){
        return controller;
    }

    public static GameView getInstanciaView() {
		if(instance == null) {
			instance = new GameView();
		}
		return instance;
	}

    public String[] getCoresView(){
        return controller.getCoresController();
    }

    public void addJogadorView(String nomeJogador, String corJogador){
        controller.addJogadorControler(nomeJogador, corJogador);
    }    

    public int getNumSoldadosView(String nomeTerritorio){
        return controller.getNumSoldadosController(nomeTerritorio);
    }

    public String getTerritorioCorView(String nomeTerritorio){
        return controller.getTerritorioCorController(nomeTerritorio);
    }

    public void chamaJanelaInicio(){
        new JanelaInicial();
    }

    public void inciaJogo(){
        controller.beginGameController();
    }

    public void chamaJanelaJogo(){
        janelaJogo = new JanelaJogo();
    }

    public void atualizaNumSoldadosView(String nomeTerritorio){
        janelaJogo.atualizaNumSoldados(nomeTerritorio);

    }

    public void atualizaDonoView(String nomeTerritorio, String nomeJogador){
        janelaJogo.atualizaDono(nomeTerritorio, nomeJogador);
    }

    public void voltaTerrioriosView(){
        janelaJogo.voltaTerriorios();
    }

    public String getCorJogadorAtualView(){
        return controller.getCorJogadorAtualController();
    }

    public int getNumSoldadosDisponiveisView(){
        return controller.getNumSoldadosDisponiveisController();
    }
    
    public void continuaJogo(){

    }

    public void btnTerritorioController(String nomeTerritorio){
        controller.btnTerritorioController(nomeTerritorio);
    }

    public void passaFaseView(){
        controller.passaFaseController();
    }

    public void ataqueTerritorioView(String nomeTerritorio, List<String> alvos){
        janelaJogo.ataqueAlvos(nomeTerritorio, alvos);
    }
    
    public void passaVezView(){
        janelaJogo.setCorJogadorbtn();
    }

    public int getNumAtacantesView(){
        return janelaJogo.getNumAtacantes();
    }

    public String atualizaCor(String nomeTerritorio){
        return controller.getTerritorioCorController(nomeTerritorio);
        
    }
    
    public void btnAtaqueView(String nomeTerritorio){
        controller.btnAtaqueController(nomeTerritorio);
    }

    public int getNumMovimentoView(){
        return janelaJogo.getNumMovimento();
    }
    public void btnMoverView(String nomeTerritorio){
        controller.btnMoverController(nomeTerritorio);
    }

    public void atualizaBtnAtaque(String nomeTerritorio, int numExercitosAtacantes){
        janelaJogo.atualizaBtnAtaque(numExercitosAtacantes);
    }

    public void atualizaBtnMover(String nomeTerritorio, int numExercitosMovimento){
        janelaJogo.atualizaBtnMover(numExercitosMovimento);
    }
    public void movimentoTerritorioView(String nomeTerritorio, List<String> vizinhos){
        janelaJogo.movimentoTerritorio(nomeTerritorio, vizinhos);
    }

}
