package controller.curator;

import data.AddData;
import data.SearchStudentData;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import start.zine.HelloApplication;

import java.sql.Date;

import static controller.curator.WorkGroupController.saveDialog;


public class AddStudentController extends HelloApplication {
    @FXML
    private TextField StudentName,StudentSurname,StudentMiddleName;
    @FXML
    private TextField StudentPhoneNumber,StudentAddress;
    @FXML
    private DatePicker StudentDateOfBirth;

    public void addStudent() {
        if(isAllFieldFilled()){
            if(!isExist()){
                String name = StudentName.getText();
                String surname = StudentSurname.getText();
                String middleName = StudentMiddleName.getText();
                String phoneNumber = StudentPhoneNumber.getText();
                String address = StudentAddress.getText();
                Date dateOfBirth = Date.valueOf(StudentDateOfBirth.getValue());
                AddData.addStudent(name,surname,middleName,address,phoneNumber,dateOfBirth);
                closeDialog();
                loadAndShowSuccessNotification("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
            }else{
                loadAndShowLoginAlarm("/fxml/notifications/WarningExistStudent.fxml");
            }
        }else{
            loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
        }

    }

    public boolean isAllFieldFilled() {
        if(!StudentName.getText().isEmpty() &&
               !StudentSurname.getText().isEmpty() &&
               !StudentMiddleName.getText().isEmpty() &&
               StudentDateOfBirth.getValue() != null &&
               !StudentPhoneNumber.getText().isEmpty() &&
               !StudentAddress.getText().isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public boolean isExist(){
        String phoneNumber = StudentPhoneNumber.getText();
        return SearchStudentData.validateStudentExist(phoneNumber);
    }

    public void closeDialog(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

}
