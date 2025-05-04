package controller.curator.generalInfo;

import data.DisplayDate;
import hibernate.entity.StudentJob;
import interfaces.WindowActions.WindowControl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.ScreenService;
import services.TableService;
import start.zine.StartApplication;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class LaborActivityController extends StartApplication implements Initializable, WindowControl {

    @FXML
    private TableColumn<StudentJob, Date> EndDate;

    @FXML
    private TableColumn<StudentJob, String> MiddleNameColumn;

    @FXML
    private TableColumn<StudentJob, String> NameColumn;

    @FXML
    private TableColumn<StudentJob, Integer> NumberColumn;

    @FXML
    private TableColumn<StudentJob, String> Place;

    @FXML
    private TableColumn<StudentJob, String> Position;

    @FXML
    private TableColumn<StudentJob, Date> StartDate;

    @FXML
    private TableView<StudentJob> StudentJobTable;

    @FXML
    private TableColumn<StudentJob, String> SurnameColumn;

    @FXML
    private HBox Hbox;

    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

    public void back(ActionEvent event) {
        switchWorkStudPage(event);
    }

    public void displayStudentJob() {
        ObservableList<StudentJob> studentJobs = DisplayDate.tableStudentJob();
        TableService.setDataInStudentJobTable(studentJobs,StudentJobTable,NumberColumn,NameColumn,SurnameColumn,MiddleNameColumn,StartDate,EndDate,Place,Position);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayStudentJob();
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
