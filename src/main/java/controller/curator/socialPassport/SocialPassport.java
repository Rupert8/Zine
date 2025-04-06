package controller.curator.socialPassport;

import data.DisplayDate;
import hibernate.entity.CircleActivity;
import interfaces.WindowActions.WindowControl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.ScreenService;
import services.TableService;
import start.zine.HelloApplication;

import java.net.URL;
import java.util.ResourceBundle;

public class SocialPassport extends HelloApplication implements Initializable, WindowControl {
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

    @FXML
    private HBox Hbox;

    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

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
