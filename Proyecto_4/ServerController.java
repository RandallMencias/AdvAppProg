import java.io.DataInputStream;
import java.io.IOException;
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
            ServerSocket servidor = new ServerSocket(12345);

            while (true) {// lazo para que se puedan ingresar multiples palabras
                // que el socket acepte la conexion
                Socket socket1 = servidor.accept();
                DataInputStream entrada = new DataInputStream(socket1.getInputStream());
                String mensaje = entrada.readUTF();// almacenar mensaje
                TAServer.appendText("\n" + mensaje);
                socket1.close();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
