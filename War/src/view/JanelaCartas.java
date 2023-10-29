package view;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.GameModel;

import java.awt.Label;

public class JanelaCartas extends Frame {
    private GameModel gameModel;
    Map<String,  String> dictPath = new HashMap<>();
    Map<String, String> dicionario = new HashMap<>();

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
    dicionario.put("ESPANHA", "war_carta_oc_espanha.png");
    dicionario.put("FRANCA", "war_carta_oc_franca.png");
    dicionario.put("ITALIA", "war_carta_oc_italia.png");
    dicionario.put("POLONIA", "war_carta_oc_polonia.png");
    dicionario.put("REINO_UNIDO", "war_carta_oc_reinounido.png");
    dicionario.put("ROMENIA", "war_carta_oc_romenia.png");
    dicionario.put("SUECIA", "war_carta_oc_suecia.png");
    dicionario.put("UCRANIA", "war_carta_oc_ucrania.png");
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
        Set<String> nomesTerritorios = dictPath.keySet();
        int offSetX = 0;
        int offsetY = 0;
        for (String nome : nomesTerritorios) {        	
        	JLabel label = new JLabel();
        	label.setBounds(0 + offSetX, 0+offsetY, 160, 300);
        	System.out.println(dictPath.get(nome));
        	label.setIcon(new ImageIcon(dictPath.get(nome)));
        	add(label);
        	offSetX += 150;
        	if (offSetX > 1500) {
        		offSetX = 0;
        		offsetY = 300;
        	}
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

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
    	 String basePath = "C:\\Users\\Murilo\\Desktop\\Projetos\\programacao_orientada_a_objetos\\War\\src\\view\\images\\";
    	    for (int i = 0; i < arr.length; i++) {
    	        String countryName = arr[i];
    	        String path = basePath + dicionario.get(countryName);
    	        dictPath.put(countryName, path);
    	    }
    	    
    	    
    }
    public static void main(String[] args) {
        new JanelaCartas();
    }
}