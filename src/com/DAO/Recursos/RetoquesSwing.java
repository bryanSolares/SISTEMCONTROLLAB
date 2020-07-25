package com.DAO.Recursos;

import java.awt.Color;
import java.util.Random;

public class RetoquesSwing {

    private static final String PREFIJO = "#";
    private String nombreColor;

    private static final String[] listaColores = {"5b8c5a", "0f4c81", "ed6663", "00bdaa", "f688bb", "f67575", "ffa34d"};

    /*
        "00bdaa","400082","fe346e","f1e7b6","de7119","116979",
        "18b0b0","f6d186","fcf7bb","cbe2b0","f19292","1eb2a6",
        "d4f8e8","ffa34d","f67575","f688bb","e8f9e9","",""
     */
    private static int CONTADOR = 1;

    public RetoquesSwing() {
    }

    public Color generaColor() {
        this.nombreColor = PREFIJO.concat(listaColores[new Random().nextInt(listaColores.length)]);
        return Color.decode(nombreColor);
    }

    public String getNombreColor() {
        return nombreColor;
    }

}
