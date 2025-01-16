package controller;

import data.AddData;
import javafx.event.ActionEvent;
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
    private DatePicker dateExecution;

    @FXML
    private ComboBox<Integer> SemesterComboBox;

    private String completion;
    private String nameEvent;
    private Date date;
    private Integer semester;
    private String status;

    private URL url;
    private ResourceBundle resources;

    private void getData(){
        if(CompletionComboBox.getValue().equals("Виконано")){
            status = "Виконано";
        }else{
            status = "Невиконано";
        }

        nameEvent = String.valueOf(NameEvent.getText());
        date = Date.valueOf(dateExecution.getValue());
        semester = SemesterComboBox.getValue();
    }

    public void addEvent(ActionEvent event){
        getData();
        AddData.addPlanForCuratorData(nameEvent,date,performerNameForAdd,status,semester);

    }

    public void closeAddDialog(ActionEvent event){
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
