package com.example.proyecto_5;

import java.util.*;

public class Elementos {

    private Map<String, List<Gastos>> mapaGastos;
    private static Elementos instancia = new Elementos();
    public static Elementos getInstance(){return instancia; }
    private Elementos() {
        mapaGastos = new HashMap<>();
    }

    public void agregarGasto(String fecha, double precio, String tipo){
        List<Gastos> listaGastos = mapaGastos.get(tipo);
        if (listaGastos == null) {
            listaGastos = new ArrayList<>();
            mapaGastos.put(tipo, listaGastos);
        }
        // Agregar un nuevo gasto a la lista
        Gastos nuevoGasto = new Gastos(fecha, precio);
        listaGastos.add(nuevoGasto);


        /*Gastos gasto = new Gastos(fecha, precio);
        mapaGastos.put(tipo, gasto);*/
    }
    public List<Gastos> getLista(String key){
        return mapaGastos.get(key);
    }

    public Map<String, List<Gastos>> getGastos() {
        return mapaGastos;
    }
}
