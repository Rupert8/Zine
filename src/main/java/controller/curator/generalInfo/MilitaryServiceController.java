package controller.curator.generalInfo;

import data.DisplayDate;
import hibernate.entity.MilitaryService;
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

public class MilitaryServiceController extends StartApplication implements Initializable, WindowControl {
    @FXML
    private TableColumn<MilitaryService, Date> EndDate;

    @FXML
    private TableColumn<MilitaryService, String> MiddleNameColumn;

    @FXML
    private TableView<MilitaryService> MilitaryTable;

    @FXML
    private TableColumn<MilitaryService, String> NameColumn;

    @FXML
    private TableColumn<MilitaryService, Integer> NumberColumn;

    @FXML
    private TableColumn<MilitaryService, Date> StartDate;

    @FXML
    private TableColumn<MilitaryService, String> SurnameColumn;

    @FXML
    private TableColumn<MilitaryService, String> Unit;

    @FXML
    private HBox Hbox;

    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

    public void back(ActionEvent event) {
        switchWorkStudPage(event);
    }

    public void displayMilitaryService(){
        ObservableList<MilitaryService> militaryServices = DisplayDate.tableMilitaryService();
        TableService.setDataInMilitaryServiceTable(militaryServices,MilitaryTable,NumberColumn,NameColumn,SurnameColumn,MiddleNameColumn,StartDate,EndDate,Unit);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayMilitaryService();
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
