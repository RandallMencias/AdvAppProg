package com.example.proyecto_5;

import java.util.*;

public class Elementos {

    private static Map<String, List<Gastos>> mapaGastos;
    private static Map<String,Double> mapGastosMax;
    private static Elementos instancia = new Elementos();
    public static Elementos getInstance(){return instancia; }
    private Elementos() {
        mapaGastos = new HashMap<>();
        mapGastosMax = new HashMap<>();

        mapGastosMax.put("Vivienda", 400.0);
        mapaGastos.put("Vivienda", new ArrayList<>());

        mapGastosMax.put("Alimentacion", 5.0);
        mapaGastos.put("Alimentacion", new ArrayList<>());

        mapGastosMax.put("Salud", 50.0);
        mapaGastos.put("Salud", new ArrayList<>());

        mapGastosMax.put("Educacion", 300.0);
        mapaGastos.put("Educacion", new ArrayList<>());

        mapGastosMax.put("Ropa", 100.0);
        mapaGastos.put("Ropa", new ArrayList<>());



    }

    public void agregarGasto(String fecha, double precio, String tipo){
        if (mapaGastos.containsKey(tipo)) {
            mapaGastos.get(tipo).add(new Gastos(fecha, precio));
        }




        /*Gastos gasto = new Gastos(fecha, precio);
        mapaGastos.put(tipo, gasto);*/
    }

    public double getGastoMax(String tipo){
        return mapGastosMax.get(tipo);
    }

    public double getGastoTotal(String tipo){
        double total = 0;
        if (mapaGastos.get(tipo) != null) {
            List<Gastos> listaGastos = mapaGastos.get(tipo);
            for (Gastos gasto : listaGastos) {
                total += gasto.getValor();
            }
        }
        return total;
    }

    public Map<String, List<Gastos>> getmapGastos() {
        return mapaGastos;
    }

    public List<String> getTiposGastos(){
        List<String> listaTipos = new ArrayList<>();
        for (String tipo : mapaGastos.keySet()) {
            listaTipos.add(tipo);
        }
        return listaTipos;
    }

}
