package controller.admin.adminDialog;

import controller.admin.AdminGroupController;
import data.DeleteData;
import data.DisplayDate;
import data.SearchStudentData;
import data.UpdateData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdditionalGroupDialogController extends AdminGroupController implements Initializable {
    @FXML
    private TextField GroupEducationAndProfessionalProgram,GroupFormOfEducation,GroupLevelOfEducation,GroupName,GroupProfession,GroupYearOfStudy;

    @FXML
    private ComboBox<String> GroupCourse;

    @FXML
    private ComboBox<String> GroupCurator;

    private String name;
    private String profession;
    private String formOfEducation;
    private String yearOfStudy;
    private String levelOfEducation;
    private String educationAndProfessionalProgram;
    private int course;
    private String curator;

    private void getData(){
        name = GroupName.getText();
        profession = GroupProfession.getText();
        formOfEducation = GroupFormOfEducation.getText();
        yearOfStudy = GroupYearOfStudy.getText();
        levelOfEducation = GroupLevelOfEducation.getText();
        educationAndProfessionalProgram = GroupEducationAndProfessionalProgram.getText();
        course = Integer.parseInt(GroupCourse.getValue());
    }

    private void setValueInTextFields(){
        GroupName.setText(groupName);
        GroupProfession.setText(groupProfession);
        GroupFormOfEducation.setText(groupFormOfEducation);
        GroupYearOfStudy.setText(groupYearOfStudy);
        GroupLevelOfEducation.setText(groupLevelOfEducation);
        GroupCourse.setValue(String.valueOf(groupCourse));
        GroupEducationAndProfessionalProgram.setText(groupEducationAndProfessionProgram);
    }

    public void updateGroup(){
        if(isAllFieldsFilled()){
                getData();
                UpdateData.updateGroupDataById(groupId,name,profession,educationAndProfessionalProgram,levelOfEducation,course,yearOfStudy, formOfEducation);
                closeDialog();
        }else{
            loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
        }

    }

    public void deleteGroup(){
        DeleteData.deleteGroup(groupId,groupNameForDelete);
        closeDialog();
    }

    public void closeDialog(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

    private boolean isAllFieldsFilled() {
        if (!GroupName.getText().isEmpty() &&
                !GroupProfession.getText().isEmpty() &&
                !GroupFormOfEducation.getText().isEmpty() &&
                GroupCourse.getValue() != null &&
                !GroupYearOfStudy.getText().isEmpty() &&
                !GroupLevelOfEducation.getText().isEmpty() &&
                !GroupEducationAndProfessionalProgram.getText().isEmpty()) {
            return true; // Усі поля заповнені
        } else {
            return false; // Є незаповнені поля
        }
    }

    private boolean isExist(){
        String groupName = GroupName.getText();
        return SearchStudentData.validateGroupName(groupName);
    }

    public void setCourseComboBox(){
        GroupCourse.getItems().setAll("1","2","3","4");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValueInTextFields();
        setCourseComboBox();
    }
}
