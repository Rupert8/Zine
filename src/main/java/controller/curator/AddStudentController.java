package controller.curator;

import data.AddData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import start.zine.HelloApplication;

import java.sql.Date;

import static controller.WorkGroupController.saveDialog;


public class AddStudentController extends HelloApplication {
    @FXML
    private TextField StudentName,StudentSurname,StudentMiddleName;
    @FXML
    private TextField StudentPhoneNumber,StudentAddress;
    @FXML
    private DatePicker StudentDateOfBirth;

    public void addStudent(ActionEvent event) {
        String name = StudentName.getText();
        String surname = StudentSurname.getText();
        String middleName = StudentMiddleName.getText();
        String phoneNumber = StudentPhoneNumber.getText();
        String address = StudentAddress.getText();
        Date dateOfBirth = Date.valueOf(StudentDateOfBirth.getValue());
        AddData.addStudent(name,surname,middleName,address,phoneNumber,dateOfBirth);
        closeDialog();
    }

    public void closeDialog(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

}
