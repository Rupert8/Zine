package controller.admin;

import interfaces.WindowActions.WindowControl;
import data.DisplayDate;
import data.SearchStudentData;
import hibernate.entity.StudentInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.ScreenService;
import start.zine.HelloApplication;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static controller.extendedInformationAboutStudent.ExtendedInformationAboutStudent.userStatus;


public class AdminMainController extends HelloApplication implements WindowControl {
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

    @FXML
    private HBox Hbox;
    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

    @FXML
    private HBox WorkPlanHBox,WorkTeacherHBox,StudentBackPane,StudentHBox,GroupHBox,SocialPassportHBox;
    @FXML
    private HBox SortLabelHBox,SortComboBoxHBox,SortByGroupHBox;
    @FXML
    private HBox ExtendedInfoHbox,AddHbox;

    public static int studentId;
    public static String studentName;
    public static String studentSurname;
    public static String studentMiddleName;
    public static String studentAddress;
    public static String studentPhoneNumber;
    public static String studentGroupName;
    private static ObservableList<StudentInfo> studentList;



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
                studentGroupName = studentInfo.getGroupName();
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
            throw new IllegalArgumentException("Група не може бути null");
        }

    }

    public void displayDataByGroupNameStream(){
        String semester = SortByGroupComboBox.getValue();
        if(semester != null){
            List<StudentInfo> studentSortByGroup = studentList.stream()
                    .filter(studentInfo ->  Objects.equals(studentInfo.getGroupName(), semester))
                    .collect(Collectors.toList());

            ObservableList<StudentInfo> studentSortByGroupConvertedList = FXCollections.observableArrayList(studentSortByGroup);
            setDataInGroupTable(studentSortByGroupConvertedList);
        }else {
            throw new IllegalArgumentException("Група не може бути null");
        }

    }

    private void displayGroupData(){
        studentList = DisplayDate.getStudentInfo();
        setDataInGroupTable(studentList);
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

    public void loadExtendedStudentDialog(ActionEvent event){
        userStatus = 0;
        switchToExtendedStudentInfo(event);
    }

    private void correctVisible(){
        SortByGroupComboBox.setVisible(false);
        GroupTable.getSelectionModel().clearSelection();
        displayGroupData();
        selectItems();
        setSortStudentComboBox();
        GroupTable.getSelectionModel().clearSelection();
        StudentHBox.setPickOnBounds(false);
        WorkTeacherHBox.setPickOnBounds(false);
        SocialPassportHBox.setPickOnBounds(false);
        GroupHBox.setPickOnBounds(false);
        WorkPlanHBox.setPickOnBounds(false);
        SortComboBoxHBox.setPickOnBounds(false);
        SortLabelHBox.setPickOnBounds(false);
        AddHbox.setPickOnBounds(false);
        ExtendedInfoHbox.setPickOnBounds(false);
        StudentBackPane.setPickOnBounds(false);
        SortByGroupHBox.setPickOnBounds(false);
    }

    public void initialize() {
        correctVisible();
    }

    @Override
    public void setMinimizeWindowButton() {
        ScreenService.minimized_Window(minimizeWindowButton);
    }

    @Override
    public void setMaximizeWindowButton() {
        ScreenService.maximized_Window(maximizeWindowButton.getScene().getWindow());
    }

    @Override
    public void setCloseWindow() {
        ScreenService.close_Window();
    }

    @Override
    public void setDragWindow(MouseEvent dragEvent) {
        ScreenService.paneDragged(dragEvent,Hbox);
    }

    @Override
    public void setPressedWindow(MouseEvent mouseEvent) {
        ScreenService.panePressed(mouseEvent);
    }
}
