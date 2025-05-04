package controller.curator.individualSupport;

import data.DisplayDate;
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

public class IndividualSupport extends StartApplication implements Initializable, WindowControl {
    @FXML
    private TableColumn<hibernate.entity.IndividualSupport, String> Content;

    @FXML
    private TableColumn<hibernate.entity.IndividualSupport, Date> Date;

    @FXML
    private TableView<hibernate.entity.IndividualSupport> IndividualSupportTable;

    @FXML
    private TableColumn<hibernate.entity.IndividualSupport, String> MiddleNameColumn;

    @FXML
    private TableColumn<hibernate.entity.IndividualSupport, String> NameColumn;

    @FXML
    private TableColumn<hibernate.entity.IndividualSupport, Integer> NumberColumn;

    @FXML
    private TableColumn<hibernate.entity.IndividualSupport, Integer> Semester;

    @FXML
    private TableColumn<hibernate.entity.IndividualSupport, String> SurnameColumn;

    @FXML
    private HBox Hbox;

    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

    public void back(ActionEvent event) {
        switchWorkStudPage(event);
    }

    public void displayIndividualSupport() {
        ObservableList<hibernate.entity.IndividualSupport> individualSupports = DisplayDate.tableIndividualSupport();
        TableService.setDataInIndividualSupportTable(individualSupports,IndividualSupportTable,NumberColumn,NameColumn,SurnameColumn,MiddleNameColumn,Semester,Date,Content);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayIndividualSupport();
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
