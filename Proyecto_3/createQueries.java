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
         // Establish a connection to the database

         // Get metadata about the database
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
         // Get metadata about the database
         DatabaseMetaData metaData = connection.getMetaData();

         // Get a ResultSet containing information about all tables in the database
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
      return courseslist;
   }

   // get a list of courses
   public List<courses> getAllCourses() {

      ResultSet resultSet = null;

      try {
         // select all of the entries in the COURSES table
         showCourseTable = connection.prepareStatement("SELECT * FROM COURSES");
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
         // TODO Auto-generated catch block
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
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

   // **********************************************************deleters***************************************************************

   public void deletefaculty(String id) {
      try {
         statement.executeUpdate("DELETE FROM FACULTY WHERE FACULTY_ID = '" + id + "'");
         statement.executeUpdate("DELETE FROM COURSES WHERE FACULTY_ID = '" + id + "'");
         facultylist.clear();
         courseslist.clear();
         getAllCourses();
         getAllFaculty();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void deletecourse(String id) {
      try {
         statement.executeUpdate("DELETE FROM COURSES WHERE FACULTY_ID = '" + id + "'");
         facultylist.clear();
         courseslist.clear();
         getAllCourses();
         getAllFaculty();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   // **********************************************************updaters***************************************************************

   public void updatefaculty(faculty faculty, String id) {
      // usar faculty id
      try {
         String sql = "UPDATE FACULTY  SET FACULTY_ID = ?, FACULTY_NAME = ?, OFFICE = ? WHERE FACULTY_ID = ?";
         PreparedStatement pStatement = connection.prepareStatement(sql);
         pStatement.setString(1, faculty.getID());
         pStatement.setString(2, faculty.getName());
         pStatement.setString(3, faculty.getOffice());
         pStatement.setString(4, id);
         pStatement.executeUpdate();
         facultylist.clear();
         courseslist.clear();
         getAllCourses();
         getAllFaculty();

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void updatecourse(courses course, String id) {
      // usar course id
      // update a course based on the course id
      boolean flag = false;
      for (faculty faculty : facultylist) {
         if (faculty.getID().equals(course.getFaculty_id())) {
            flag = true;
         }
      }
      if (!flag) {
         throw new IllegalArgumentException("No tiene profesor");
      }

      try {
         String sql = "UPDATE COURSES SET COURSE_ID = ?, COURSE = ?, FACULTY_ID = ? WHERE COURSE_ID = ?";
         System.out.println("a");
         PreparedStatement pStatement = connection.prepareStatement(sql);
         pStatement.setString(1, course.getCourse_id());
         pStatement.setString(2, course.getCourse());
         pStatement.setString(3, course.getFaculty_id());
         pStatement.setString(4, id);
         pStatement.executeUpdate();
         facultylist.clear();
         courseslist.clear();
         getAllCourses();
         getAllFaculty();
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

}