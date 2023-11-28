package model;

enum ListaObjetivos {
    CONQ_24, // Conquistar 24 territorios
    CONQ_18_2, // Conquistar 18 territorios, cada um com no minimo 2 exercitos
    CONQ_AS_LATAM, // Conquistar Asia e America do Sul
    CONQ_AS_AF, // Conquistar Asia e Africa
    CONQ_NA_AF, // Conquistar America do Norte e Africa
    CONQ_NA_AU, // Conquistar America do Norte e Australasia
    CONQ_EU_LATAM, // Conquistar Europa e America do Sul
    CONQ_EU_AU, // Conquistar Europa e Australasia 
    ELIM_AZUL, // Eliminar o jogador azul ou conquistar 24 (caso não tenha azul)
    ELIM_VERMELHO,
    ELIM_AMARELO,
    ELIM_VERDE,
    ELIM_BRANCO,
    ELIM_PRETO
}
