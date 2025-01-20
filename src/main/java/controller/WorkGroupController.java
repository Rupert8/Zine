package controller;

import data.DeleteData;
import data.DisplayDate;
import data.SearchStudentData;
import hibernate.entity.Curators;
import hibernate.entity.StudentInfo;
import hibernate.entity.WorkPlan;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import start.zine.HelloApplication;

import java.sql.Date;
import java.util.List;

import static controller.LoginController.curatorEmail;
import static controller.admin.AdminMainController.studentGroupName;
import static controller.admin.AdminMainController.*;

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
    private Button DeleteStudentButton,UpdateStudentButton;

    @FXML
    private Label CuratorName;
    @FXML
    private Button extendedInfo;

    public static String curatorFullName;
    public static int studentWorkId;
    public static String studentWorkName;
    public static String studentWorkSurname;
    public static String studentWorkMiddleName;
    public static String studentWorkAddress;
    public static String studentWorkPhoneNumber;
    public static Date studentWorkBirthDay;

    private static ObservableList<StudentInfo> list;

    public static Dialog<Boolean> saveDialog;

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

    private void selectRows(){
        GroupTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                StudentInfo studentInfo = newValue;
                // admin field
                studentName = studentInfo.getName();
                studentSurname = studentInfo.getSurname();
                studentMiddleName = studentInfo.getMiddleName();
                studentAddress = studentInfo.getAddress();
                studentPhoneNumber = studentInfo.getPhoneNumber();
                studentGroupName = studentInfo.getGroupName();
                studentId = SearchStudentData.getIdStudentForAdmin(studentAddress,studentPhoneNumber);


                // curatorField
                studentWorkName = studentInfo.getName();
                studentWorkSurname = studentInfo.getSurname();
                studentWorkMiddleName = studentInfo.getMiddleName();
                studentWorkAddress = studentInfo.getAddress();
                studentWorkPhoneNumber = studentInfo.getPhoneNumber();
                studentWorkBirthDay = studentInfo.getDate_of_birth();
                studentGroupName = studentInfo.getGroupName();

                studentWorkId = SearchStudentData.getIdStudentForAdmin(studentWorkAddress,studentWorkPhoneNumber);


                UpdateStudentButton.setVisible(true);
                DeleteStudentButton.setVisible(true);
                extendedInfo.setVisible(true);
            }
        });
    }

    private void displayGroupData(){
        String hql = "FROM StudentInfo WHERE groupName = :groupName and status = true";
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

    public void showAddStudentDialog(){
        saveDialog = loadAndShowDialog("/fxml/curator/curatorDialogFxml/AddStudent.fxml",saveDialog);
        saveDialog.setOnHidden(event -> startWorkGroup());
    }

    public void showUpdateStudentDialog(){
        saveDialog = loadAndShowDialog("/fxml/curator/curatorDialogFxml/UpdateStudent.fxml",saveDialog);
        saveDialog.setOnHidden(event -> startWorkGroup());
    }

    public void delete(){
        DeleteData.deleteStudent(studentId);
        startWorkGroup();
    }

    public void startWorkGroup(){
        selectRows();
        displayGroupData();
        displayCuratorName();
        UpdateStudentButton.setVisible(false);
        DeleteStudentButton.setVisible(false);
    }

    public void initialize() {
        startWorkGroup();
    }
}