package com.example.proyecto_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.*;

public class ClientController implements Runnable {
    private Socket cliente;
    private BufferedReader in;
    private PrintWriter out;
    private boolean flag;

    @FXML
    private TextArea TAchat;

    @FXML
    private TextField TFMessage;

    @FXML
    private TextField TFName;

    @FXML
    private TextField TFReciever;
    //Run para que se reciban datos

    public ClientController() {
        flag = true;

        Thread newThread = new Thread(this);
        newThread.start();
    }


    @Override
    public void run() {
        try {
            cliente = new Socket(InetAddress.getLocalHost(), 12345);
            out = new PrintWriter(cliente.getOutputStream(), true);
            in = new BufferedReader(new java.io.InputStreamReader(cliente.getInputStream()));
            mensajes mensaje = new mensajes();
            Thread thread = new Thread(mensaje);
            thread.start();
            String mensajes;
            while ((mensajes = in.readLine()) != null) {
                TAchat.appendText(mensajes);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //al iniciar, se crea el hilo para recibir datos

    class mensajes implements Runnable {
        @Override
        public void run() {
            try {

                BufferedReader inReader = new BufferedReader(new java.io.InputStreamReader(cliente.getInputStream()));
                while (flag) {
                    String mensaje = inReader.readLine();//recibe el mensaje


                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
