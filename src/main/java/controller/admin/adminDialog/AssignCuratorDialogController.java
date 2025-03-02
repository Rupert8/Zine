package controller.admin.adminDialog;

import controller.admin.AdminGroupController;
import data.DisplayDate;
import data.UpdateData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static controller.admin.AdminGroupController.groupName;
import static controller.admin.AdminGroupController.saveDialog;

public class AssignCuratorDialogController extends AdminGroupController implements Initializable {
    @FXML
    private TextField GroupNameField;
    @FXML
    private ComboBox<String> CuratorComboBox;

    public void setFieldValues() {
        GroupNameField.setText(groupName);
        CuratorComboBox.getItems().addAll(DisplayDate.getCuratorName());
    }

    public void closeDialog() {
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

    public void assignCurator() {
        String groupName = GroupNameField.getText();
        String fullName = CuratorComboBox.getValue(); // Отримуємо вибране значення
        if (fullName != null && !fullName.trim().isEmpty()) {
            String[] parts = fullName.trim().split("\\s+"); // Розділяємо на частини
            if (parts.length == 3) {
                String surname = parts[0];
                String name = parts[1];
                String middleName = parts[2];

                // Викликаємо метод, передаючи параметри
                UpdateData.updateCuratorGroup(name, surname, middleName, groupName);
                UpdateData.updateGroupCurator(CuratorComboBox.getValue(),groupName,true);
                closeDialog();
                loadAndShowSuccessNotification("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
            } else {
                System.out.println("Неправильний формат імені куратора!");
            }
        } else {
            loadAndShowLoginAlarm("/fxml/notifications/warningNotifications/WarningEmptyCurator.fxml");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFieldValues();
    }
}
