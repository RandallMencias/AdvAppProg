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

    public void agregarGasto(String fecha, double precio, String tipo, String item){
        if (mapaGastos.containsKey(tipo)) {
            mapaGastos.get(tipo).add(new Gastos(fecha, precio, item));
        }
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
    public Map<String, Double> getmapGastosMax() {
        return mapGastosMax;
    }
    public void setmapGastosMax(String key, Double valor) {
        mapGastosMax.put(key, valor);
    }
    public List<String> getTiposGastos(){
        List<String> listaTipos = new ArrayList<>();
        for (String tipo : mapaGastos.keySet()) {
            listaTipos.add(tipo);
        }
        return listaTipos;
    }
    public void nuevoTipoGasto(String key, double valorMax){
        mapGastosMax.put(key, valorMax);
        mapaGastos.put(key, new ArrayList<>());
    }

    public List<String> getGastosPorTipo(String tipo){
        List<String> listaGastos = new ArrayList<>();
        if (mapaGastos.get(tipo) != null) {
            for (Gastos gasto : mapaGastos.get(tipo)) {
                listaGastos.add("Fecha: "+gasto.getFecha()+" "+ gasto.getItem()+" $"+Double.toString(gasto.getValor()));
                }
        }
        return listaGastos;
    }

    public String getGastoTotalTipo(String tipo){
        double total = 0;
        if (mapaGastos.get(tipo) != null) {
            List<Gastos> listaGastos = mapaGastos.get(tipo);
            for (Gastos gasto : listaGastos) {
                total += gasto.getValor();
            }
        }
        return Double.toString(total);
    }

    public String getGastoTotalFecha(String fecha){
        double total = 0;
        for (String tipo : mapaGastos.keySet()) {
            List<Gastos> listaGastosObj = mapaGastos.get(tipo);
            for (Gastos gasto : listaGastosObj) {
                if (gasto.getFecha().equals(fecha)) {
                    total += gasto.getValor();
                }
            }
        }
        return Double.toString(total);
    }

    public String getGastoTotalRango(Double min, Double max){
        double total = 0;
        for (String tipo : mapaGastos.keySet()) {
            List<Gastos> listaGastosObj = mapaGastos.get(tipo);
            for (Gastos gasto : listaGastosObj) {
                if (gasto.getValor() >= min && gasto.getValor() <= max) {
                    total += gasto.getValor();
                }
            }
        }
        return Double.toString(total);
    }


    public List<String> getGastosPorFecha(String fecha){

        List<String> listaGastos = new ArrayList<>();
        for (String tipo : mapaGastos.keySet()) {
            List<Gastos> listaGastosObj = mapaGastos.get(tipo);
            for (Gastos gasto : listaGastosObj) {
                if (gasto.getFecha().equals(fecha)) {
                    listaGastos.add(tipo +": "+ gasto.getItem()+" $" + Double.toString(gasto.getValor()));
                }
            }
        }

        return listaGastos;
    }

    public List<String> getGastosPorRango(Double min, Double max){
        List<String> listaGastos = new ArrayList<>();
        for (String tipo : mapaGastos.keySet()) {
            List<Gastos> listaGastosObj = mapaGastos.get(tipo);
            for (Gastos gasto : listaGastosObj) {
                if (gasto.getValor() >= min && gasto.getValor() <= max) {
                    listaGastos.add(tipo+" Fecha: "+ gasto.getFecha()+" "+ gasto.getItem()+" $"+Double.toString(gasto.getValor()));
                }
            }
        }
        return listaGastos;
    }

    public void eliminarGasto(String tipo, String item){
        boolean flag = false;
        List<Gastos> listaGastos = mapaGastos.get(tipo);
        for (Gastos gasto : listaGastos) {
            if (gasto.getItem().equals(item)) {
                flag = true;
                listaGastos.remove(gasto);
                break;
            }
        }
        if(!flag){
            throw new RuntimeException();
        }
    }




}
