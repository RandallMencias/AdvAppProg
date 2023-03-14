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
    @FXML
    private ListView<courses> LVperProfessor;
    @FXML
    private TextField InsertToSort;

    private createQueries fQueries = new createQueries();

    private final ObservableList<faculty> facultyList = FXCollections.observableArrayList();
    private final ObservableList<courses> courseList = FXCollections.observableArrayList();
    private final ObservableList<courses> SortByProfessor = FXCollections.observableArrayList();

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

    private void UpdateFacultyEntries() {
        facultyList.clear();
        facultyList.setAll(fQueries.getFaculty());
    }

    private void UpdateCourseEntries() {
        courseList.clear();
        courseList.setAll(fQueries.getCourses());
    }

    private void selectFirstEntry() {
        LVFaculty.getSelectionModel().selectFirst();
        LVCourse.getSelectionModel().selectFirst();

    }

    private void emptyFacultyTF() {
        InsertName.setText("");
        InsertOffice.setText("");
    }

    private void emptyCourseTF() {
        InsertCourse.setText("");
        InsertCourseID.setText("");
    }

    @FXML
    void InserProfessor(ActionEvent event) {
        try {
            if (InsertName.getText().isEmpty() || InsertOffice.getText().isEmpty() || InsertID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese todos los datos:\n    -Nombre\n    -Oficina \n    -Id");
            } else {
                fQueries.addfaculty(new faculty(InsertID.getText(), InsertName.getText(), InsertOffice.getText()));
                UpdateFacultyEntries();
                emptyFacultyTF();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
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
                fQueries.addcourse(new courses(InsertCourseID.getText(), InsertCourse.getText(), InsertID.getText()));
                UpdateCourseEntries();
                emptyCourseTF();
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void btnRemoveClass(ActionEvent event) {
        if (InsertCourseID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingrese el ID de la clase a eliminar");
        } else {
            fQueries.deletecourse(InsertCourseID.getText());
            UpdateCourseEntries();
            UpdateFacultyEntries();
        }
    }

    @FXML
    void btnRemoveProfessor(ActionEvent event) {
        if (InsertID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingrese el ID del profesor a eliminar");
        } else {
            int value = JOptionPane.showConfirmDialog(null,
                    "Seguro desea eliminar el profesor?\nTambien se eliminaran sus cursos", "Ventana de confirmacion",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (value == JOptionPane.YES_OPTION) {
                fQueries.deletefaculty(InsertID.getText());
                UpdateCourseEntries();
                UpdateFacultyEntries();
            }
        }
    }

    @FXML
    void btnModifyCourse(ActionEvent event) {
        if (InsertCourseID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el codigo de la clase a modificar");
        } else {
            fQueries.updatecourse(new courses(InsertCourseID.getText(), InsertCourse.getText(), InsertID.getText()),
                    InsertCourseID.getText());
            UpdateCourseEntries();
        }

    }

    @FXML
    void btnModifyFaculty(ActionEvent event) {
        if (InsertID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el ID del profesor a modificar");
        } else {
            fQueries.updatefaculty(new faculty(InsertID.getText(), InsertName.getText(), InsertOffice.getText()),
                    InsertID.getText());
            UpdateFacultyEntries();
            emptyFacultyTF();
            InsertID.setText("");
        }
    }

    @FXML
    void btnViewProfessor(ActionEvent event) {
        LVperProfessor.getItems().clear();
        if(InsertToSort.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Ingere el ID profesor a buscar");
        }else{
            
            LVperProfessor.getItems().addAll(fQueries.getcoursesperfaculty(InsertToSort.getText()));
        }
    }
}
