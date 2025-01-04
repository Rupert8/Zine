package controller;

import data.DisplayDate;
import hibernate.entity.Curators;
import hibernate.entity.StudentInfo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import start.zine.HelloApplication;

import java.sql.Date;
import java.util.List;

import static controller.LoginController.curatorEmail;


public class WorkGroupController extends HelloApplication {
    @FXML
    private TableView<StudentInfo> GroupTable;

    @FXML
    private TableColumn<StudentInfo, Integer> NumberColumn;
    @FXML
    private TableColumn<StudentInfo, String> SurnameColumn;
    @FXML
    private TableColumn<StudentInfo, String> NameColumn;
    @FXML
    private TableColumn<StudentInfo, java.sql.Date> DateBirthDayColumn;
    @FXML
    private TableColumn<StudentInfo, Long> PhoneNumberColumn;
    @FXML
    private TableColumn<StudentInfo, String> AddressColumn;
    @FXML
    private TableColumn<StudentInfo, String> MiddleNameColumn;

    @FXML
    private Label CuratorName;

    public static String curatorFullName;

    private static ObservableList<StudentInfo> list;

    public void switchPlanPage(ActionEvent event) {
        switchWorkPlanPage(event);
    }

    public void switchStudPage(ActionEvent event) {
        switchWorkStudPage(event);
    }

    private void setDataInGroupTable(ObservableList<StudentInfo> studentInfo){
        GroupTable.setItems(studentInfo);

        NumberColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, Integer>("id"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("name"));
        MiddleNameColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("middleName"));
        SurnameColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("surname"));
        DateBirthDayColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, Date>("date_of_birth"));
        PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, Long>("phoneNumber"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("address"));
    }

    private void displayGroupData(){
        String hql = "FROM StudentInfo WHERE groupName = :groupName";
        Curators curatorName = DisplayDate.getCuratorByUserEmail(curatorEmail);
        String groupName = curatorName.getGroup();
        list = DisplayDate.getDataStudentInfo(hql,groupName);
        setDataInGroupTable(list);
    }

    private void displayCuratorName(){
        if(curatorFullName == null){
            Curators curatorName = DisplayDate.getCuratorByUserEmail(curatorEmail);
            curatorFullName = curatorName.getSurname() + " " + curatorName.getName() + " " + curatorName.getMiddleName();
            CuratorName.setText(curatorFullName);
        }else{
            CuratorName.setText(curatorFullName);
        }
    }

    public void initialize() {
        displayGroupData();
        displayCuratorName();
    }
}