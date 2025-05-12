package controller.admin;

import data.DisplayDate;
import data.SearchStudentData;
import hibernate.entity.StudentInfo;
import interfaces.WindowActions.WindowControl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.ScreenService;
import start.zine.StartApplication;
import tableView.RemovedStudentPrototype;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;


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
    @FXML
    private ComboBox<String> SortStudentComboBox;
    @FXML
    private ComboBox<String> SortByGroupComboBox;

    public static Dialog<Boolean> saveDialog;

    public static String studentSurname;
    public static String studentName;
    public static String studentMiddleName;
    public static int studentId;

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


    public void selectSortStudentComboBox() {
        if (SortStudentComboBox.getValue().equals("Показати все")) {
            displayRemovedStudentData();
            SortByGroupComboBox.setVisible(false);
            SortByGroupComboBox.getItems().clear();
            RemovedStudentTable.getSelectionModel().clearSelection();
        } else if (SortStudentComboBox.getValue().equals("Групою")) {
            SortByGroupComboBox.setVisible(true);
            SortByGroupComboBox.getItems().clear();
            setSortByGroupComboBox();
            RemovedStudentTable.getSelectionModel().clearSelection();
        }

    }

    private void setSortStudentComboBox(){
        if(SortStudentComboBox.getSelectionModel().isEmpty()){
            SortStudentComboBox.getItems().setAll("Показати все","Групою");
        }
    }


    private void setSortByGroupComboBox(){
        List<String> groupName = DisplayDate.getRemovedGroupName();
        SortByGroupComboBox.getItems().setAll(groupName);
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

    public void displayDataByGroupName(){
        String groupName = SortByGroupComboBox.getValue();
        if(groupName != null){
            ObservableList<RemovedStudentPrototype> groupList = DisplayDate.getDataByGroupNameForRemoved(groupName);
            setDataInGroupTable(groupList);
        }else {
            throw new IllegalArgumentException("Група не може бути null");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayRemovedStudentData();
        selectItems();
        RemovedStudentTable.getSelectionModel().clearSelection();
        exportStudentInfoButton.setVisible(false);
        setSortStudentComboBox();
    }


    public void loadChooseExportMethod(){
        saveDialog = loadAndShowDialog("/fxml/exportWindow/ChooseExportMethodRemovedDialog.fxml",saveDialog,"Експорт Документа");
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

