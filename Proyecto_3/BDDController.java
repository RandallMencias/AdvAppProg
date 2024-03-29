import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class BDDController {
    //importar variables del FXML
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
    @FXML
    private TextField TFModifyID;

    //Variables para manejar las listviews
    private createQueries fQueries = new createQueries();

    private final ObservableList<faculty> facultyList = FXCollections.observableArrayList();
    private final ObservableList<courses> courseList = FXCollections.observableArrayList();

    //modificar valores del listView
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

    //Modificar TextFields
    private void emptyFacultyTF() {
        InsertName.setText("");
        InsertOffice.setText("");
    }

    private void emptyCourseTF() {
        InsertCourse.setText("");
        InsertCourseID.setText("");
    }

    //Boton para ingresar un profesor en Faculty
    @FXML
    void InserProfessor(ActionEvent event) {
        try {
            //validar que se tenga todo para agregar profesor
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
    //Boton para ingresar una clase nueva
    @FXML
    void btnInsertClass(ActionEvent event) {
        try {
            //validar que se tengan los datos para añadirla
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
    //boton para eliminar una clase
    @FXML
    void btnRemoveClass(ActionEvent event) {
        //verificar que se haya ingresado el ID para eliminar
        if (InsertCourseID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingrese el ID de la clase a eliminar");
        } else {
            //eliminar 
            fQueries.deletecourse(InsertCourseID.getText());
            UpdateCourseEntries();
            UpdateFacultyEntries();
        }
    }
    //Boton para eliminar profesor
    @FXML
    void btnRemoveProfessor(ActionEvent event) {
        //verificar que se haya ingresado el ID a eliminar
        if (InsertID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingrese el ID del profesor a eliminar");
        } else {
            //mensaje que indica que se van a eliminar de las dos listas
            int value = JOptionPane.showConfirmDialog(null,
                    "Seguro desea eliminar el profesor?\nTambien se eliminaran sus cursos", "Ventana de confirmacion",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            //eliminar de las dos listas
            if (value == JOptionPane.YES_OPTION) {
                fQueries.deletefaculty(InsertID.getText());
                UpdateCourseEntries();
                UpdateFacultyEntries();
            }
        }
    }
    //Boton para modificar el curso
    @FXML
    void btnModifyCourse(ActionEvent event) {
        if (InsertCourseID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el codigo de la clase a modificar");
        } else {
            if (TFModifyID.getText().isEmpty()) {
                fQueries.updatecourse(InsertCourseID.getText(), InsertCourse.getText());
            } else{
                fQueries.updatecourse(InsertCourseID.getText(), InsertCourse.getText(),TFModifyID.getText());
            }
            UpdateCourseEntries();
        }

    }
    //Boton para modificar Profesores
    @FXML
    void btnModifyFaculty(ActionEvent event) {
        if (InsertID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el ID del profesor a modificar");
        } else {
            if (InsertName.getText().isEmpty() && TFModifyID.getText().isEmpty()) {
                fQueries.updatefaculty(InsertID.getText(), InsertOffice.getText());
            } else if (TFModifyID.getText().isEmpty()) {
                fQueries.updatefaculty(InsertID.getText(), InsertOffice.getText(), InsertName.getText());
            } else {
                fQueries.updatefaculty(InsertID.getText(), InsertOffice.getText(), InsertName.getText(),
                        TFModifyID.getText());
            }
            UpdateFacultyEntries();
            emptyFacultyTF();
            UpdateCourseEntries();
        }
    }
    //Boton para mostrar clases ordenadas por profesor
    @FXML
    void btnViewProfessor(ActionEvent event) {
        LVperProfessor.getItems().clear();
        if (InsertToSort.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingere el ID profesor a buscar");
        } else {
            LVperProfessor.getItems().addAll(fQueries.getcoursesperfaculty(InsertToSort.getText()));
        }
    }
}
