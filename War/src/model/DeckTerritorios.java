package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DeckTerritorios {
    private List<Carta> cards;

    public DeckTerritorios() {
        cards = new ArrayList<>();
        initializeDeckTerritorios();
    }

    private void initializeDeckTerritorios() {
        String symbol;
        String[][] territories = {
                { // Triangulo
                        "AFRICA_DO_SUL", "EGITO", "ALASCA", "TEXAS", "VANCOUVER", "COREIA_DO_SUL", "INDIA", "IRAQUE",
                        "MONGOLIA", "RUSSIA", "TAILANDIA", "TURQUIA", "PERU", "VENEZUELA", "FRANCA", "POLONIA",
                        "ROMENIA", "AUSTRALIA", "INDONESIA",
                },
                { // Quadrado
                        "ANGOLA", "SOMALIA", "CALIFORNIA", "MEXICO", "NOVAYORK", "CHINA", "COREIA_DO_NORTE", "IRA",
                        "JORDANIA", "LETONIA", "SIBERIA", "SIRIA", "ARGENTINA", "ITALIA", "SUECIA", "NOVAZELANDIA"
                },
                { // Circulo
                        "ARGELIA", "NIGERIA", "CALGARY", "GROELANDIA", "QUEBEC", "ARABIA_SAUDITA", "BANGLADESH",
                        "CAZAQUISTAO", "ESTONIA", "JAPAO", "PAQUISTAO", "BRASIL", "ESPANHA", "REINO_UNIDO", "UCRANIA",
                        "PERTH" }
        };

        for (int i = 0; i < territories.length; i++) {
            for (String territory : territories[i]) {
                switch (i) {
                    case 0:
                        symbol = "TRIANGULO";
                        cards.add(new Carta(territory, symbol));
                        break;
                    case 1:
                        symbol = "QUADRADO";
                        cards.add(new Carta(territory, symbol));
                        break;
                    case 2:
                        symbol = "CIRCULO";
                        cards.add(new Carta(territory, symbol));
                        break;
                    default:
                        // code block
                }

            }
        }
        shuffleDeckTerritorios();
    }

    private void shuffleDeckTerritorios() {
        Collections.shuffle(cards);
    }

    public Carta drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null; 
    }

    public int getSize() {
        return cards.size();
    }

    public void addCarta(Carta carta){
        cards.add(carta);
    }
}