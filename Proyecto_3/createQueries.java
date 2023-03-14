import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;

public class createQueries {

   private PreparedStatement showFacultyTable;
   private PreparedStatement showCourseTable;
   private Connection connection;
   private Statement statement;
   private List<faculty> facultylist;
   private List<courses> courseslist;

   // constructor
   public createQueries() {
      facultylist = new ArrayList<faculty>();
      courseslist = new ArrayList<courses>();

      try {
         connection = DriverManager.getConnection("jdbc:derby:Datos;", "Ran", "Ran");
         System.out.println("Connection successful");
         statement = connection.createStatement();

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void getTables() {
      try {

         DatabaseMetaData metaData = connection.getMetaData();

         // Get a ResultSet containing information about all tables in the database
         ResultSet tables = metaData.getTables(null, null, "%", new String[] { "TABLE" });

         // Iterate over the ResultSet and print out the names of the tables
         while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            System.out.println(tableName);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void getColumns(String table) {
      try {
         DatabaseMetaData metaData = connection.getMetaData();

         ResultSet columns = metaData.getColumns(null, null, table, null);

         // Iterate over the ResultSet and print out the names of the tables
         while (columns.next()) {
            String columnName = columns.getString("COLUMN_NAME");
            System.out.println(columnName);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public List<faculty> getAllFaculty() {

      ResultSet resultSet = null;

      try {
         // select all of the entries in the FACULTY table
         showFacultyTable = connection.prepareStatement("SELECT * FROM FACULTY");
         resultSet = showFacultyTable.executeQuery();

         while (resultSet.next()) {
            facultylist.add(new faculty(resultSet.getString("FACULTY_ID"), resultSet.getString("FACULTY_NAME"),
                  resultSet.getString("OFFICE")));
         }
      } catch (SQLException sqlException) {
         sqlException.printStackTrace();
      } finally {
         try {
            resultSet.close();
         } catch (SQLException sqlException) {
            sqlException.printStackTrace();
         }
      }

      return facultylist;
   }

   public List<faculty> getFaculty() {
      return facultylist;
   }

   public List<courses> getCourses() {
      courseslist.clear();
      ResultSet resultSet = null;
      try {
         // select all of the entries in the COURSES table
         showCourseTable = connection.prepareStatement("SELECT * FROM COURSES ORDER BY COURSE");
         resultSet = showCourseTable.executeQuery();

         while (resultSet.next()) {
            courseslist.add(new courses(resultSet.getString("COURSE_ID"), resultSet.getString("COURSE"),
                  resultSet.getString("FACULTY_ID")));
         }
      } catch (SQLException sqlException) {
         sqlException.printStackTrace();
      }
      return courseslist;
   }

   // get a list of courses
   public List<courses> getAllCourses() {

      ResultSet resultSet = null;

      try {
         // select all of the entries in the COURSES table
         showCourseTable = connection.prepareStatement("SELECT * FROM COURSES ORDER BY COURSE");
         resultSet = showCourseTable.executeQuery();

         while (resultSet.next()) {
            courseslist.add(new courses(resultSet.getString("COURSE_ID"), resultSet.getString("COURSE"),
                  resultSet.getString("FACULTY_ID")));
         }
      } catch (SQLException sqlException) {
         sqlException.printStackTrace();
      } finally {
         try {
            resultSet.close();
         } catch (SQLException sqlException) {
            sqlException.printStackTrace();
         }
      }

      return courseslist;
   }

   // ***********************************************Adders***********************************************

   public void addfaculty(faculty faculty) {
      for (faculty efaculty : facultylist) {
         if (efaculty.getID().equals(faculty.getID()) || efaculty.getOffice().equals(faculty.getOffice())) {
            JOptionPane.showMessageDialog(null, "No se puede agregar el profesor, esta repetido");
            throw new IllegalArgumentException("No se puede agregar el profesor, esta repetido");
         }
      }

      try {
         statement.executeUpdate("INSERT INTO FACULTY VALUES ('" + faculty.getID() + "','" + faculty.getName() + "','"
               + faculty.getOffice() + "')");
         facultylist.add(faculty);
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public void addcourse(courses course) throws IllegalArgumentException {
      for (courses ecourse : courseslist) {
         if (ecourse.getCourse_id().equals(course.getCourse_id())) {
            JOptionPane.showMessageDialog(null, "No se puede agregar el curso, esta repetido");
            throw new IllegalArgumentException("No se puede agregar el curso, esta repetido");
         }
      }

      boolean flag = false;
      for (faculty faculty : facultylist) {
         if (faculty.getID().equals(course.getFaculty_id())) {
            flag = true;
         }
      }
      if (!flag) {
         JOptionPane.showMessageDialog(null, "No se ha agregado profesor para este curso");
         throw new IllegalArgumentException("No tiene profesor");
      }
      try {
         statement.executeUpdate("INSERT INTO COURSES VALUES ('" + course.getCourse_id() + "','" + course.getCourse()
               + "','" + course.getFaculty_id() + "')");
         courseslist.add(course);
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   // **********************************************************deleters***************************************************************

   public void deletefaculty(String id) {
      try {
         statement.executeUpdate("DELETE FROM COURSES WHERE FACULTY_ID = '" + id + "'");
         statement.executeUpdate("DELETE FROM FACULTY WHERE FACULTY_ID = '" + id + "'");
         
         updatelists();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void deletecourse(String id) {
      try {
         statement.executeUpdate("DELETE FROM COURSES WHERE COURSE_ID = '" + id + "'");
         updatelists();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   // **********************************************************updaters***************************************************************
   public void updatefaculty(String faculty_id, String... str) {
      // office, faculty_name, faculty_id
      try {
         if (str.length == 1) {
            statement.executeUpdate("UPDATE FACULTY SET OFFICE='" + str[0] + "' WHERE FACULTY_ID='"+ faculty_id + "'");
         }

         if (str.length == 2) {
            statement.executeUpdate("UPDATE FACULTY SET OFFICE='" + str[0] + "' WHERE FACULTY_ID='"+ faculty_id + "'");
            statement.executeUpdate("UPDATE FACULTY SET FACULTY_NAME='" + str[1] + "' WHERE FACULTY_ID='"+ faculty_id + "'");
         }
         if (str.length == 3) {
            statement.executeUpdate("UPDATE FACULTY SET OFFICE='" + str[0] + "' WHERE FACULTY_ID='"+ faculty_id + "'");
            statement.executeUpdate("UPDATE FACULTY SET FACULTY_NAME='" + str[1] + "' WHERE FACULTY_ID='"+ faculty_id + "'");
            statement.executeUpdate("UPDATE FACULTY SET FACULTY_ID='" + str[2] + "' WHERE FACULTY_ID='"+ faculty_id + "'");
            statement.executeUpdate("UPDATE COURSES SET FACULTY_ID='" + str[2] + "' WHERE FACULTY_ID='"+ faculty_id + "'");
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }
      updatelists();
   }

   public void updatecourse(String course_id, String... str) {
      // faculty_id, course, course_id
      try {
         if (str.length == 1) {
            statement.executeUpdate("UPDATE COURSES SET COURSE='" + str[0] + "' WHERE COURSE_ID='"+ course_id + "'");
         }
         if (str.length == 2) {
            statement.executeUpdate("UPDATE COURSES SET COURSE='" + str[0] + "' WHERE COURSE_ID='"+ course_id + "'");
            statement.executeUpdate("UPDATE COURSES SET COURSE_ID='" + str[1] + "' WHERE COURSE_ID='"+ course_id + "'");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   // ******************************consultas***************************************

   public ArrayList<courses> getcoursesperfaculty(String id) {
      ArrayList<courses> coursesperfaculty = new ArrayList<courses>();
      ResultSet resultSet = null;

      try {
         // select all of the entries in the COURSES table
         showCourseTable = connection.prepareStatement("SELECT * FROM COURSES WHERE FACULTY_ID = '" + id + "'");
         resultSet = showCourseTable.executeQuery();

         while (resultSet.next()) {
            coursesperfaculty.add(new courses(resultSet.getString("COURSE_ID"), resultSet.getString("COURSE"),
                  resultSet.getString("FACULTY_ID")));
         }
      } catch (SQLException sqlException) {
         sqlException.printStackTrace();
      }

      return coursesperfaculty;
   }

   public ArrayList<courses> getCommonWords(String similar) {
      ArrayList<courses> sorted = new ArrayList<>();
      ResultSet resultSet = null;
      try {
         showCourseTable = connection.prepareStatement("SELECT * FROM COURSES WHERE COURSE LIKE '" + similar + "'");
         resultSet = showCourseTable.executeQuery();
         while (resultSet.next()) {
            sorted.add(new courses(resultSet.getString("COURSE_ID"), resultSet.getString("COURSE"),
                  resultSet.getString("FACULTY_ID")));
         }
      } catch (Exception e) {
      }
      return sorted;
   }

   private void updatelists() {
      facultylist.clear();
      courseslist.clear();
      getAllCourses();
      getAllFaculty();
   }

}