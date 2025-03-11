package controller.generalInfo;

import data.DisplayDate;
import hibernate.entity.StudentJob;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import services.TableService;
import start.zine.HelloApplication;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class LaborActivityController extends HelloApplication implements Initializable {

    @FXML
    private TableColumn<StudentJob, Date> EndDate;

    @FXML
    private TableColumn<StudentJob, String> MiddleNameColumn;

    @FXML
    private TableColumn<StudentJob, String> NameColumn;

    @FXML
    private TableColumn<StudentJob, Integer> NumberColumn;

    @FXML
    private TableColumn<StudentJob, String> Place;

    @FXML
    private TableColumn<StudentJob, String> Position;

    @FXML
    private TableColumn<StudentJob, Date> StartDate;

    @FXML
    private TableView<StudentJob> StudentJobTable;

    @FXML
    private TableColumn<StudentJob, String> SurnameColumn;


    public void back(ActionEvent event) {
        switchWorkStudPage(event);
    }

    public void displayStudentJob() {
        ObservableList<StudentJob> studentJobs = DisplayDate.tableStudentJob();
        TableService.setDataInStudentJobTable(studentJobs,StudentJobTable,NumberColumn,NameColumn,SurnameColumn,MiddleNameColumn,StartDate,EndDate,Place,Position);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayStudentJob();
    }
}
