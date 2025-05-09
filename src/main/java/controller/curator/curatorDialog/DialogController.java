package controller.curator.curatorDialog;

import controller.curator.WorkPlanController;
import data.AddData;
import data.SearchStudentData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;

import java.util.ResourceBundle;

import static controller.login.LoginController.curatorGroupName;

public class DialogController extends WorkPlanController implements Initializable{
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


    private String completion;
    private String nameEvent;
    private Date date;
    private Integer semester;
    private String status;
    private int year;
    private String groupName;

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
        groupName = curatorGroupName;
    }

    public void addEvent(){
        if(isAllFieldsFilled()){
            if(!isExist()){
                getData();
                AddData.addPlanForCuratorData(nameEvent,date,performerNameForAdd,status,semester,year,groupName);
                closeAddDialog();
                loadAndShowSuccessNotification("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
            }else{
                loadAndShowLoginAlarm("/fxml/notifications/warningNotifications/WarningExistEvent.fxml");
            }

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

    private void setYearComboBox(){
        YearComboBox.getItems().addAll(1,2,3,4);
    }

    private boolean isAllFieldsFilled() {
        if (!NameEvent.getText().isEmpty() &&
                dateExecution.getValue() != null &&
                SemesterComboBox.getValue() != null &&
                CompletionComboBox.getValue() != null) {
            return true; // Усі поля заповнені
        } else {
            return false; // Є незаповнені поля
        }
    }

    private boolean isExist(){
        String eventName = NameEvent.getText();
        return SearchStudentData.validateCuratorEventName(eventName);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCompletionComboBox();
        setSemesterComboBox();
        setYearComboBox();
    }
}
