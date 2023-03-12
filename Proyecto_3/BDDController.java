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
    private ListView<?> LVCourse;

    @FXML
    private ListView<Data> LVFaculty;

    @FXML
    private AnchorPane root;

    //private Data data;
    private final ObservableList<Data> facultyList = FXCollections.observableArrayList();
    private final createQueries FQueries = new createQueries();

    /*public void initialize() {
        LVFaculty.setItems(facultyList); // bind to contactsList
        facultyList.setAll(FQueries.getFacultyElements());

        // when ListView selection changes, display selected person's data
        LVFaculty.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    displayContact(newValue);
                });
    }*/

    /*public void updateTFFaculty() {
        InsertName.setText(data.getName());
        InsertOffice.setText(data.getOffice());
        InsertID.setText(data.getID());
    }*/
    /*private void displayContact(Data Data) {
        if (Data != null) {
            InsertName.setText(Data.getName());
            InsertOffice.setText(Data.getOffice());
            InsertID.setText(Data.getID());
        }
        else {
            InsertName.clear();
            InsertOffice.clear();
            InsertID.clear();
        }
     }*/

    @FXML
    void InserProfessor(ActionEvent event) {
        try {
            if (InsertName.getText().isEmpty() || InsertOffice.getText().isEmpty() || InsertID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese todos los datos:\n    -Nombre\n    -Oficina \n    -Id");
            } else {

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
                // ingresar elementos en listade la base de datos Faculty
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void btnRemoveClass(ActionEvent event) {
        if (InsertCourse.getText().isEmpty() || InsertCourseID.getText().isEmpty() || InsertID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese todos los datos");
        }
    }

    @FXML
    void btnRemoveProfessor(ActionEvent event) {

    }
}
