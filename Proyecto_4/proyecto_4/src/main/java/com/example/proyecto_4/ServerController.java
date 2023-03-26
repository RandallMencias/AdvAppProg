package com.example.proyecto_4;

import java.io.*;
import java.net.ServerSocket;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ServerController implements Runnable {
    @FXML
    private TextArea TAServer;
    private ArrayList<connections> conexiones;
    private ArrayList<log> logs;
    private ServerSocket servidor;
    private boolean flag;
    private ExecutorService pool;


    public ServerController() {
        flag = true;
        conexiones = new ArrayList<>();

    }

    @Override
    // run para iniciar al crear
    public void run() {

        try {
            pool = Executors.newCachedThreadPool();
            // crear socket del server
            servidor = new ServerSocket(1234);
            // aceptar conexiones
            while (flag) {
                Socket cliente = servidor.accept();
                connections conexion = new connections(cliente);
                System.out.println("Conexion aceptada");
                conexiones.add(conexion);
                pool.execute(conexion);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarmssgatodos(String mensaje) {
        for (connections conexion : conexiones) {
            conexion.enviarMensaje(mensaje);
        }
    }

    public void apagarservidor() {
        try {
            flag = false;
            if (!servidor.isClosed()) {
                servidor.close();
            }
            for (connections conexion : conexiones) {
                conexion.cerrarConexion();
            }

        } catch (IOException e) {
            //todo: handle exception
        }

    }


    //------------------------------------------------------------------------------------------******************************----------------------------------------------


    public class connections implements Runnable {
        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private User usuario;


        public connections(Socket client) {
            this.client = client;
//            usuario = new User();
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(client.getOutputStream(), true); // enviar mensajes
                in = new BufferedReader(new java.io.InputStreamReader(client.getInputStream())); // recibe nombre// conectar con gui
                out.println("Ingrese su nombre: ");
                String nombre = in.readLine();
                System.out.println(nombre + " se ha conectado");
                enviarmssgatodos(nombre + " se ha conectado");
                String mensaje;
                while ((mensaje = in.readLine()) != null) {
//                    usuario.addMensaje(mensaje);
                    System.out.println(mensaje);
//                    System.out.println(usuario.getultimomensaje());
                    //agregar log aqui?
                    enviarmssgatodos(nombre+ ": " + mensaje);

                }
            } catch (IOException e) {
                //todo   : handle
            }

        }

        public void enviarMensaje(String mensaje) {
            out.println(mensaje);
        }

        public void cerrarConexion() {
            try {
                if (!client.isClosed()) {
                    client.close();
                    in.close();
                    out.close();
                }
            } catch (IOException e) {
                ;
            }
        }
    }
}




