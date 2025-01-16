package controller.admin;

import data.DisplayDate;
import data.SearchStudentData;
import hibernate.entity.StudentInfo;
import hibernate.entity.WorkPlan;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import start.zine.HelloApplication;

import java.sql.Date;
import java.util.List;

public class AdminMainController extends HelloApplication {
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
    private ComboBox<String> SortStudentComboBox,SortByGroupComboBox;

    @FXML
    private Button extendedInfo;

    public static int studentId;
    public static String studentName;
    public static String studentSurname;
    public static String studentMiddleName;
    public static String studentAddress;
    public static String studentPhoneNumber;
    private static ObservableList<StudentInfo> list;

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

    private void selectItems(){
        GroupTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                StudentInfo studentInfo = newValue;

                studentName = studentInfo.getName();
                studentSurname = studentInfo.getSurname();
                studentMiddleName = studentInfo.getMiddleName();
                studentAddress = studentInfo.getAddress();
                studentPhoneNumber = studentInfo.getPhoneNumber();
                extendedInfo.setVisible(true);

                studentId = SearchStudentData.getIdStudentForAdmin(studentAddress,studentPhoneNumber);
            }
        });
    }

    public void displayDataByGroupName(){
        String semester = SortByGroupComboBox.getValue();
        if(semester != null){
            ObservableList<StudentInfo> semesterList = DisplayDate.getDataByGroupNameForAdminStudentInfo(semester);
            setDataInGroupTable(semesterList);
        }else {
            throw new IllegalArgumentException("Семестер не може бути null");
        }

    }

    private void displayGroupData(){
        String hql = "FROM StudentInfo";
        list = DisplayDate.getStudentInfo();
        setDataInGroupTable(list);
    }


    private void setSortStudentComboBox(){
        SortStudentComboBox.getItems().setAll("Показати все","Групою");
    }

    private void setSortByGroupComboBox(){
        List<String> groupName = DisplayDate.getGroupName();
        SortByGroupComboBox.getItems().setAll(groupName);
    }

    public void selectSortStudentComboBox(){
        if(SortStudentComboBox.getValue().equals("Показати все")){
            displayGroupData();
            SortByGroupComboBox.setVisible(false);
            SortByGroupComboBox.getItems().clear();
            GroupTable.getSelectionModel().clearSelection();
        }else if(SortStudentComboBox.getValue().equals("Групою")){
            SortByGroupComboBox.setVisible(true);
            SortByGroupComboBox.getItems().clear();
            setSortByGroupComboBox();
            GroupTable.getSelectionModel().clearSelection();
        }
    }



    private void correctVisible(){
        SortByGroupComboBox.setVisible(false);
        GroupTable.getSelectionModel().clearSelection();
        displayGroupData();
        selectItems();
        setSortStudentComboBox();
        GroupTable.getSelectionModel().clearSelection();
    }

    public void initialize() {
        correctVisible();
    }
}
