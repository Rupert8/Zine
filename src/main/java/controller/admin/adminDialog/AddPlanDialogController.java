package controller.admin.adminDialog;

import data.AddData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import start.zine.StartApplication;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static controller.admin.AdminWorkPlanController.saveDialog;

public class AddPlanDialogController extends StartApplication implements Initializable {
    @FXML
    private ComboBox<String> CompletionComboBox;

    @FXML
    private TextField NameEvent;

    @FXML
    private DatePicker dateExecution;

    @FXML
    private ComboBox<Integer> SemesterComboBox;

    @FXML
    private ComboBox<Integer> YearComboBox;

    private String nameEvent;
    private Date date;
    private Integer semester;
    private String status;
    private Integer year;


    private void getData(){
        if(CompletionComboBox.getValue().equals("Виконано")){
            status = "Виконано";
        }else{
            status = "Невиконано";
        }

        nameEvent = String.valueOf(NameEvent.getText());
        date = Date.valueOf(dateExecution.getValue());
        semester = SemesterComboBox.getValue();
        year = YearComboBox.getValue();
    }

    public void addEvent(){
        if(isAllFieldsFilled()){
            getData();
            AddData.addPlanForAdminData(nameEvent,date,status,semester,year);
            closeAddDialog();
            loadAndShowSuccessNotification("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
        }else{
            loadAndShowLoginAlarm("/fxml/notifications/warningNotifications/WarningEmptyField.fxml");
        }

    }

    public void closeAddDialog(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

    private void setCompletionComboBox(){
        CompletionComboBox.getItems().addAll("Виконано","Невиконано");
    }

    private void setSemesterComboBox(){
        SemesterComboBox.getItems().addAll(1,2);
    }

    private void setYearComboBox(){ YearComboBox.getItems().addAll(1,2,3,4); }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCompletionComboBox();
        setSemesterComboBox();
        setYearComboBox();
    }

    private boolean isAllFieldsFilled() {
        if (!NameEvent.getText().isEmpty() &&
                dateExecution.getValue() != null &&
                SemesterComboBox.getValue() != null &&
                CompletionComboBox.getValue() != null &&
                YearComboBox.getValue() != null) {
            return true; // Усі поля заповнені
        } else {
            return false; // Є незаповнені поля
        }
    }
}
