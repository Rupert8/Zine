package controller.admin.adminDialog;

import controller.admin.AdminCuratorController;
import data.AddData;
import data.DisplayDate;
import data.SearchStudentData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import start.zine.HelloApplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddCuratorDialogController extends AdminCuratorController implements Initializable {
    @FXML
    private ComboBox<String> CuratorGroup;

    @FXML
    private TextField CuratorMiddleName,CuratorName,CuratorSurname,CuratorEmail,CuratorPassword;

    private String name;
    private String surname;
    private String email;
    private String password;
    private String group;
    private String middleName;


    private void getData(){
        name = CuratorName.getText();
        surname = CuratorSurname.getText();
        middleName = CuratorMiddleName.getText();
        email = CuratorEmail.getText();
        password = CuratorPassword.getText();
        group = CuratorGroup.getValue();
    }

    public void closeDialog(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

    public void addCurator(){
        if(isAllFieldsFilled()){
            if(!isExist()){
                getData();
                AddData.addCuratorData(name,surname,middleName,group,email,password);
                closeDialog();
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningExistCuratorEmail.fxml");
            }
        }else{
            loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
        }

    }

    public void setComboBox(){
        List<String> groups = DisplayDate.getGroupForAddCuratorName();
        CuratorGroup.getItems().setAll(groups);
    }

    private boolean isAllFieldsFilled() {
        if (!CuratorName.getText().isEmpty() &&
                !CuratorSurname.getText().isEmpty() &&
                !CuratorMiddleName.getText().isEmpty() &&
                CuratorGroup.getValue() != null &&
                !CuratorEmail.getText().isEmpty() &&
                !CuratorPassword.getText().isEmpty()) {
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
        setComboBox();
    }
}
