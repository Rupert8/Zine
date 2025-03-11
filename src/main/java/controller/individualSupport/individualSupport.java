package controller.individualSupport;

import data.DisplayDate;
import hibernate.entity.IndividualSupport;
import hibernate.entity.StudentJob;
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

public class individualSupport extends HelloApplication implements Initializable {
    @FXML
    private TableColumn<IndividualSupport, String> Content;

    @FXML
    private TableColumn<IndividualSupport, Date> Date;

    @FXML
    private TableView<IndividualSupport> IndividualSupportTable;

    @FXML
    private TableColumn<IndividualSupport, String> MiddleNameColumn;

    @FXML
    private TableColumn<IndividualSupport, String> NameColumn;

    @FXML
    private TableColumn<IndividualSupport, Integer> NumberColumn;

    @FXML
    private TableColumn<IndividualSupport, Integer> Semester;

    @FXML
    private TableColumn<IndividualSupport, String> SurnameColumn;

    public void back(ActionEvent event) {
        switchWorkStudPage(event);
    }

    public void displayIndividualSupport() {
        ObservableList<IndividualSupport> individualSupports = DisplayDate.tableIndividualSupport();
        TableService.setDataInIndividualSupportTable(individualSupports,IndividualSupportTable,NumberColumn,NameColumn,SurnameColumn,MiddleNameColumn,Semester,Date,Content);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayIndividualSupport();
    }
}
