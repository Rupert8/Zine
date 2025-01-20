package controller.admin;

import data.DisplayDate;
import hibernate.entity.Curators;
import hibernate.entity.StudentInfo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import start.zine.HelloApplication;
import tableView.RemovedStudentPrototype;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;


public class RemovedStudentController extends HelloApplication implements Initializable {
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
}

