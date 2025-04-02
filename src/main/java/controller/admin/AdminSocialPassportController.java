package controller.admin;

import hibernate.entity.SocialPassport;
import interfaces.WindowActions.WindowControl;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.ScreenService;
import services.TableService;
import data.DisplayDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import start.zine.HelloApplication;
import tableView.SocialPassportPrototype;

import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static services.exportExel.ExelExportService.exportToExcel;

public class AdminSocialPassportController extends HelloApplication implements Initializable, WindowControl {

    @FXML
    private TableColumn<SocialPassportPrototype, String> Category;

    @FXML
    private TableColumn<SocialPassportPrototype, String> MiddleName;

    @FXML
    private TableColumn<SocialPassportPrototype, String> Name;

    @FXML
    private TableColumn<SocialPassportPrototype, Integer> NumberStudent;

    @FXML
    private TableColumn<SocialPassportPrototype, Integer> Semester;

    @FXML
    private TableColumn<SocialPassportPrototype, String> Surname;

    @FXML
    private TableColumn<SocialPassportPrototype, String> GroupName;

    @FXML
    private TableView<SocialPassportPrototype> SocialPassportTable;

    @FXML
    private ComboBox<String> SortByGroupComboBox;

    @FXML
    private ComboBox<String> SortCategoryComboBox;

    @FXML
    private ComboBox<String> SortSocialPassportComboBox;

    @FXML
    private HBox Hbox;
    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

    @FXML
    private HBox WorkPlanHBox,WorkTeacherHBox,SocialPassportPaneHBox,StudentHBox,GroupHBox,SocialPassportHBox;
    @FXML
    private HBox SortLabelHBox,SortComboBoxHBox,SortByCategoryHBox,SortByGroupHBox;
    @FXML
    private HBox ExtendedInfoHbox,AddHbox;

    private ObservableList<SocialPassportPrototype> socialPassportPrototypes = FXCollections.observableArrayList();

    public static Dialog<Boolean> saveDialog;

    private void setSocialPassportColumns() {
        TableService.setDataInSocialPassportTable(socialPassportPrototypes,SocialPassportTable,NumberStudent,Name,Surname,MiddleName,Semester,Category,GroupName);
    }

    public void loadAddCategoryDialog(){
        saveDialog = loadAndShowDialog("/fxml/admin/adminDialogFxml/AddCategoryDialogPane.fxml",saveDialog);
        saveDialog.setOnHidden(event -> startSocialPassport());
    }

    private void setSocialPassportTable(){
        getSocialPassportData();
        setSocialPassportColumns();
    }

    private void getSocialPassportData() {
        socialPassportPrototypes = DisplayDate.getSocialPassportInfo();

    }

    private void setSortSocialPassportComboBox(){
        if(SortSocialPassportComboBox.getSelectionModel().getSelectedItem() == null){
            SortSocialPassportComboBox.getItems().addAll("Показати все","Групою","Категорією");
        }
    }

    public void displayDataByGroupName(){
        String groupName = SortByGroupComboBox.getValue();
        if(groupName != null){
            ObservableList<SocialPassportPrototype> groupList = DisplayDate.getDataByGroupNameForAdminSocialPassport(groupName);
            TableService.setDataInSocialPassportTable(groupList,SocialPassportTable,NumberStudent,Name,Surname,MiddleName,Semester,Category,GroupName);
        }else {
            throw new IllegalArgumentException("група не може бути null");
        }

    }

    public void displayDataByCategoryName(){
        String categoryName = SortCategoryComboBox.getValue();
        if(categoryName != null){
            ObservableList<SocialPassportPrototype> categoryList = DisplayDate.getDataByCategoryNameForAdminSocialPassport(categoryName);
            TableService.setDataInSocialPassportTable(categoryList,SocialPassportTable,NumberStudent,Name,Surname,MiddleName,Semester,Category,GroupName);
        }else {
            throw new IllegalArgumentException("категорія не може бути null");
        }

    }

    private void setSortByGroupComboBox(){
        List<String> groupName = DisplayDate.getGroupName();
        if(groupName != null){
            System.out.print(groupName);
            SortByGroupComboBox.getItems().setAll(groupName);
        }else{
            throw new IllegalArgumentException("не може бути null");
        }

    }

    private void setSortCategoryComboBox(){
        List<String> categoryName = DisplayDate.getCategoryInComboBox();
        SortCategoryComboBox.getItems().setAll(categoryName);

    }


    private void startSocialPassport(){
        setSocialPassportTable();
        setSortSocialPassportComboBox();
        setSortByGroupComboBox();
        setSortCategoryComboBox();
        SortByGroupComboBox.setVisible(false);
        SortCategoryComboBox.setVisible(false);
        SocialPassportTable.getSelectionModel().clearSelection();
        StudentHBox.setPickOnBounds(false);
        WorkTeacherHBox.setPickOnBounds(false);
        SocialPassportHBox.setPickOnBounds(false);
        GroupHBox.setPickOnBounds(false);
        WorkPlanHBox.setPickOnBounds(false);
        SortComboBoxHBox.setPickOnBounds(false);
        SortLabelHBox.setPickOnBounds(false);
        AddHbox.setPickOnBounds(false);
        ExtendedInfoHbox.setPickOnBounds(false);
        SortByGroupHBox.setPickOnBounds(false);
        SortByCategoryHBox.setPickOnBounds(false);
        SocialPassportPaneHBox.setPickOnBounds(false);
    }

    public void selectedSortSocialPassportComboBox(){
        if(SortSocialPassportComboBox.getValue().equals("Показати все")){
            setSocialPassportTable();
            SortCategoryComboBox.setVisible(false);
            SortByGroupComboBox.setVisible(false);
            SocialPassportTable.getSelectionModel().clearSelection();
        }else if(SortSocialPassportComboBox.getValue().equals("Групою")){
            SortByGroupComboBox.setVisible(true);
            SortByGroupComboBox.getItems().clear();
            setSortByGroupComboBox();
            SortCategoryComboBox.setVisible(false);
            SocialPassportTable.getSelectionModel().clearSelection();
        }else if(SortSocialPassportComboBox.getValue().equals("Категорією")){
            SortCategoryComboBox.setVisible(true);
            SortCategoryComboBox.getItems().clear();
            setSortCategoryComboBox();
            SortByGroupComboBox.setVisible(false);
            SocialPassportTable.getSelectionModel().clearSelection();
        }
    }

    public void exportExelSocialPassport(){
        List<SocialPassport> socialPassportList = DisplayDate.selectStudentSocialPassportInfoForExport();
        String downloadFolder = System.getProperty("user.home") + "\\Downloads";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        String filePath = downloadFolder + "\\Соціальний_паспорт_експорт_" + currentDate + ".xlsx";
        exportToExcel(socialPassportList, filePath);
        loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessExportNotification.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startSocialPassport();
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
