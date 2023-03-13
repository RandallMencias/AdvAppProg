import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class BDDController {
    @FXML
    private ListView<courses> LVCourse;
    @FXML
    private ListView<faculty> LVFaculty;
    @FXML
    private TextField InsertCourse;
    @FXML
    private TextField InsertCourseID;
    @FXML
    private TextField InsertID;
    @FXML
    private TextField InsertName;
    @FXML
    private TextField InsertOffice;
    @FXML
    private AnchorPane root;

    private createQueries fQueries = new createQueries();

    private final ObservableList<faculty> facultyList = FXCollections.observableArrayList();
    private final ObservableList<courses> courseList = FXCollections.observableArrayList();

    public void initialize() {
        LVFaculty.setItems(facultyList);
        LVCourse.setItems(courseList);
        getAllEntries();
    }

    private void getAllEntries() {
        facultyList.setAll(fQueries.getAllFaculty());
        courseList.setAll(fQueries.getAllCourses());
        selectFirstEntry();
    }

    private void removeFacultyEntries() {
        // facultyList.clear();
        facultyList.setAll(fQueries.getFaculty());

    }

    private void removeCourseEntries() {
        courseList.clear();
    }

    private void selectFirstEntry() {
        LVFaculty.getSelectionModel().selectFirst();
        LVCourse.getSelectionModel().selectFirst();
    }

    @FXML
    void InserProfessor(ActionEvent event) {
        try {
            if (InsertName.getText().isEmpty() || InsertOffice.getText().isEmpty() || InsertID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese todos los datos:\n    -Nombre\n    -Oficina \n    -Id");
            } else {
                faculty facultad = new faculty(InsertID.getText(), InsertName.getText(), InsertOffice.getText());
                fQueries.addfaculty(facultad);
                facultyList.add(facultad);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        // removeFacultyEntries();
    }

    @FXML
    void btnInsertClass(ActionEvent event) {
        try {
            if (InsertCourse.getText().isEmpty() || InsertCourseID.getText().isEmpty()
                    || InsertID.getText().isEmpty()) {
                // Ingresar validacion que el profesor con ese ID ya exista
                JOptionPane.showMessageDialog(null,
                        "Ingrese todos los datos \n    -Curso\n    -ID de la facutlad \n    -Id");
            } else {
                courses curso = new courses(InsertCourseID.getText(), InsertCourse.getText(), InsertID.getText());
                fQueries.addcourse(curso);
                courseList.add(curso);
                System.out.println("Guarde un curso");
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void btnRemoveClass(ActionEvent event) {
        fQueries.deletecourse(InsertID.getText());
        System.out.println(fQueries.getCourses());
    }

    @FXML
    void btnRemoveProfessor(ActionEvent event) {
        fQueries.deletefaculty(InsertID.getText());
        /*
         * facultyList.clear();
         * facultyList.setAll(fQueries.getFaculty());
         * facultyList.add(new faculty("A","A", "A"));
         */
    }

    // ingresar Updaters de faculty/course usando ID
    @FXML
    void btnModifyCourse(ActionEvent event) {
        fQueries.updatecourse(new courses(InsertCourseID.getText(), InsertCourse.getText(), InsertID.getText()), InsertID.getText());
    }

    @FXML
    void btnModifyFaculty(ActionEvent event) {
        fQueries.updatefaculty(new faculty(InsertID.getText(), InsertName.getText(), InsertOffice.getText()), InsertID.getText());
    }
}
