package controller.admin;

import interfaces.WindowActions.WindowControl;
import data.DisplayDate;
import data.SearchStudentData;
import hibernate.entity.Groups;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.ScreenService;
import start.zine.HelloApplication;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminGroupController extends HelloApplication implements Initializable, WindowControl {
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
    @FXML
    private Button AssignCuratorButton;

    @FXML
    private HBox Hbox;
    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

    @FXML
    private HBox WorkPlanHBox,WorkTeacherHBox,GroupPaneHBox,StudentHBox,GroupHBox,SocialPassportHBox;
    @FXML
    private HBox ExtendedInfoHbox,AddHbox,SetCuratorGroupHBox;

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
        saveDialog = loadAndShowDialog("/fxml/admin/adminDialogFxml/AddGroupDialogPane.fxml",saveDialog,"Реєстрація групи");
        saveDialog.setOnHidden(event -> startAdminGroup());
    }

    public void loadAdditionInfoDialog(){
        saveDialog = loadAndShowDialog("/fxml/admin/adminDialogFxml/AdditionalGroupDialogPane.fxml",saveDialog,"Редагування та видалення даних групи");
        saveDialog.setOnHidden(event -> startAdminGroup());
    }

    public void loadAssignCuratorDialog(){
        saveDialog = loadAndShowDialog("/fxml/admin/adminDialogFxml/AssignCuratorDialog.fxml",saveDialog,"Призначити Куратора");
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
                groupName = groups.getGroupName();
                groupId = SearchStudentData.getIdGroup(groupName);
                groupNameForDelete = groups.getGroupName();

                if(groupCurator == null){
                    DisplayDate.setFullGroupInfo(groupId);
                    AdditionInfoButton.setVisible(true);
                    AssignCuratorButton.setVisible(true);
                }else{
                    DisplayDate.setFullGroupInfo(groupId);
                    AdditionInfoButton.setVisible(true);
                    AssignCuratorButton.setVisible(false);
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
        AssignCuratorButton.setVisible(false);
        GroupTable.getSelectionModel().clearSelection();
        selectRows();
        GroupTable.getSelectionModel().clearSelection();
        StudentHBox.setPickOnBounds(false);
        WorkTeacherHBox.setPickOnBounds(false);
        SocialPassportHBox.setPickOnBounds(false);
        GroupHBox.setPickOnBounds(false);
        WorkPlanHBox.setPickOnBounds(false);
        AddHbox.setPickOnBounds(false);
        ExtendedInfoHbox.setPickOnBounds(false);
        GroupPaneHBox.setPickOnBounds(false);
        SetCuratorGroupHBox.setPickOnBounds(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startAdminGroup();
    }

    @Override
    public void setMinimizeWindowButton() {
        ScreenService.minimized_Window(minimizeWindowButton);
    }

    @Override
    public void setMaximizeWindowButton() {
        ScreenService.maximized_Window(maximizeWindowButton.getScene().getWindow());
    }

    @Override
    public void setCloseWindow() {
        ScreenService.close_Window();
    }

    @Override
    public void setDragWindow(MouseEvent dragEvent) {
        ScreenService.paneDragged(dragEvent,Hbox);
    }

    @Override
    public void setPressedWindow(MouseEvent mouseEvent) {
        ScreenService.panePressed(mouseEvent);
    }
}
