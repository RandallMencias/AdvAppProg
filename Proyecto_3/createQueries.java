import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class createQueries {
   public static void main(String args[]) {
      try {
         Connection connection = DriverManager.getConnection(
                  "jdbc:derby:Datos;", "Ran", "Ran");
            Statement statement = connection.createStatement();
      }catch (Exception e){

      }
      
   }
}