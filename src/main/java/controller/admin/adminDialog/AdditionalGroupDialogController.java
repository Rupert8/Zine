package controller.admin.adminDialog;

import controller.admin.AdminGroupController;
import data.DeleteData;
import data.DisplayDate;
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
        curator = GroupCurator.getValue();
    }

    private void setValueInTextFields(){
        GroupName.setText(groupName);
        GroupProfession.setText(groupProfession);
        GroupFormOfEducation.setText(groupFormOfEducation);
        GroupYearOfStudy.setText(groupYearOfStudy);
        GroupLevelOfEducation.setText(groupLevelOfEducation);
        GroupCourse.setValue(String.valueOf(groupCourse));
        GroupCurator.setValue(groupCurator);
        GroupEducationAndProfessionalProgram.setText(groupEducationAndProfessionProgram);
    }

    public void updateGroup(){
        getData();
        UpdateData.updateGroupDataById(groupId,name,curator,profession,educationAndProfessionalProgram,levelOfEducation,course,yearOfStudy, formOfEducation);
        closeDialog();
    }

    public void deleteGroup(){
        DeleteData.deleteGroup(groupId);
        closeDialog();
    }

    public void closeDialog(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

    public void setCuratorComboBox(){
        List<String> groups = DisplayDate.getCuratorName();
        GroupCurator.getItems().setAll(groups);

    }

    public void setCourseComboBox(){
        GroupCourse.getItems().setAll("1","2","3","4");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValueInTextFields();
        setCourseComboBox();
        setCuratorComboBox();
    }
}
