import java.util.ArrayList;

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