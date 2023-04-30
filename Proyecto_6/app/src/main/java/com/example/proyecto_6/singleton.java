package com.example.proyecto_6;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class singleton {
    private static Map<String , Nasa> mapaImagenes;
    private ArrayList<String> Keys;
    private Map<Integer, String> lista;
    private static Map<String, Bitmap> cache;
    private static singleton instancia = new singleton();
    public static singleton getInstance(){return instancia; }
    private singleton(){
        mapaImagenes = new HashMap<>();
        cache = new HashMap<>();
        Keys = new ArrayList<>();
        lista = new HashMap<>();
        lista.put(1,"Hola");
        lista.put(2,"Hola");
        lista.put(3,"Hola");
        lista.put(4,"Hola");
        lista.put(5,"Hola");
        lista.put(6,"Hola");

    }

    public void addNasa(Nasa nasa){
        if(!mapaImagenes.containsKey(nasa.getTitle())){
            mapaImagenes.put(nasa.getTitle(),nasa);
            Keys.add(nasa.getTitle());
            Log.d("singleton", "addNasa: " + nasa.getTitle());
        }
    }

    public List<Nasa> showImageMap(){
        List<Nasa> listaNasa = new ArrayList<>();
        for(String l : mapaImagenes.keySet()){
            listaNasa.add(mapaImagenes.get(l));
        }
        return listaNasa;
    }

    public void addtoCache(String name, Bitmap bitmap){
        cache.put(name,bitmap);
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

    public String getRandomImage(){
        int random = (int) (Math.random() * Keys.size());
        if(validarLista(Keys.get(random))){
            return Keys.get(random);
        } else{
            return getRandomImage();
        }

    }


    public String getURL(String name) {
        if (!cache.containsKey(name)) {
            return mapaImagenes.get(name).getUrl();
        }
        else {
            return "Cache";
        }
    }
    public boolean inCache(String name){
        return cache.containsKey(name);
    }
    public boolean validarLista(String name){
        for (Map.Entry<Integer, String> entry : lista.entrySet()){
            if(entry.getValue().equals(name)){
                return false;
            }
        }
        return true;
    }
    public void replaceImage(Integer i, String name){
        lista.put(i,name);
    }
}
