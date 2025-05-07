package controller.admin.adminDialog;

import controller.admin.AdminGroupController;
import data.AddData;
import data.SearchStudentData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class AddGroupDialogController extends AdminGroupController implements Initializable {
    @FXML
    private TextField EducationAndProfessionalProgram,GroupFormOfEducation,GroupLevelOfEducation,GroupName,GroupProfession,GroupYearOfStudy;

    @FXML
    private ComboBox<String> GroupCourse;

    @FXML
    private ComboBox<String> GroupCurator;

    private String groupName;
    private String groupProfession;
    private String groupFormOfEducation;
    private String groupGroupYearOfStudy;
    private String groupLevelOfEducation;
    private String groupEducationAndProfessionalProgram;
    private int groupCourse;

    private void getData(){
        groupName = GroupName.getText();
        groupProfession = GroupProfession.getText();
        groupFormOfEducation = GroupFormOfEducation.getText();
        groupGroupYearOfStudy = GroupYearOfStudy.getText();
        groupLevelOfEducation = GroupLevelOfEducation.getText();
        groupEducationAndProfessionalProgram = EducationAndProfessionalProgram.getText();
        groupCourse = Integer.parseInt(GroupCourse.getValue());
    }

    public void closeDialog(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

    public void addGroup(){
        if(isAllFieldsFilled()){
            if(!isExist()){
                getData();
                AddData.addGroupInfo(groupName,groupProfession,groupFormOfEducation,groupGroupYearOfStudy,groupLevelOfEducation,groupEducationAndProfessionalProgram,groupCourse);
                closeDialog();
                loadAndShowSuccessNotification("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/warningNotifications/WarningExistGroupName.fxml");
            }
        }else{
            loadAndShowLoginAlarm("/fxml/notifications/warningNotifications/WarningEmptyField.fxml");
        }

    }


    public void setCourseComboBox(){
        GroupCourse.getItems().setAll("1","2","3","4");
    }

    private boolean isAllFieldsFilled() {
        if (!GroupName.getText().isEmpty() &&
                !GroupProfession.getText().isEmpty() &&
                !GroupFormOfEducation.getText().isEmpty() &&
                GroupCourse.getValue() != null &&
                !GroupYearOfStudy.getText().isEmpty() &&
                !GroupLevelOfEducation.getText().isEmpty() &&
                !EducationAndProfessionalProgram.getText().isEmpty()) {
            return true; // Усі поля заповнені
        } else {
            return false; // Є незаповнені поля
        }
    }

    private boolean isExist(){
        String groupName = GroupName.getText();
        return SearchStudentData.validateGroupName(groupName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCourseComboBox();
    }

}
