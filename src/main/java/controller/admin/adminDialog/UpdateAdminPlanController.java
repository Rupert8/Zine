package controller.admin.adminDialog;

import controller.admin.AdminWorkPlanController;
import data.UpdateData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class UpdateAdminPlanController extends AdminWorkPlanController {
    @FXML
    private ComboBox<String> CompletionComboBox;

    @FXML
    private TextField NameEvent;

    @FXML
    private TextField DialogPerformer;

    @FXML
    private ComboBox<Integer> SemesterComboBox;

    @FXML
    private ComboBox<String> ConfirmationNoteComboBox;

    @FXML
    private Button applyButton;

    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker dateExecution;

    private String nameEvent;
    private Date date;
    private Integer semester;
    private String status;
    private String confirmationNote;

    private void getData(){
        if(CompletionComboBox.getValue().equals("Виконано")){
            status = "Виконано";
        }else{
            status = "Невиконано";
        }

        nameEvent = String.valueOf(NameEvent.getText());
        date = Date.valueOf(dateExecution.getValue());
        semester = SemesterComboBox.getValue();
        confirmationNote = ConfirmationNoteComboBox.getValue();
    }

    public void setField(){
        NameEvent.setText(passEventName);
        dateExecution.setValue(passExecutionDate.toLocalDate());
        SemesterComboBox.setValue(passSemester);
        CompletionComboBox.setValue(passDone);
        ConfirmationNoteComboBox.setValue(passConfirmationNote);
    }

    public void updateEvent(){
        if(isAllFieldsFilled()){
            getData();
            UpdateData.updateAdminPlanDataById(nameEvent,date,confirmationNote,status,semester);
            closeDialog();
        }else{
            loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
        }

    }

    public void setCompletionComboBox(){
        CompletionComboBox.getItems().addAll("Виконано","Невиконано");
    }

    public void setConfirmationNoteComboBox(){
        ConfirmationNoteComboBox.getItems().addAll("Затверджено","Не затверджено");
    }

    public void closeDialog(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setField();
        setCompletionComboBox();
        setConfirmationNoteComboBox();
    }
}
