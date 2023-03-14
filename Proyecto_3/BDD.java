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
      //============================================================================
      createQueries FQueries = new createQueries();
      ArrayList<courses> courses = FQueries.getcoursesperfaculty("abc43Ra");      
      courses = FQueries.getcoursesperfaculty("abc43Ra");
      for (courses course : courses) {
         System.out.println(course.getCourse());
      }
      //==============================================================================
      Scene scene = new Scene(root);
      stage.setTitle("RegisterViewer");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String[] args) {
      launch(args);
   }
   
}