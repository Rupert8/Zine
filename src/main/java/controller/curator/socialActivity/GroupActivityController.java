package controller.curator.socialActivity;

import data.DisplayDate;
import hibernate.entity.CircleActivity;
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

public class GroupActivityController extends StartApplication implements Initializable, WindowControl {
    @FXML
    private TableView<CircleActivity> GroupActivityTable;

    @FXML
    private TableColumn<CircleActivity, String> GroupName;

    @FXML
    private TableColumn<CircleActivity, String> MiddleNameColumn;

    @FXML
    private TableColumn<CircleActivity, String> NameColumn;

    @FXML
    private TableColumn<CircleActivity, String> Note;

    @FXML
    private TableColumn<CircleActivity, Integer> NumberColumn;

    @FXML
    private TableColumn<CircleActivity, Integer> Semester;

    @FXML
    private TableColumn<CircleActivity, String> SurnameColumn;

    @FXML
    private HBox Hbox;

    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

    public void back(ActionEvent event) {
        switchWorkStudPage(event);
    }

    public void displayGroupActivity() {
        ObservableList<CircleActivity> groupActivities = DisplayDate.tableCircleActivity();
        TableService.setDataInCircleActivityTable(groupActivities,GroupActivityTable,NumberColumn,NameColumn,SurnameColumn,MiddleNameColumn,Semester,GroupName,Note);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayGroupActivity();
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
