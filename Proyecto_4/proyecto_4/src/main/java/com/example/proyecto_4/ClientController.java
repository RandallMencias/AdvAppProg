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
            out = new PrintWriter(cliente.getOutputStream(), true); // crea conexion de envio con el servidor
            in = new BufferedReader(new InputStreamReader(cliente.getInputStream())); //crea conexion de recepcion con el servidor
            mensajes mensaje = new mensajes();
            Thread thread = new Thread(mensaje);
            thread.start();
            String inMessage;
            while ((inMessage = in.readLine()) != null) { //recibe del servidor
//                    TAchat.appendText(mssg + "\n");
                if(inMessage.contains("EliminarM@"))
                {
                    String [] mensajesplit = inMessage.split("@");
                    //iterar gui y eliminar el mensaje que coincida con el messagesplit[1]
                }
                System.out.println(inMessage); // imprime el mensaje recibido a consola

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


    class mensajes implements Runnable {

        private BufferedReader inReader;
        //envia mensajes al servidor
        @Override
        public void run()  { //constructor
            try {
                inReader = new BufferedReader(new InputStreamReader(System.in)); //recibe de consola
                while (flag) {
                    //regex por comas y espacios para separar destinatarios
                    //segun el size de la lista iterar y mandar el mensaje invididualmente
                    //
                    String message = inReader.readLine(); //recibe de consola// aqui habria que conectar con el gui
                    out.println(message); //envia al servidor
//                    //si presiona el boton de enviar y el destinatario esta vacio se envia a todos
//                    out.println(message); //envia al servidor
//                    //if tiene destinatario
//                    out.println("@"+TFReciever.getText()+"//"+message);
//                    // if salir
//                    out.println("Salir//");


                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
