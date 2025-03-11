package controller.socialPassport;

import data.DisplayDate;
import hibernate.entity.CircleActivity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import services.TableService;
import start.zine.HelloApplication;

import java.net.URL;
import java.util.ResourceBundle;

public class SocialPassport extends HelloApplication implements Initializable {
    @FXML
    private TableColumn<hibernate.entity.SocialPassport,String> Category;

    @FXML
    private TableColumn<hibernate.entity.SocialPassport, String> MiddleNameColumn;

    @FXML
    private TableColumn<hibernate.entity.SocialPassport, String> NameColumn;

    @FXML
    private TableColumn<hibernate.entity.SocialPassport, Integer> NumberColumn;

    @FXML
    private TableColumn<hibernate.entity.SocialPassport, Integer> Semester;

    @FXML
    private TableView<hibernate.entity.SocialPassport> SocialPassportTable;

    @FXML
    private TableColumn<hibernate.entity.SocialPassport, String> SurnameColumn;

    public void back(ActionEvent event) {
        switchWorkStudPage(event);
    }

    public void displaySocialPassport() {
        ObservableList<hibernate.entity.SocialPassport> socialPassports = DisplayDate.tableSocialPassport();
        TableService.setDataInCuratorSocialPassportTable(socialPassports,SocialPassportTable,NumberColumn,NameColumn,SurnameColumn,MiddleNameColumn,Semester,Category);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displaySocialPassport();
    }
}
