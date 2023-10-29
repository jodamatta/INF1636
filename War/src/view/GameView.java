package view;

public class GameView {
    private static GameView instance = null;

    private GameView(){
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
}
