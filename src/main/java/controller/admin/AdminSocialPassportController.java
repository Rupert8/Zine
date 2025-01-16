package controller.admin;

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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminSocialPassportController extends HelloApplication implements Initializable {

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


    private ObservableList<SocialPassportPrototype> socialPassportPrototypes = FXCollections.observableArrayList();

    public static Dialog<Boolean> saveDialog;

    private void setSocialPassportColumns() {
        TableService.setDataInSocialPassportTable(socialPassportPrototypes,SocialPassportTable,NumberStudent,Name,Surname,MiddleName,Semester,Category,GroupName);
    }

    public void loadAddCategoryDialog(){
        saveDialog = loadAndShowDialog("/fxml/admin/adminDialogFxml/AddCategoryDialogPane.fxml",saveDialog);
    }

    private void setSocialPassportTable(){
        getSocialPassportData();
        setSocialPassportColumns();
    }

    private void getSocialPassportData() {
        socialPassportPrototypes = DisplayDate.getSocialPassportInfo();

    }

    private void setSortSocialPassportComboBox(){
        SortSocialPassportComboBox.getItems().addAll("Показати все","Групою","Категорією");
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


    private void start(){
        setSocialPassportTable();
        setSortSocialPassportComboBox();
        setSortByGroupComboBox();
        setSortCategoryComboBox();
        SortByGroupComboBox.setVisible(false);
        SortCategoryComboBox.setVisible(false);
        SocialPassportTable.getSelectionModel().clearSelection();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start();
    }
}
