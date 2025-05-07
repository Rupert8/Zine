package controller.curator;

import interfaces.WindowActions.WindowControl;
import data.DeleteData;
import data.DisplayDate;
import data.SearchStudentData;
import hibernate.entity.Curators;
import hibernate.entity.StudentInfo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import services.ScreenService;
import start.zine.StartApplication;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static controller.extendedInformationAboutStudent.ExtendedInformationAboutStudent.userStatus;
import static controller.login.LoginController.curatorEmail;
import static controller.login.LoginController.curatorGroupName;
import static controller.admin.AdminMainController.studentGroupName;
import static controller.admin.AdminMainController.*;

public class WorkGroupController extends StartApplication implements Initializable, WindowControl {
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
    private Button DeleteStudentButton,UpdateStudentButton,exportStudentInfoButton;

    @FXML
    private Label CuratorName;
    @FXML
    private Button extendedInfo;

    @FXML
    private Button GroupNameButton;

    @FXML
    private Pane GroupButtonPane;

    @FXML
    private Button minimizeWindowButton;
    @FXML
    private Button closeWindowButton;
    @FXML
    private Button maximizeWindowButton;

    @FXML
    private HBox Hbox,DeleteHbox,ExtendedInfoHbox,UpdateHbox,AddHbox;
    @FXML
    private HBox WorkPlanHBox,WorkStudHBox,GroupPaneHBox,GroupHbox;
    @FXML
    private StackPane CrudStackPane;

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

    public Curators curator;

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
                exportStudentInfoButton.setVisible(true);
                extendedInfo.setVisible(true);
            }
        });
    }

    private void displayGroupData(){
        String groupName = curator.getGroup();
        list = DisplayDate.getDataStudentInfo(groupName);
        setDataInGroupTable(list);
    }

    private void displayCuratorName(){
        if(curatorFullName == null){
            curatorFullName = curator.getSurname() + " " + curator.getName() + " " + curator.getMiddleName();
            CuratorName.setText(curatorFullName);
        }else{
            CuratorName.setText(curatorFullName);
        }
    }

    public void showAddStudentDialog(){
        saveDialog = loadAndShowDialog("/fxml/curator/curatorDialogFxml/AddStudent.fxml",saveDialog,"Реєстрація студента");
        saveDialog.setOnHidden(event -> startWorkGroup());
        //saveDialog.setOnHidden(event -> extendedInfo.setVisible(false));
    }

    public void showChooseExportDialog(){
        saveDialog = loadAndShowDialog("/fxml/exportWindow/ChooseExportMethodCuratorDialog.fxml",saveDialog,"Експорт Документа");
        saveDialog.setOnHidden(event -> startWorkGroup());
    }

    public void showUpdateStudentDialog(){
        saveDialog = loadAndShowDialog("/fxml/curator/curatorDialogFxml/UpdateStudent.fxml",saveDialog,"Редагування даних студента");
        saveDialog.setOnHidden(event -> startWorkGroup());
        //saveDialog.setOnHidden(event -> extendedInfo.setVisible(false));
    }

    public void loadExtendedStudentDialog(ActionEvent event){
        userStatus = 1;
        switchToExtendedStudentInfo(event);
    }

    public void delete(){
        DeleteData.deleteStudent(studentId);
        startWorkGroup();
        loadAndShowSuccessNotification("/fxml/notifications/successNotifications/SuccessDeleteNotification.fxml");
    }

    public void startWorkGroup(){
        selectRows();
        displayGroupData();
        displayCuratorName();
        GroupNameButton.setText(curatorGroupName);
        //GroupButtonPane.prefHeightProperty().bind(GroupNameButton.heightProperty());
        //GroupButtonPane.prefWidthProperty().bind(GroupNameButton.widthProperty());
        UpdateStudentButton.setVisible(false);
        DeleteStudentButton.setVisible(false);
        extendedInfo.setVisible(false);
        GroupTable.getSelectionModel().clearSelection();
        CrudStackPane.setPickOnBounds(false);
        DeleteHbox.setPickOnBounds(false);
        UpdateHbox.setPickOnBounds(false);
        ExtendedInfoHbox.setPickOnBounds(false);
        AddHbox.setPickOnBounds(false);
        WorkPlanHBox.setPickOnBounds(false);
        WorkStudHBox.setPickOnBounds(false);
        GroupPaneHBox.setPickOnBounds(false);
        GroupHbox.setPickOnBounds(false);
        exportStudentInfoButton.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        curator = DisplayDate.getCuratorByUserEmail(curatorEmail);
        startWorkGroup();
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