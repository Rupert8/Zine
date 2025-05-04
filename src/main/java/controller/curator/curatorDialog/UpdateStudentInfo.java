package controller.curator.curatorDialog;

import controller.curator.WorkGroupController;
import data.SearchStudentData;
import data.UpdateData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class UpdateStudentInfo extends WorkGroupController implements Initializable {
    @FXML
    private TextField StudentName,StudentSurname,StudentMiddleName;
    @FXML
    private TextField StudentPhoneNumber,StudentAddress;
    @FXML
    private DatePicker StudentDateOfBirth;

    public void updateStudent() {
        if(isAllFieldFilled()){
                String name = StudentName.getText();
                String surname = StudentSurname.getText();
                String middleName = StudentMiddleName.getText();
                String phoneNumber = StudentPhoneNumber.getText();
                String address = StudentAddress.getText();
                Date dateOfBirth = Date.valueOf(StudentDateOfBirth.getValue());
                UpdateData.updateStudent(name, surname, middleName, address, phoneNumber , dateOfBirth,studentWorkId);
                closeDialog();
                loadAndShowSuccessNotification("/fxml/notifications/successNotifications/SuccessUpdateNotification.fxml");
        }else{
            loadAndShowLoginAlarm("/fxml/notifications/warningNotifications/WarningEmptyField.fxml");
        }

    }

    public void setValueInTextFields(){
        StudentName.setText(studentWorkName);
        StudentSurname.setText(studentWorkSurname);
        StudentMiddleName.setText(studentWorkMiddleName);
        StudentPhoneNumber.setText(studentWorkPhoneNumber);
        StudentAddress.setText(studentWorkAddress);
        StudentDateOfBirth.setValue(studentWorkBirthDay.toLocalDate());
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValueInTextFields();
    }
}
