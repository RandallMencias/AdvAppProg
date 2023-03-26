package com.example.proyecto_4;

public class log {
    private  String Origer;
    private  String Destino;
    private  String Mensaje;
    private  String Hora;
    private String Confirmacion;

    public log(String origer, String destino, String mensaje, String hora, String confirmacion) {
        Origer = origer;
        Destino = destino;
        Mensaje = mensaje;
        Hora = hora;
        Confirmacion = confirmacion;
    }

    public String getOriger() {
        return Origer;
    }

    public void setOriger(String origer) {
        Origer = origer;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String destino) {
        Destino = destino;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getConfirmacion() {
        return Confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        Confirmacion = confirmacion;
    }

    public String toString() {
        return Origer + ">>>" + Destino + ">>>" + Mensaje + ">>>" + Hora + ">>>" + Confirmacion;
    }
}
