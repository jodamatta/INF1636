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

    public void chamaInicio(){
        new JanelaInicial();
    }

    public void chamaJogo(){
        new JanelaJogo();
    }

    public void continuaJogo(){

    }
}
