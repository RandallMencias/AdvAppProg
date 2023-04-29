package com.example.proyecto_6;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class singleton {
    private static Map<String , Nasa> mapaImagenes;
    private static Map<String, Bitmap> cache;
    private static singleton instancia = new singleton();
    public static singleton getInstance(){return instancia; }
    private singleton(){
        mapaImagenes = new HashMap<>();
        cache = new HashMap<>();
    }

    public void addNasa(Nasa nasa){
        if(!mapaImagenes.containsKey(nasa.getTitle())){
            mapaImagenes.put(nasa.getTitle(),nasa);
        }
    }
    public void addtoCache(String name, Bitmap bitmap){
        if(!cache.containsKey(name)) {
            cache.put(name,bitmap);
        }
    }

    public Bitmap getBitmap(String name){
        return cache.get(name);
    }
    public List<String> getBitmapName(){
        List<String> listaNombres = new ArrayList<>();
        for(String nombre : cache.keySet()){
            listaNombres.add(nombre);
        }

        return listaNombres;
    }

    //private singleton s = singleton.getInstance(); //instancia de la clase singleton
}
