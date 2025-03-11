package controller.socialActivity;

import data.DisplayDate;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import services.TableService;
import start.zine.HelloApplication;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class SocialActivity extends HelloApplication implements Initializable {
    @FXML
    private TableColumn<hibernate.entity.SocialActivity, String> Activity;

    @FXML
    private TableColumn<hibernate.entity.SocialActivity, Date> Date;

    @FXML
    private TableColumn<hibernate.entity.SocialActivity, String> MiddleNameColumn;

    @FXML
    private TableColumn<hibernate.entity.SocialActivity, String> NameColumn;

    @FXML
    private TableColumn<hibernate.entity.SocialActivity, Integer> NumberColumn;

    @FXML
    private TableColumn<hibernate.entity.SocialActivity, Integer> Semester;

    @FXML
    private TableView<hibernate.entity.SocialActivity> SocialActivityTable;

    @FXML
    private TableColumn<hibernate.entity.SocialActivity, String> SurnameColumn;

    public void back(ActionEvent event) {
        switchWorkStudPage(event);
    }

    public void displaySocialActivity() {
        ObservableList<hibernate.entity.SocialActivity> socialActivities = DisplayDate.tableSocialActivity();
        TableService.setDataInSocialActivityTable(socialActivities,SocialActivityTable,NumberColumn,NameColumn,SurnameColumn,MiddleNameColumn,Semester,Date,Activity);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displaySocialActivity();
    }
}
