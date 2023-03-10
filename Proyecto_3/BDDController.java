import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ListView;

public class BDDController {
    @FXML private TextField InsertCourse;
    @FXML private TextField InsertCourseID;
    @FXML private TextField InsertID;
    @FXML private TextField InsertName;
    @FXML private TextField InsertOffice;
    @FXML private Button btnInsertClass;
    @FXML private Button btnInsertProfessor;
    @FXML private Button btnRemoveClass;
    @FXML private Button btnRemoveProfessor;
    @FXML private AnchorPane root;
    @FXML private ListView<Data> LV;

    private final createQueries Querie = new createQueries();
}
