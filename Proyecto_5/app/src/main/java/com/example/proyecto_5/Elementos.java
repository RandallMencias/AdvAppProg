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

        if (mapaGastos.get(tipo) == null) {
            List<Gastos> listaGastos = new ArrayList<>();
            listaGastos.add(new Gastos(fecha, precio));
            mapaGastos.put(tipo, listaGastos);
        }else{
            List<Gastos> listaGastos = mapaGastos.get(tipo);
            listaGastos.add(new Gastos(fecha, precio));

        }


        /*Gastos gasto = new Gastos(fecha, precio);
        mapaGastos.put(tipo, gasto);*/
    }


    public Map<String, List<Gastos>> getGastos() {
        return mapaGastos;
    }
}
