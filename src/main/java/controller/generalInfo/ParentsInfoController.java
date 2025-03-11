package controller.generalInfo;

import data.DisplayDate;
import hibernate.entity.EducationInfo;
import hibernate.entity.MilitaryService;
import hibernate.entity.StudentParents;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import services.TableService;
import start.zine.HelloApplication;

import java.net.URL;
import java.util.ResourceBundle;

public class ParentsInfoController extends HelloApplication implements Initializable {

    @FXML
    private TableColumn<StudentParents, String> MiddleNameColumn;

    @FXML
    private TableColumn<StudentParents, String> NameColumn;

    @FXML
    private TableColumn<StudentParents, String> Note;

    @FXML
    private TableColumn<StudentParents, Integer> NumberColumn;

    @FXML
    private TableColumn<StudentParents, String> PIPFatherColumn;

    @FXML
    private TableColumn<StudentParents, String> PIPMother;

    @FXML
    private TableColumn<StudentParents, String> PhoneFather;

    @FXML
    private TableColumn<StudentParents, String> PhoneMother;

    @FXML
    private TableView<StudentParents> StudentParentsTable;

    @FXML
    private TableColumn<StudentParents, String> SurnameColumn;


    public void back(ActionEvent event) {
        switchWorkStudPage(event);
    }

    public void displayStudentParents(){
        ObservableList<StudentParents> studentParents = DisplayDate.tableStudentParents();
        TableService.setDataInStudentParentsTable(studentParents,StudentParentsTable,NumberColumn,NameColumn,SurnameColumn,MiddleNameColumn,PIPFatherColumn,PIPMother,PhoneFather,PhoneMother,Note);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayStudentParents();
    }
}
