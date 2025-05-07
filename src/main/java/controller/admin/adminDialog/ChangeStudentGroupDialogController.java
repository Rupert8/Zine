package controller.admin.adminDialog;

import controller.admin.AdminGroupController;
import data.AddData;
import data.DisplayDate;
import data.SearchStudentData;
import data.UpdateData;
import hibernate.entity.Groups;
import hibernate.entity.StudentInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import start.zine.StartApplication;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static controller.admin.AdminMainController.*;


public class ChangeStudentGroupDialogController extends StartApplication  implements Initializable {
    @FXML
    private ComboBox<String> groupComboBox;
    @FXML
    private Label StudentLabel,GroupNameLabel;

    private void setStudentNameInLabel(){
        StudentLabel.setText(StudentInfo.getFullNameOneStudent(studentSurname, studentName, studentMiddleName));
    }

    private void setGroupNameInLabel(){
        GroupNameLabel.setText(studentGroupName);
    }

    private void setGroupComboBox(){
        List<Groups> groupsList = DisplayDate.getFullGroupInfo();

        List<String> groupNames = groupsList.stream()
                .map(Groups::getGroupName)
                .toList();

        groupComboBox.getItems().setAll(groupNames);
    }

    public void changeStudentGroup(){
        String groupName = groupComboBox.getValue();
        UpdateData.updateStudentGroup(groupName);
        closeDialog();
        loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessChangeStudentGroupNotification.fxml");
    }


    public void closeDialog(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setStudentNameInLabel();
        setGroupNameInLabel();
        setGroupComboBox();
    }
}
