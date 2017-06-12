package crawler.xmlfs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;

import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by Filip on 10.04.2017.
 */
public class HistogramController implements Initializable,Serializable {

    @FXML
    private CategoryAxis categoryAxis;
    @FXML
    private NumberAxis numberAxis;
    @FXML
    private BarChart barChart;

    private ObservableList<XYChart.Data<String, Integer>> marks = FXCollections.observableArrayList();
    private XYChart.Series<String, Integer> students = new XYChart.Series<String, Integer>(marks);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                if (object.intValue() != object.doubleValue())
                    return "";
                return "" + (object.intValue());
            }

            @Override
            public Number fromString(String string) {
                Number val = Double.parseDouble(string);
                return val.intValue();
            }
        });
        marks.addAll(
                new XYChart.Data<String, Integer>(Double.toString(2.0), 0),
                new XYChart.Data<String, Integer>(Double.toString(3.0), 0),
                new XYChart.Data<String, Integer>(Double.toString(3.5), 0),
                new XYChart.Data<String, Integer>(Double.toString(4.0), 0),
                new XYChart.Data<String, Integer>(Double.toString(4.5), 0),
                new XYChart.Data<String, Integer>(Double.toString(5.0), 0)
        );
        barChart.getData().addAll(students);
    }

    public synchronized void addData(double d)
    {
        for(XYChart.Data<String,Integer> tmp : marks)
        {
            int pom = tmp.getYValue();
            {
                if(Double.toString(d).equals(tmp.getXValue())){
                    tmp.setYValue(++pom);
                }
            }
        }

    }

    public synchronized void delData(double d)
    {
        for(XYChart.Data<String,Integer> tmp : marks)
        {
            int pom = tmp.getYValue();
            {
                if(Double.toString(d).equals(tmp.getXValue())){
                    tmp.setYValue(--pom);
                }
            }
        }

    }
    public ObservableList<XYChart.Data<String, Integer>> getMarks() {
        return marks;
    }
    public void setMarks(ObservableList<XYChart.Data<String, Integer>> marks) {
        this.marks = marks;
    }
    public XYChart.Series<String, Integer> getStudents() {
        return students;
    }
    public void setStudents(XYChart.Series<String, Integer> students) {
        this.students = students;
    }

}



/*
    @FXML
    private BarChart<String,Number> barChart;
    @FXML
    private  CategoryAxis categoryAxis;
    @FXML
    private  NumberAxis numberAxis;

    private ObservableList<XYChart.Data<Double, Double>> data;
    XYChart.Series series1=new XYChart.Series();
    private final int[] marks = new int[8];
    final String[] labels={"2,0","2,5","3,0","3,5","4,0","4,5","5,0"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i=0;i<7;i++)
            marks[i]=0;
        categoryAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(labels)));
        final XYChart.Series<String,Number> series1= new XYChart.Series<String,Number>();
        for(int i=0;i<7;i++)
        series1.getData().add(new XYChart.Data<String,Number>(labels[i],marks[i]));

    }
    private synchronized void setData()
    {
        /*
        series1.getData().add(new XYChart.Data("2,0",marks[0]));
        series1.getData().add(new XYChart.Data("2,5",marks[1]));
        series1.getData().add(new XYChart.Data("3,0",marks[2]));
        series1.getData().add(new XYChart.Data("3,5",marks[3]));
        series1.getData().add(new XYChart.Data("4,0",marks[4]));
        series1.getData().add(new XYChart.Data("4,5",marks[5]));
        series1.getData().add(new XYChart.Data("5,0",marks[6]));

        series1.setData(data);
        barChart.getData().addAll(series1);

    }
    public synchronized void addData(double s) {
        double ds=s*10;
        s=s-2;
        double n;
        n=s/0.5;
        int i;
        i=(int)n;
        marks[i]++;
        series1.getData().add(new XYChart.Data<String,Number>(labels[i],marks[i]));
        barChart.getData().add(series1);

    }
    public synchronized void delData(double s) {

        s=s-2;
        double n;
        n=s/0.5;
        int i;
        i=(int)n;
        marks[i]--;
        series1.getData().add(new XYChart.Data<String,Number>(labels[i],marks[i]));
        barChart.getData().add(series1);
    }
    */
