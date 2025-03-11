package controller.generalInfo;

import data.DisplayDate;
import hibernate.entity.EducationInfo;
import hibernate.entity.StudentInfo;
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

public class EducationInfoController extends HelloApplication implements Initializable {

    @FXML
    private TableColumn<EducationInfo, String> SchoolName;

    @FXML
    private TableColumn<EducationInfo, Date> EndDate;

    @FXML
    private TableView<EducationInfo> EducationInfoTable;

    @FXML
    private TableColumn<EducationInfo, String> MiddleNameColumn;

    @FXML
    private TableColumn<EducationInfo, String> NameColumn;

    @FXML
    private TableColumn<EducationInfo, Integer> NumberColumn;

    @FXML
    private TableColumn<EducationInfo, Float> AverageGrade;

    @FXML
    private TableColumn<EducationInfo, String> SurnameColumn;

    public void back(ActionEvent event) {
        switchWorkStudPage(event);
    }

    public void displayEducationInfo(){
        ObservableList<EducationInfo> educationInfo = DisplayDate.tableEducationInfo();
        TableService.setDataInEducationInfoTable(educationInfo,EducationInfoTable,NumberColumn,NameColumn,SurnameColumn,MiddleNameColumn,EndDate,SchoolName,AverageGrade);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayEducationInfo();
    }
}
