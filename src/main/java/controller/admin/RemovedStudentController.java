package controller.admin;

import data.DisplayDate;
import hibernate.entity.Curators;
import hibernate.entity.StudentInfo;
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
import start.zine.HelloApplication;
import tableView.RemovedStudentPrototype;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;


public class RemovedStudentController extends HelloApplication implements Initializable, WindowControl {
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

    private void displayRemovedStudentData(){;
        ObservableList<RemovedStudentPrototype> removedStudentInfoList = DisplayDate.getRemovedStudentInfo();
        setDataInGroupTable(removedStudentInfoList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayRemovedStudentData();
        RemovedStudentTable.getSelectionModel().clearSelection();
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

