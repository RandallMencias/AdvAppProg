import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class ClientController {
    
    @FXML
    private TextField TFMessage;

    @FXML
    private TextField TFName;

    public void sendMessage(ActionEvent event){
        //crear socket al presionar el boton
        try {
            Socket newSocket = new Socket(InetAddress.getLocalHost(),12345);//crear nuevo socket
            //dataoutputstream que toma el outputStream de el socket creado
            DataOutputStream salida = new DataOutputStream(newSocket.getOutputStream());
            salida.writeUTF(TFMessage.getText());//salida toma el texto del textfield
            salida.close();//cerrar flujo de datos


        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
    }
}
