package com.example.proyecto_4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ServerController implements Runnable {
    @FXML
    private TextArea TAServer;
    private ArrayList<connections> conexiones;
    ServerSocket servidor;
    private boolean flag;
    private ExecutorService pool;


    public ServerController() {
        flag = true;
        conexiones = new ArrayList<>();
        Thread newThread = new Thread(this);
        newThread.start();
    }

    @Override
    // run para iniciar al crear
    public void run() {

        try {
            pool = Executors.newCachedThreadPool();
            // crear socket del server
            servidor = new ServerSocket(12345);
            // aceptar conexiones
            while(flag) {
                Socket cliente = servidor.accept();
                connections conexion = new connections(cliente);
                conexiones.add(conexion);
                pool.execute(conexion);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarmensajetodos(String mensaje) {
        for (connections conexion : conexiones) {
            conexion.enviarMensaje(mensaje);
        }
    }

    public void apagarservidor() {
        flag = false;
        try {
            servidor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (connections conexion : conexiones) {
            conexion.cerrarConexion();
        }
    }

}




