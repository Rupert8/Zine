package controller.admin;

import controller.services.TableService;
import data.DisplayDate;
import hibernate.entity.SocialPassport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import start.zine.HelloApplication;
import tableView.SocialPassportPrototype;

import java.net.URL;
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
    private TableView<SocialPassportPrototype> SocialPassportTable;

    private ObservableList<SocialPassportPrototype> socialPassportPrototypes = FXCollections.observableArrayList();

    public static Dialog<Boolean> saveDialog;

    private void setSocialPassportColumns() {
        TableService.setDataInSocialPassportTable(socialPassportPrototypes,SocialPassportTable,NumberStudent,Name,Surname,MiddleName,Semester,Category);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSocialPassportTable();
    }
}
