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
    mensajes mensaje = new mensajes();

    public ClientController() {
        Thread thread = new Thread(mensaje);
        thread.start();
    }
    public void desconectar(ActionEvent event) {
        try {
            mensaje.desconectarCliente();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ya estas desconectado");
        }
    }
    public void sendMessage(ActionEvent event){
        mensaje.enviarMensaje(TFMessage.getText(), TFReciever.getText().isEmpty());
    }
    //--------------------------------------------------------------------
    public class mensajes extends Task<String> {
        private Socket cliente;
        private BufferedReader in;
        private PrintWriter out;
        private boolean flag;

        public mensajes() {
            flag = true;
        }

        @Override
        public String call() throws Exception {
            try {
                cliente = new Socket(InetAddress.getLocalHost(), 1234);
                out = new PrintWriter(cliente.getOutputStream(), true); // crea conexion de envio con el servidor
                in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                String inMessage = in.readLine();
                while (flag){
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
                out.close();
                in.close();
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
}