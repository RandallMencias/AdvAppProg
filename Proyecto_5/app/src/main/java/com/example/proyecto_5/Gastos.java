package com.example.proyecto_5;

import java.util.*;

public class Gastos {
    private String fecha;
    private double valor;
//osi
    public Gastos(String fecha, double valor){
        this.fecha = fecha;
        this.valor = valor;
    }

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
