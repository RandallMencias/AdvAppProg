package com.example.proyecto_6;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class singleton { //singleton para modelo de datos
    private static Map<String , Nasa> mapaImagenes; //Mapa que guarda objetos nasa con el titulo como key
    private ArrayList<String> Keys; //lista de nombres de imagenes
    private Map<Integer, String> lista; //Mapa para trackear los cuadros de imagen y su evento
    private static Map<String, Bitmap> cache;//cache para no repetir descarga de imagenes
    //singleton
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

    public void addNasa(Nasa nasa){//agregar objetos nasa a mapaImagenes y sus key
        if(!mapaImagenes.containsKey(nasa.getTitle())){
            mapaImagenes.put(nasa.getTitle(),nasa);
            Keys.add(nasa.getTitle());
            Log.d("singleton", "addNasa: " + nasa.getTitle());
        }
    }

    public List<Nasa> showImageMap(){//funcion para tener lista de objetos nasa
        ArrayList<Nasa> listaNasa = new ArrayList<>();
        for(String l : mapaImagenes.keySet()){
            listaNasa.add(mapaImagenes.get(l));
        }
        return listaNasa;
    }

    //funcion para agregar al cache al descargar la imagen
    public void addtoCache(String name, Bitmap bitmap){
        cache.put(name,bitmap);
    }

    //funcion para obtener de cache la imagen
    public Bitmap getBitmap(String name){
        return cache.get(name);
    }

    //Obtener imagen aleatoria no repetida
    public String getRandomImage(){
        int random = (int) (Math.random() * Keys.size());
        if(validarLista(Keys.get(random))){
            return Keys.get(random);
        } else{
            return getRandomImage();
        }

    }

    //obtener URL para descarga de imagen
    public String getURL(String name) {
        if (!cache.containsKey(name)) {
            return mapaImagenes.get(name).getUrl();
        }
        else {
            return "Cache";
        }
    }
    //validar si ya se tiene imagen en cache
    public boolean inCache(String name){
        return cache.containsKey(name);
    }

    //Validar que no se pueda repetir la imagen en mas de un cuadro
    public boolean validarLista(String name){
        for (Map.Entry<Integer, String> entry : lista.entrySet()){
            if(entry.getValue().equals(name)){
                return false;
            }
        }
        return true;
    }
    //actualizar lista al cambiar de imagen
    public void replaceImage(Integer i, String name){
        lista.put(i,name);
    }
}
