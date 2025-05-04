package controller.curator.generalInfo;

import data.DisplayDate;
import hibernate.entity.StudentParents;
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
import java.util.ResourceBundle;

public class ParentsInfoController extends StartApplication implements Initializable, WindowControl {

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

    @FXML
    private HBox Hbox;

    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

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
