//Juan Diego Venegas Barreto 00209856
//Randall Mencias 00321469
//clase: Programacion Avanzada de Apps
//Proyecto 3. programa que maneja base de datos

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BDD extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("Registro.fxml"));
      Scene scene = new Scene(root);
      stage.setTitle("RegisterViewer");
      stage.setScene(scene);
      stage.show();
   }

   public static void main(String[] args) {
      createQueries createQueries= new createQueries();
      createQueries.getColumns("COURSES");
      createQueries.getTables();
      launch(args);
   }
}