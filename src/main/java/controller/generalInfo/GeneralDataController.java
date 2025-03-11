package controller.generalInfo;

import data.DisplayDate;
import hibernate.entity.StudentInfo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.commons.math3.analysis.function.Add;
import services.TableService;
import start.zine.HelloApplication;

import java.net.URL;
import java.security.Provider;
import java.sql.Date;
import java.util.ResourceBundle;

public class GeneralDataController extends HelloApplication implements Initializable {
    @FXML
    private TableView<StudentInfo> GeneralInfoTable;

    @FXML
    private TableColumn<StudentInfo, String> AddressColumn;

    @FXML
    private TableColumn<StudentInfo, Date> DateBirthDayColumn;

    @FXML
    private TableColumn<StudentInfo, String> MiddleNameColumn;

    @FXML
    private TableColumn<StudentInfo, String> NameColumn;

    @FXML
    private TableColumn<StudentInfo, Integer> NumberColumn;

    @FXML
    private TableColumn<StudentInfo, String> PhoneNumberColumn;

    @FXML
    private TableColumn<StudentInfo, String> SurnameColumn;

    public void back(ActionEvent event) {
        switchWorkStudPage(event);
    }

    public void displayGeneralInfo(){
        ObservableList<StudentInfo> generalInfo = DisplayDate.tableGeneralInfo();
        TableService.setDataInGeneralInfoTable(generalInfo,GeneralInfoTable,NumberColumn,NameColumn,SurnameColumn,MiddleNameColumn,DateBirthDayColumn,PhoneNumberColumn, AddressColumn);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayGeneralInfo();
    }
}
