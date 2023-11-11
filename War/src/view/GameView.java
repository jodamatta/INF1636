package view;
import controller.GameController;

public class GameView {
    private static GameView instance = null;
    private GameController controller;

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
        new JanelaJogo();
    }

    public void continuaJogo(){

    }
}
