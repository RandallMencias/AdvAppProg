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
      createQueries FQueries = new createQueries();
      FQueries.getTables();
      FQueries.getColumns("FACULTY");
      FQueries.getColumns("COURSES");
      // FQueries.addfaculty(new faculty("abc43Ra","Profesor Randall", "Aurora"));
      // FQueries.addcourse(new courses("CSC 130", "El bicho siuuuuu", "abc43Ra"));
      ArrayList<courses> courses = FQueries.getcoursesperfaculty("abc43Ra");
      System.out.println("Courses for faculty abc43Ra: Profesor Randall:");
      
      for (courses course : courses) {
         System.out.println(course.getCourse());
      }
      FQueries.deletecourse("cpp");
      courses = FQueries.getcoursesperfaculty("abc43Ra");
      System.out.println("Courses2 for faculty abc43Ra: Profesor Randall:");
      for (courses course : courses) {
         System.out.println(course.getCourse());
      }

      // launch(args);
   }
}