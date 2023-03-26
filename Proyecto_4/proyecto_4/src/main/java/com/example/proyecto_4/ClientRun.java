package com.example.proyecto_4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientRun extends Application {
    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("Client.fxml"));
//        Scene scene = new Scene(root);
//        stage.setTitle("Cliente");
//        stage.setScene(scene);
//        stage.show();
    }
    public static void main(String[] args) {
//        launch();
        ClientController clientController = new ClientController();
        clientController.run();
    }
}