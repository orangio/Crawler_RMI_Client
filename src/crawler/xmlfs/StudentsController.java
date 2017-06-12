package crawler.xmlfs;

import crawler.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Filip on 10.04.2017.
 */
public class StudentsController implements Initializable,Serializable{

    @FXML
    public TableView tableView;
    ObservableList<Student> localStudents = FXCollections.observableArrayList();
    @FXML
    private TableColumn markColumn;
    @FXML
    private TableColumn firstNameColumn;
    @FXML
    private TableColumn lastNameColumn;
    @FXML
    private TableColumn ageColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //markColumn = new TableColumn("Mark");
        //firstNameColumn = new TableColumn("First Name");
        //lastNameColumn = new TableColumn("Last Name");
        //ageColumn = new TableColumn("Age");

        markColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("mark"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("age"));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //tableView.setContent


        tableView.setItems(localStudents);
    }

    public void addStudent(Student a)
    {
        localStudents.add(a);
        tableView.setItems(localStudents);
    }

    public void delStudent(Student a)
    {
        localStudents.remove(a);
        tableView.setItems(localStudents);
    }
}
