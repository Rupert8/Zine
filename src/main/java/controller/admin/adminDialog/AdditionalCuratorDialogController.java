package controller.admin.adminDialog;

import controller.admin.AdminCuratorController;
import data.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static controller.admin.AdminCuratorController.*;

public class AdditionalCuratorDialogController extends AdminCuratorController implements Initializable{
    @FXML
    private ComboBox<String> CuratorGroup;

    @FXML
    private TextField CuratorMiddleName,CuratorName,CuratorSurname,CuratorEmail;

    private String name;
    private String surname;
    private String email;
    private String group;
    private String middleName;


    private void getData(){
        name = CuratorName.getText();
        surname = CuratorSurname.getText();
        middleName = CuratorMiddleName.getText();
        email = CuratorEmail.getText();
        //group = CuratorGroup.getValue();
    }

    public void closeDialog(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

    public void updateCurator(){
        if(isAllFieldsFilled()){
                getData();
                UpdateData.updateCuratorDataById(name,surname,middleName,email);
                closeDialog();
                loadAndShowSuccessNotification("/fxml/notifications/successNotifications/SuccessUpdateNotification.fxml");
        }else{
            loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
        }
        
    }

    private void setValueInTextFields(){
        CuratorName.setText(curatorName);
        CuratorSurname.setText(curatorSurname);
        CuratorMiddleName.setText(curatorMiddleName);
        CuratorEmail.setText(curatorEmail);
        //CuratorGroup.setValue(curatorGroupName);
    }

//    public void setComboBox(){
//        List<String> groups = DisplayDate.getGroupName();
//        CuratorGroup.getItems().setAll(groups);
//    }

    public void deleteCurator(){
        DeleteData.deleteCurator(curatorId,curatorGroupName);
        closeDialog();
        loadAndShowSuccessNotification("/fxml/notifications/successNotifications/SuccessDeleteNotification.fxml");
    }

    private boolean isAllFieldsFilled() {
        if (!CuratorName.getText().isEmpty() &&
                !CuratorSurname.getText().isEmpty() &&
                !CuratorMiddleName.getText().isEmpty() &&
                !CuratorEmail.getText().isEmpty()) {
            return true; // Усі поля заповнені
        } else {
            return false; // Є незаповнені поля
        }
    }

    private boolean isExist(){
        String email = CuratorEmail.getText();
        return SearchStudentData.validateUserEmail(email);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        setComboBox();
        setValueInTextFields();
    }
}
