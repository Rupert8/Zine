package controller.admin.adminDialog;

import controller.admin.AdminCuratorController;
import data.AddData;
import data.DisplayDate;
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
        getData();
        AddData.addCuratorData(name,surname,middleName,group,email,password);
        closeDialog();
        displayCurators();
    }

    public void setComboBox(){
        List<String> groups = DisplayDate.getGroupForAddCuratorName();
        CuratorGroup.getItems().setAll(groups);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setComboBox();
    }
}
