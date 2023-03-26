package com.example.proyecto_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    private static String mssg;

    public ClientController() {
        flag = true;

    }


    @Override
    public void run() {
        System.out.println("Cliente iniciado");
        try {
            cliente = new Socket(InetAddress.getLocalHost(), 1234);
            out = new PrintWriter(cliente.getOutputStream(), true); // envia el mensaje
            in = new BufferedReader(new InputStreamReader(cliente.getInputStream())); // recibe el mensaje
            mensajes mensaje = new mensajes();
            Thread thread = new Thread(mensaje);
            thread.start();
            String inMessage;
            while ((inMessage = in.readLine()) != null) { //imprime mensaje en ventana del cliente
//                    TAchat.appendText(mssg + "\n");
                System.out.println(inMessage);
                }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void desconectar() {
        try {
            flag = false;
            out.close();
            in.close();
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    class mensajes implements Runnable { // maneja el proceso de cierre de la conexion// agregar cierre de conexion
        @Override
        public void run() {
            try {
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while (flag) {
                    String message = inReader.readLine();
                    out.println(message);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
