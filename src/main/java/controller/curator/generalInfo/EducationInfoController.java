package controller.curator.generalInfo;

import data.DisplayDate;
import hibernate.entity.EducationInfo;
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

public class EducationInfoController extends StartApplication implements Initializable, WindowControl {

    @FXML
    private TableColumn<EducationInfo, String> SchoolName;

    @FXML
    private TableColumn<EducationInfo, Date> EndDate;

    @FXML
    private TableView<EducationInfo> EducationInfoTable;

    @FXML
    private TableColumn<EducationInfo, String> MiddleNameColumn;

    @FXML
    private TableColumn<EducationInfo, String> NameColumn;

    @FXML
    private TableColumn<EducationInfo, Integer> NumberColumn;

    @FXML
    private TableColumn<EducationInfo, Float> AverageGrade;

    @FXML
    private TableColumn<EducationInfo, String> SurnameColumn;

    @FXML
    private HBox Hbox;

    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

    public void back(ActionEvent event) {
        switchWorkStudPage(event);
    }

    public void displayEducationInfo(){
        ObservableList<EducationInfo> educationInfo = DisplayDate.tableEducationInfo();
        TableService.setDataInEducationInfoTable(educationInfo,EducationInfoTable,NumberColumn,NameColumn,SurnameColumn,MiddleNameColumn,EndDate,SchoolName,AverageGrade);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayEducationInfo();
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
