package controller.curator.curatorDialog;

import data.AddData;
import data.SearchStudentData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import start.zine.StartApplication;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import static controller.curator.WorkGroupController.saveDialog;


public class AddStudentController extends StartApplication implements Initializable {
    @FXML
    private TextField StudentName,StudentSurname,StudentMiddleName;
    @FXML
    private TextField StudentPhoneNumber,StudentAddress;
    @FXML
    private DatePicker StudentDateOfBirth;

    public void addStudent() {
        if(isAllFieldFilled()){
            if(!isExist()){
                if(isPhoneNumberCorrectLength()){
                    String name = StudentName.getText();
                    String surname = StudentSurname.getText();
                    String middleName = StudentMiddleName.getText();
                    String phoneNumber = StudentPhoneNumber.getText();
                    String address = StudentAddress.getText();
                    Date dateOfBirth = Date.valueOf(StudentDateOfBirth.getValue());
                    AddData.addStudent(name,surname,middleName,address,phoneNumber,dateOfBirth);
                    closeDialog();
                    loadAndShowSuccessNotification("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
                } else{
                     loadAndShowLoginAlarm("/fxml/notifications/warningNotifications/IncorrectPhoneNumber.fxml");
                }
            }else{
                loadAndShowLoginAlarm("/fxml/notifications/warningNotifications/WarningExistStudent.fxml");
            }
        }else{
            loadAndShowLoginAlarm("/fxml/notifications/warningNotifications/WarningEmptyField.fxml");
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

    public boolean isPhoneNumberCorrectLength(){
        String phoneNumber = StudentPhoneNumber.getText();
        return phoneNumber.length() >= 10;
    }

    public void isPhoneNumberMaxLength(){
        UnaryOperator<TextFormatter.Change> digitFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,10}")) {
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(digitFilter);
        StudentPhoneNumber.setTextFormatter(textFormatter);
    }

    public boolean isExist(){
        String phoneNumber = StudentPhoneNumber.getText();
        return SearchStudentData.validateStudentExist(phoneNumber);
    }

    public void closeDialog(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isPhoneNumberMaxLength();
    }
}
