package controller.generalInfo;

import data.DisplayDate;
import hibernate.entity.EducationInfo;
import hibernate.entity.MilitaryService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import services.TableService;
import start.zine.HelloApplication;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class MilitaryServiceController extends HelloApplication implements Initializable {
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
}
