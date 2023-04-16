package com.example.proyecto_5;
//Clase gastos

public class Gastos {
    //datos miembro
    private String fecha;
    private double valor;

    //constructor
    public Gastos(String fecha, double valor){
        this.fecha = fecha;
        this.valor = valor;
    }
    //setters y getters
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
