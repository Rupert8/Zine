package controller.admin;

import data.DisplayDate;
import data.SearchStudentData;
import hibernate.entity.Curators;
import hibernate.entity.Groups;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import start.zine.HelloApplication;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminGroupController extends HelloApplication implements Initializable {
    @FXML
    private TableColumn<Groups, String> GroupCuratorNameColumn;

    @FXML
    private TableColumn<Groups, String> GroupNameColumn;

    @FXML
    private TableColumn<Groups, Integer> GroupNumberColumn;

    @FXML
    private TableColumn<Groups, String> GroupProffesionColumn;

    @FXML
    private TableView<Groups> GroupTable;

    @FXML
    private Button AdditionInfoButton;

    public static Dialog<Boolean> saveDialog;

    public static int groupId;
    public static String groupName;
    public static String groupNameForDelete;
    public static String groupCurator;
    public static String groupProfession;
    public static int groupCourse;
    public static String groupEducationAndProfessionProgram;
    public static String groupLevelOfEducation;
    public static String groupYearOfStudy;
    public static String groupFormOfEducation;

    public void loadAddGroupDialog(){
        saveDialog = loadAndShowDialog("/fxml/admin/adminDialogFxml/AddGroupDialogPane.fxml",saveDialog);
        saveDialog.setOnHidden(event -> startAdminGroup());
    }

    public void loadAdditionInfoDialog(){
        saveDialog = loadAndShowDialog("/fxml/admin/adminDialogFxml/AdditionalGroupDialogPane.fxml",saveDialog);
        saveDialog.setOnHidden(event -> startAdminGroup());
    }


    private void setDataInPlanTable(ObservableList<Groups> groupInfo){
        GroupTable.setItems(groupInfo);

        GroupNumberColumn.setCellValueFactory(new PropertyValueFactory<Groups, Integer>("id"));
        GroupNameColumn.setCellValueFactory(new PropertyValueFactory<Groups, String>("groupName"));
        GroupCuratorNameColumn.setCellValueFactory(new PropertyValueFactory<Groups, String>("curator"));
        GroupProffesionColumn.setCellValueFactory(new PropertyValueFactory<Groups, String>("profession"));
    }

    public void selectRows(){
        GroupTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                Groups groups = newValue;

                groupCurator = groups.getCurator();
                groupId = SearchStudentData.getIdGroup(groupCurator);
                groupNameForDelete = groups.getGroupName();

                if(groupId == 0){
                    loadAndShowLoginAlarm("/fxml/notifications/WarningUpdateGroupInfo.fxml");
                    GroupTable.getSelectionModel().clearSelection();
                }else{
                    DisplayDate.setFullGroupInfo(groupId);
                    AdditionInfoButton.setVisible(true);
                }
            }
        });
    }

    public void displayCurators(){
        ObservableList<Groups> groups = DisplayDate.getFullGroupInfo();
        setDataInPlanTable(groups);

    }

    public void startAdminGroup(){
        displayCurators();
        AdditionInfoButton.setVisible(false);
        GroupTable.getSelectionModel().clearSelection();
        selectRows();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startAdminGroup();
    }
}
