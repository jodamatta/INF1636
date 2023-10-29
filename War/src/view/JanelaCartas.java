package view;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.GameModel;

public class JanelaCartas extends Frame {
    private GameModel gameModel;
    Map<String,  String> dictPath = new HashMap<>();
    Map<String, String> dicionario = new HashMap<>();
    Map<String, String> dictObejetivos = new HashMap<>();
    private Button btnTrocaCartas;

    {
    dicionario.put("AFRICA_DO_SUL", "war_carta_af_africadosul.png");
    dicionario.put("ANGOLA", "war_carta_af_angola.png");
    dicionario.put("ARGELIA", "war_carta_af_argelia.png");
    dicionario.put("EGITO", "war_carta_af_egito.png");
    dicionario.put("NIGERIA", "war_carta_af_nigeria.png");
    dicionario.put("SOMALIA", "war_carta_af_somalia.png");
    dicionario.put("ALASCA", "war_carta_an_alasca.png");
    dicionario.put("CALGARY", "war_carta_an_calgary.png");
    dicionario.put("CALIFORNIA", "war_carta_an_california.png");
    dicionario.put("GROELANDIA", "war_carta_an_groelandia.png");
    dicionario.put("MEXICO", "war_carta_an_mexico.png");
    dicionario.put("NOVAYORK", "war_carta_an_novayork.png");
    dicionario.put("QUEBEC", "war_carta_an_quebec.png");
    dicionario.put("TEXAS", "war_carta_an_texas.png");
    dicionario.put("VANCOUVER", "war_carta_an_vancouver.png");
    dicionario.put("ARGENTINA", "war_carta_asl_argentina.png");
    dicionario.put("BRASIL", "war_carta_asl_brasil.png");
    dicionario.put("PERU", "war_carta_asl_peru.png");
    dicionario.put("VENEZUELA", "war_carta_asl_venezuela.png");
    dicionario.put("ARABIA_SAUDITA", "war_carta_as_arabiasaudita.png");
    dicionario.put("BANGLADESH", "war_carta_as_bangladesh.png");
    dicionario.put("CAZAQUISTAO", "war_carta_as_cazaquistao.png");
    dicionario.put("CHINA", "war_carta_as_china.png");
    dicionario.put("COREIA_DO_NORTE", "war_carta_as_coreiadonorte.png");
    dicionario.put("COREIA_DO_SUL", "war_carta_as_coreiadosul.png");
    dicionario.put("ESTONIA", "war_carta_as_estonia.png");
    dicionario.put("INDIA", "war_carta_as_india.png");
    dicionario.put("IRA", "war_carta_as_ira.png");
    dicionario.put("IRAQUE", "war_carta_as_iraque.png");
    dicionario.put("JAPAO", "war_carta_as_japao.png");
    dicionario.put("JORDANIA", "war_carta_as_jordania.png");
    dicionario.put("LETONIA", "war_carta_as_letonia.png");
    dicionario.put("MONGOLIA", "war_carta_as_mongolia.png");
    dicionario.put("PAQUISTAO", "war_carta_as_paquistao.png");
    dicionario.put("RUSSIA", "war_carta_as_russia.png");
    dicionario.put("SIBERIA", "war_carta_as_siberia.png");
    dicionario.put("SIRIA", "war_carta_as_siria.png");
    dicionario.put("TAILANDIA", "war_carta_as_tailandia.png");
    dicionario.put("TURQUIA", "war_carta_as_turquia.png");
    dicionario.put("ESPANHA", "war_carta_eu_espanha.png");
    dicionario.put("FRANCA", "war_carta_eu_franca.png");
    dicionario.put("ITALIA", "war_carta_eu_italia.png");
    dicionario.put("POLONIA", "war_carta_eu_polonia.png");
    dicionario.put("REINO_UNIDO", "war_carta_eu_reinounido.png");
    dicionario.put("ROMENIA", "war_carta_eu_romenia.png");
    dicionario.put("SUECIA", "war_carta_eu_suecia.png");
    dicionario.put("UCRANIA", "war_carta_eu_ucrania.png");
    dicionario.put("AUSTRALIA", "war_carta_oc_australia.png");
    dicionario.put("INDONESIA", "war_carta_oc_indonesia.png");
    dicionario.put("NOVAZELANDIA", "war_carta_oc_novazelandia.png");
    dicionario.put("PERTH", "war_carta_oc_perth.png");
    }
    
    public JanelaCartas() {
        gameModel = GameModel.getInstancia();
        initUI();
    }

    private void initUI() {
    	String[] listaPaises = criaListaPaises();
    	preencheDictPath(listaPaises);
        setTitle("Janela Cartas");
        setSize(1200, 700);
        setLayout(null);
        List<String> nomesCatas = gameModel.getCartasJogadorAtual();
        int offSetX = 0;
        int offsetY = 0;
        for (String nome : nomesCatas) {        	
        	JLabel label = new JLabel();
        	label.setBounds(0 + offSetX, 0+offsetY, 160, 300);
        	label.setIcon(new ImageIcon(dictPath.get(nome)));
        	add(label);
        	offSetX += 150;
        	if (offSetX > 1500) {
        		offSetX = 0;
        		offsetY += 300;
        	}
        	
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        btnTrocaCartas = new Button("Trocar Cartas");
        btnTrocaCartas.setBounds(1070, 640, 120, 30); // Define posição e tamanho do botão
        btnTrocaCartas.addActionListener(e -> {gameModel.addExercitoCarta(); dispose();});
        add(btnTrocaCartas);
        displayObjetivo();
        setVisible(true);
    }

    private String[] splitCountries(String input) {
        String[] countries = input.split(",\\s*");
        return countries;
    }
    
    private String[] criaListaPaises() {
    	 String countriesList = "AFRICA_DO_SUL, ANGOLA, ARGELIA, EGITO, NIGERIA, SOMALIA, ALASCA, CALGARY, CALIFORNIA, "
    	 		+ "GROELANDIA, MEXICO, NOVAYORK, QUEBEC, TEXAS, VANCOUVER, ARGENTINA, BRASIL, "
    	 		+ "PERU, VENEZUELA, ARABIA_SAUDITA, "
    	 		+ "BANGLADESH, CAZAQUISTAO, CHINA, COREIA_DO_NORTE, COREIA_DO_SUL, ESTONIA, INDIA, IRA, IRAQUE, JAPAO, JORDANIA, LETONIA, "
    	 		+ "MONGOLIA, PAQUISTAO, RUSSIA, SIBERIA, SIRIA, TAILANDIA, TURQUIA, ESPANHA, FRANCA, ITALIA, POLONIA, REINO_UNIDO, ROMENIA, "
    	 		+ "SUECIA, UCRANIA, AUSTRALIA, INDONESIA, NOVAZELANDIA, PERTH";
    	 String[] countryArray = splitCountries(countriesList);
    	 return countryArray;
    }
    
    private void preencheDictPath(String [] arr) {
    	 String basePath = "C:\\Users\\miguel.batista_bigda\\Documents\\GitHub\\programacao_orientada_a_objetos\\War\\src\\view\\images\\";
    	    for (int i = 0; i < arr.length; i++) {
    	        String countryName = arr[i];
    	        String path = basePath + dicionario.get(countryName);
    	        dictPath.put(countryName, path);
    	    }
    	    
    	    
    }
    public static void main(String[] args) {
        new JanelaCartas();
    }

    {
        dictObejetivos.put("CONQ_24", "Conquistar 24 territorios");
        dictObejetivos.put("CONQ_18_2", "Conquistar 18 territorios, cada um com no minimo 2 exercitos");
        dictObejetivos.put("CONQ_AS_LATAM", "Conquistar Asia e America do Sul");
        dictObejetivos.put("CONQ_NA_AF", "Conquistar America do Norte e Africa");
        dictObejetivos.put("CONQ_NA_AU", "Conquistar America do Norte e Australasia");
        dictObejetivos.put("CONQ_EU_LATAM", "Conquistar Europa e America do Sul");
        dictObejetivos.put("CONQ_EU_AU", "Conquistar Europa e Australasia");
        dictObejetivos.put("ELIM_AZUL", "Eliminar o jogador azul");
        dictObejetivos.put("ELIM_VERMELHO", "Eliminar o jogador vermelho");
        dictObejetivos.put("ELIM_AMARELO", "Eliminar o jogador amarelo");
        dictObejetivos.put("ELIM_VERDE", "Eliminar o jogador verde");
        dictObejetivos.put("ELIM_BRANCO", "Eliminar o jogador branco");
        dictObejetivos.put("ELIM_PRETO", "Eliminar o jogador preto");
    }

    private void displayObjetivo(){
        String objetivo = "Objetivo: " + dictObejetivos.get(gameModel.getObjetivoJogadorAtual());
        Label lblObjetivo = new Label(objetivo);
        lblObjetivo.setBounds(70, 650, 400, 30);
        add(lblObjetivo);
    }
}