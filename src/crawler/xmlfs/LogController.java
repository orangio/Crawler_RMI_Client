package crawler.xmlfs;

import crawler.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Filip on 10.04.2017.
 */


public class LogController implements Initializable,Serializable {

    @FXML
    public ListView listView;

    private  ObservableList<String> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        data = FXCollections.observableArrayList();
        listView.setItems(data);

    }

    public void addData(String type, Student student) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

        switch (type) {
            case "add":
                data.add(simpleDateFormat.format(date) + "        [ADDED]          " + "name:"+student.getFirstName() + " surname:"+ student.getLastName()+ " mark:"+ student.getMark());
                break;
            case "del":
                data.add(simpleDateFormat.format(date) + "        [REMOVED]          " + "name:"+student.getFirstName() + " surname:"+ student.getLastName()+ " mark:"+ student.getMark());
                break;
            case "no":
                data.add(simpleDateFormat.format(date) + "nothing changed" );
                break;
        }
    }
}
