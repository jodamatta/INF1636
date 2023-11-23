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

    public void setIsTesteView(boolean flagTeste){
        janelaJogo.setIsTeste(flagTeste);
    }
    public void btnTerritorioController(String nomeTerritorio, boolean isTeste){
        controller.btnTerritorioController(nomeTerritorio, isTeste);
    }

    public void passaFaseView(){
        boolean rodadaInicialFlag = controller.passaFaseController();
        janelaJogo.setLabelPassa(controller.getFaseRodadaController(), rodadaInicialFlag);
       
    }

    public void salvarBttnEnableView() {
    	janelaJogo.setSalvarBttnState(true);
    }
    
    public void salvarBttnDisableView() {
    	janelaJogo.setSalvarBttnState(false);
    }
    
    public void salvarView() {
    	controller.salvarController();
    }
    
    public void ataqueTerritorioView(String nomeTerritorio, List<String> alvos){
        janelaJogo.ataqueAlvos(nomeTerritorio, alvos);
    }
    
    public void setCorJogadorView(){
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

    public List<String> getCartasJogadorAtualView(){
        return controller.getCartasJogadorAtualController();
    }

    public void addExercitoCartaView(){
        controller.addExercitoCartaController();
    }

    public String getObjetivoJogadorAtualView(){
        return controller.getObjetivoJogadorAtualController();
    }

    public void jogoTeste(){
        controller.jogoTesteController();
    }

    public void chamaJanelaFimJogo(String nomeJogador, String corJogador){
        janelaJogo.finalizaJogo(nomeJogador, corJogador);
    }

    public void exibiDados(List<Integer> listDadosAtaque, List<Integer> listDadosDefesa){
        janelaJogo.exibiDados(listDadosAtaque, listDadosDefesa);
    }

    public List<Integer> retornaValoresAtaqueView(){
        return janelaJogo.retornaValoresAtaque();
    }

    public List<Integer> retornaValoresDefesaView(){
        return janelaJogo.retornaValoresDefesa();
    }

    public void mostraSelecaoDadosView(){
        janelaJogo.mostraSelecaoDados(getNumAtacantesView(), getNumAtacantesView());
    }
}
