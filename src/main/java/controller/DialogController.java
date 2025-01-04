package controller;

import data.AddData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;

import java.util.ResourceBundle;

public class DialogController extends WorkPlanController implements Initializable{

    @FXML
    private ComboBox<String> CompletionComboBox;

    @FXML
    private TextField NameEvent;

    @FXML
    private TextField DialogPerformer;

    @FXML
    private DatePicker dateExecution;

    @FXML
    private ComboBox<Integer> SemesterComboBox;

    @FXML
    private Button applyButton;

    @FXML
    private Button cancelButton;

    private String completion;
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

    public void addEvent(){
        getData();
        AddData.addPlanData(nameEvent,date,performer,status,semester);
        closeDialog();
    }

    public void closeDialog(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }


    private void setCompletionComboBox(){
        CompletionComboBox.getItems().addAll("Виконано","Невиконано");
    }

    private void setSemesterComboBox(){
        SemesterComboBox.getItems().addAll(1,2,3,4,5,6,7,8);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCompletionComboBox();
        setSemesterComboBox();
    }
}
