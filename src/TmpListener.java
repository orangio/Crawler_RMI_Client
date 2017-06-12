import crawler.CrawlerEventListener;
import crawler.Student;
import crawler.xmlfs.HistogramController;
import crawler.xmlfs.LogController;
import crawler.xmlfs.StudentsController;
import javafx.fxml.FXML;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Filip on 30.05.2017.
 */
public class TmpListener extends UnicastRemoteObject implements Serializable, CrawlerEventListener{

    public transient StudentsController tabSController;

    public transient LogController tabLController;

    public transient HistogramController tabHController;

    protected TmpListener()throws RemoteException{
        super();
    }
    public void setTabSController(StudentsController tabSController) {
        this.tabSController = tabSController;
    }

    public void setTabLController(LogController tabLController) {
        this.tabLController = tabLController;
    }

    public void setTabHController(HistogramController tabHController) {
        this.tabHController = tabHController;
    }

    @Override
    public void onStudentAdded(Student a) {
        tabSController.addStudent(a);
        tabLController.addData("add",a);
        tabHController.addData(a.getMark());
        //System.out.println("lol");
    }

    @Override
    public void onStudentDeleted(Student a) {

        tabSController.delStudent(a);
        tabLController.addData("add",a);
        tabHController.delData(a.getMark());
    }

    @Override
    public void onNoChange() {

    }
}
