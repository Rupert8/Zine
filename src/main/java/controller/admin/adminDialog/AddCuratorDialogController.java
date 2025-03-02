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
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddCuratorDialogController extends AdminCuratorController implements Initializable {
    @FXML
    private ComboBox<String> CuratorGroup;

    @FXML
    private TextField CuratorMiddleName, CuratorName, CuratorSurname, CuratorEmail, CuratorPassword;

    private String name;
    private String surname;
    private String email;
    private String password;
    private String group;
    private String middleName;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private final Random random = new Random();

    private void getData() {
        name = CuratorName.getText();
        surname = CuratorSurname.getText();
        middleName = CuratorMiddleName.getText();
        email = CuratorEmail.getText();
        password = String.valueOf( 10000 + random.nextInt(90000));
    }

    public void closeDialog() {
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

    public void addCurator() {
        if (isAllFieldsFilled()) {
            if (!isExist()) {
                if(isValidEmail()){
                    getData();
                    AddData.addCuratorData(name, surname, middleName, email, password);
                    closeDialog();
                    loadAndShowSuccessNotification("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
                }else{
                    loadAndShowLoginAlarm("/fxml/notifications/warningNotifications/WarningInvalidEmail.fxml");
                }
            } else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningExistCuratorEmail.fxml");
            }
        } else {
            loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
        }

    }

//    public void setComboBox() {
//        List<String> groups = DisplayDate.getGroupForAddCuratorName();
//        CuratorGroup.getItems().setAll(groups);
//    }

    private boolean isAllFieldsFilled() {
        if (!CuratorName.getText().isEmpty() &&
                !CuratorSurname.getText().isEmpty() &&
                !CuratorMiddleName.getText().isEmpty() &&
                !CuratorEmail.getText().isEmpty() ) {
            return true; // Усі поля заповнені
        } else {
            return false; // Є незаповнені поля
        }
    }

    private boolean isValidEmail() {
        String email = CuratorEmail.getText().trim();
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isExist(){
        String email = CuratorEmail.getText();
        return SearchStudentData.validateUserEmail(email);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setComboBox();
    }
}
