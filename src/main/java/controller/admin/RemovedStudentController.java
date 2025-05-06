package controller.admin;

import data.DisplayDate;
import data.SearchStudentData;
import hibernate.entity.*;
import interfaces.WindowActions.WindowControl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.ScreenService;
import start.zine.StartApplication;
import tableView.RemovedStudentPrototype;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import static services.exportExel.ExelExportService.exportMultiSheetExcel;


public class RemovedStudentController extends StartApplication implements Initializable, WindowControl {
    @FXML
    private TableView<RemovedStudentPrototype> RemovedStudentTable;

    @FXML
    private TableColumn<RemovedStudentPrototype, String> AddressColumn;

    @FXML
    private TableColumn<RemovedStudentPrototype, Date> DateBirthDayColumn;

    @FXML
    private TableColumn<RemovedStudentPrototype, String> MiddleNameColumn;

    @FXML
    private TableColumn<RemovedStudentPrototype, String> NameColumn;

    @FXML
    private TableColumn<RemovedStudentPrototype, Integer> NumberColumn;

    @FXML
    private TableColumn<RemovedStudentPrototype, String> PhoneNumberColumn;

    @FXML
    private TableColumn<RemovedStudentPrototype, Date> RemovedDateColumn;

    @FXML
    private TableColumn<RemovedStudentPrototype, String> SurnameColumn;

    @FXML
    private HBox Hbox;
    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;
    @FXML
    private Button exportStudentInfoButton;

    private String studentSurname;
    private String studentName;
    private String studentMiddleName;
    private int studentId;

    private void setDataInGroupTable(ObservableList<RemovedStudentPrototype> studentInfo){
        RemovedStudentTable.setItems(studentInfo);

        NumberColumn.setCellValueFactory(new PropertyValueFactory<RemovedStudentPrototype, Integer>("studentId"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<RemovedStudentPrototype, String>("studentName"));
        MiddleNameColumn.setCellValueFactory(new PropertyValueFactory<RemovedStudentPrototype, String>("studentMiddleName"));
        SurnameColumn.setCellValueFactory(new PropertyValueFactory<RemovedStudentPrototype, String>("studentSurname"));
        DateBirthDayColumn.setCellValueFactory(new PropertyValueFactory<RemovedStudentPrototype, Date>("studentBirthDate"));
        PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<RemovedStudentPrototype, String>("studentPhoneNumber"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<RemovedStudentPrototype, String>("studentAddress"));
        RemovedDateColumn.setCellValueFactory(new PropertyValueFactory<RemovedStudentPrototype, Date>("studentRemovedDate"));
    }

    private void selectItems(){
        RemovedStudentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RemovedStudentPrototype removedStudentPrototype = newValue;

                studentId = SearchStudentData.getIdStudentForAdmin(removedStudentPrototype.getStudentAddress(),removedStudentPrototype.getStudentPhoneNumber());
                studentSurname = removedStudentPrototype.getStudentSurname();
                studentName = removedStudentPrototype.getStudentName();
                studentMiddleName = removedStudentPrototype.getStudentMiddleName();
                exportStudentInfoButton.setVisible(true);

            }
        });
    }

    private void displayRemovedStudentData(){;
        ObservableList<RemovedStudentPrototype> removedStudentInfoList = DisplayDate.getRemovedStudentInfo();
        setDataInGroupTable(removedStudentInfoList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayRemovedStudentData();
        selectItems();
        RemovedStudentTable.getSelectionModel().clearSelection();
        exportStudentInfoButton.setVisible(false);
    }

    public void exportAllRemovedStudentData(){
        List<EducationInfo> educationInfoList = DisplayDate.loadByStudentId(EducationInfo.class, "id", studentId);
        List<MilitaryService> militaryList = DisplayDate.loadByStudentId(MilitaryService.class, "id", studentId);
        List<StudentParents> parentsList = DisplayDate.loadByStudentId(StudentParents.class, "id", studentId);
        List<StudentJob> jobList = DisplayDate.loadByStudentId(StudentJob.class, "id", studentId);
        List<CircleActivity> circleActivityList = DisplayDate.loadByStudentId(CircleActivity.class, "id", studentId);
        List<SocialActivity> socialActivityList = DisplayDate.loadByStudentId(SocialActivity.class, "id", studentId);
        List<Promotion> promotionList = DisplayDate.loadByStudentId(Promotion.class, "id", studentId);
        List<IndividualSupport> individualSupportList = DisplayDate.loadByStudentId(IndividualSupport.class, "id", studentId);
        List<SocialPassport> socialPassportList = DisplayDate.loadByStudentId(SocialPassport.class, "id", studentId);

        String downloadFolder = System.getProperty("user.home") + "\\Downloads";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new java.util.Date());
        String filePath = downloadFolder + "\\Повна_інформація_"+ studentSurname + "_" + studentName + "_" + studentMiddleName + "_" + currentDate + ".xlsx";

        try {
            exportMultiSheetExcel(educationInfoList,militaryList,parentsList,jobList,socialActivityList,circleActivityList,individualSupportList,promotionList,socialPassportList,filePath);
            loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessExportNotification.fxml");
        }catch (Exception e){
            e.printStackTrace();

        }


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

