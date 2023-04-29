package com.example.proyecto_6;

import java.util.HashMap;
import java.util.Map;

public class singleton {
    private static Map<String , Nasa> mapaImagenes;
    private static singleton instancia = new singleton();
    public static singleton getInstance(){return instancia; }
    private singleton(){
        mapaImagenes = new HashMap<>();
    }

    public void addNasa(Nasa nasa){
        if(!mapaImagenes.containsKey(nasa.getTitle())){
            mapaImagenes.put(nasa.getTitle(),nasa);
        }
    }

}
