import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class createQueries {
   PreparedStatement showFacultyTable;
   PreparedStatement showCourseTable;
   // constructor
   public createQueries() {
      try {
         Connection connection = DriverManager.getConnection("jdbc:derby:Datos;", "Ran", "Ran");
         //query que muestre tabla faculty
         showFacultyTable = connection.prepareStatement("SELECT * FROM Faculty");
         //query que muestre tabla course
         showCourseTable = connection.prepareStatement("SELECT * FROM Course");
      } catch (SQLException sqlException) {
         sqlException.printStackTrace();
      }
   }

   //show all Faculty elements
   public List<Data> getFacultyElements(){
      try(ResultSet resultSet = showFacultyTable.executeQuery()) {
         List<Data> results = new ArrayList<Data>();
         while (resultSet.next()) {
            results.add(new Data(
               resultSet.getString("faculty_Name"),
               resultSet.getString("office"),
               resultSet.getString("LastName")));
         }
         return results;
      } catch (Exception e) {
         // TODO: handle exception
      }
      return null;
   }

}