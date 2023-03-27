import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClientController implements Runnable {

    @FXML
    private TextArea TAchat;

    @FXML
    private TextField TFMessage;

    @FXML
    private TextField TFName;

    @FXML
    private TextField TFReciever;

    int hora, minuto;

    // al iniciar, se crea el hilo para recibir datos
    public ClientController() {
        Thread newThread = new Thread(this);
        newThread.start();
    }

    public void sendMessage(ActionEvent event) {
        // crear socket al presionar el boton
        try {
            Socket newSocket = new Socket(InetAddress.getLocalHost(), 9999);// crear nuevo socket
            // Crear objeto usuario con sus datos respectivos
            User usuario = new User();
            usuario.setNombre(TFName.getText());
            usuario.setIp(TFReciever.getText());
            usuario.setMensaje(TFMessage.getText());
            Calendar calendario = new GregorianCalendar();
            hora = calendario.get(Calendar.HOUR_OF_DAY);
            minuto = calendario.get(Calendar.MINUTE);
            String tiempo = hora + ":" + minuto;
            usuario.setHora(tiempo); 
            // enviar objeto por socket
            ObjectOutputStream salida = new ObjectOutputStream(newSocket.getOutputStream());
            salida.writeObject(usuario);
            newSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
    }

    // Run para que se reciban datos
    @Override
    public void run() {
        try {
            ServerSocket recibeInfo = new ServerSocket(54321);
            Socket informacion;
            User datos = new User();
            while (true) {
                informacion = recibeInfo.accept();
                ObjectInputStream datosEntrada = new ObjectInputStream(informacion.getInputStream());
                datos = (User) datosEntrada.readObject();
                TAchat.appendText("\n" + datos);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
