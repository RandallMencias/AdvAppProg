//package com.example.proyecto_4;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//
//public class User implements Serializable{
//    private String nombre;
//
//    private ArrayList<String>mensajes;
//    public User(String nombre){
//        nombre = this.nombre;
//    }
//    public String getNombre() {
//        return nombre;
//
//    }
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public void setMensajes(ArrayList<String> mensajes) {
//        this.mensajes = mensajes;
//    }
//
//    public ArrayList<String> getMensajes() {
//        return mensajes;
//    }
//
//    public void addMensaje(String mensaje) {
//        mensajes.add(mensaje);
//    }
//
//    public String getultimomensaje() {
//        return mensajes.get(mensajes.size()-1);
//    }
//
//    @Override
//    public String toString() {
//        return getNombre() + ">>>" + mensajes.get(mensajes.size()-1);//colocar hora
//    }
//
//}