package controller.admin.adminDialog;

import controller.admin.AdminGroupController;
import data.AddData;
import data.DisplayDate;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
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
    private String groupCurator;

    private void getData(){
        groupName = GroupName.getText();
        groupProfession = GroupProfession.getText();
        groupFormOfEducation = GroupFormOfEducation.getText();
        groupGroupYearOfStudy = GroupYearOfStudy.getText();
        groupLevelOfEducation = GroupLevelOfEducation.getText();
        groupEducationAndProfessionalProgram = EducationAndProfessionalProgram.getText();
        groupCourse = Integer.parseInt(GroupCourse.getValue());
        groupCurator = GroupCurator.getValue();
    }

    public void closeDialog(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

    public void addGroup(){
        getData();
        AddData.addGroupInfo(groupName,groupProfession,groupFormOfEducation,groupGroupYearOfStudy,groupLevelOfEducation,groupEducationAndProfessionalProgram,groupCourse,groupCurator);
        closeDialog();
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
        setCuratorComboBox();
        setCourseComboBox();
    }
}
