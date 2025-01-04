package controller;

import data.UpdateData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class DialogUpdateController extends WorkPlanController implements Initializable {
    @FXML
    private ComboBox<String> CompletionComboBox;

    @FXML
    private TextField NameEvent;

    @FXML
    private TextField DialogPerformer;

    @FXML
    private ComboBox<Integer> SemesterComboBox;

    @FXML
    private Button applyButton;

    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker dateExecution;

    private String nameEvent;
    private String performer;
    private Date date;
    private Integer semester;
    private String status;

    private void getData(){
        if(CompletionComboBox.getValue().equals("Виконано")){
            status = "Виконано";
        }else{
            status = "Невиконано";
        }

        nameEvent = String.valueOf(NameEvent.getText());
        performer = String.valueOf(DialogPerformer.getText());
        date = Date.valueOf(dateExecution.getValue());
        semester = SemesterComboBox.getValue();
    }

    public void setField(){
        NameEvent.setText(passEventName);
        DialogPerformer.setText(passPerformer);
        dateExecution.setValue(passExecutionDate.toLocalDate());
        SemesterComboBox.setValue(passSemester);
        CompletionComboBox.setValue(passDone);
    }

    public void updateEvent(){
        getData();
        UpdateData.updatePlanDataById(nameEvent,date,performer,status,semester);
        closeDialog();
    }

    public void setCompletionComboBox(){
        CompletionComboBox.getItems().addAll("Виконано","Невиконано");
    }

    public void closeDialog(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setField();
        setCompletionComboBox();
    }
}
