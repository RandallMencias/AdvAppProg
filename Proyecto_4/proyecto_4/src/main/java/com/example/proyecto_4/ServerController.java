import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.*;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ServerController implements Runnable {
    @FXML
    private TextArea TAServer;

    public ServerController() {
        Thread newThread = new Thread(this);
        newThread.start();
    }

    @Override
    // run para iniciar al crear
    public void run() {

        try {
            // crear socket del server
            ServerSocket servidor = new ServerSocket(9999);

            User usuario;
            while (true) {// lazo para que se puedan ingresar multiples palabras
                // que el socket acepte la conexion
                Socket socket1 = servidor.accept();
                ObjectInputStream entrada = new ObjectInputStream(socket1.getInputStream());
                usuario = (User) entrada.readObject();
                
                TAServer.appendText("\n" + usuario);
                
                //Enviar Informacion a otro usuario
                Socket enviar_info = new Socket(usuario.getIp(),54321);
                ObjectOutputStream salida = new ObjectOutputStream(enviar_info.getOutputStream());
                salida.writeObject(entrada);
                
                //cerrar sockets activos
                /* enviar_info.close();
                socket1.close(); */
            }

        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
