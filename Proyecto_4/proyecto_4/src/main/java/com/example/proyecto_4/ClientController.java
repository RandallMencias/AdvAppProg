package com.example.proyecto_4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class ClientController {
    @FXML
    private TextArea TAchat;
    @FXML
    private ListView<String> LVCliente;
    @FXML
    private TextField TFMessage;

    @FXML
    private TextField TFName;

    @FXML
    private TextField TFReciever;
    private Socket cliente;
    enviarMensajes mensajeenvio;
    recibirMensajes mensajerecibido;

    public ClientController() {
        try {
            cliente = new Socket(InetAddress.getLocalHost(), 1234);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mensajeenvio = new enviarMensajes(cliente);
        mensajerecibido = new recibirMensajes(cliente);
        Thread thread = new Thread(mensajeenvio);
        Thread thread2 = new Thread(mensajerecibido);
        thread.start();
        thread2.start();
    }
    public void desconectar(ActionEvent event) {
        try {
            mensajeenvio.desconectarCliente();
            mensajerecibido.desconectarCliente();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ya estas desconectado");
        }
    }
    public void sendMessage(ActionEvent event){
        mensajeenvio.enviarMensaje(TFMessage.getText(), TFReciever.getText().isEmpty());
    }
    //--------------------------------------------------------------------
    public class enviarMensajes extends Task<String> {
        private Socket cliente;
        private PrintWriter out;
        private boolean flag;

        public enviarMensajes(Socket cliente) {
            flag = true;
            this.cliente = cliente;
        }

        @Override
        public String call() throws Exception {
            try {
                out = new PrintWriter(cliente.getOutputStream(), true); // crea conexion de envio con el servidor

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "no se puede conectar al servidor");
            }
            return "Hola Mundo";
        }
        public void desconectarCliente(){
            try {
                flag = false;
                out.close();
                cliente.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void enviarMensaje(String message, boolean emptyTF){
            String mensaje = message;
            if(!    emptyTF){
                String[] split = TFReciever.getText().replaceAll("\\s", "").split(",");
                for (String string : split) {
                    out.println("@" + string + "//" + mensaje);
                }
            } else{
                out.println("Todos//" + mensaje);
            }
        }
    }
    public class recibirMensajes extends Task<String>{
        private Socket cliente;
        private BufferedReader in;
        private boolean flag;

        public recibirMensajes(Socket cliente) {
            flag = true;
            this.cliente = cliente;
        }

        @Override
        public String call() throws Exception {
            try {
                in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                String inMessage;
                while ((inMessage = in.readLine()) != null){
                    if (inMessage.contains("EliminarM@")) {
                        String[] mensajesplit = inMessage.split("@");
                        // iterar gui y eliminar el mensaje que coincida con el messagesplit[1]
                        System.out.println("SIUI");
                    }
                    System.out.println(inMessage);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "no se puede conectar al servidor");
            }
            return "Hola Mundo";
        }
        public void desconectarCliente(){
            try {
                flag = false;
                in.close();
                cliente.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}