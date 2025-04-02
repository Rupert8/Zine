package controller.curator.promotion;

import data.DisplayDate;
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

public class Promotion extends HelloApplication implements Initializable, WindowControl {
    @FXML
    private TableColumn<hibernate.entity.Promotion,String> Content;

    @FXML
    private TableColumn<hibernate.entity.Promotion, java.sql.Date> Date;

    @FXML
    private TableView<hibernate.entity.Promotion> PromotionTable;

    @FXML
    private TableColumn<hibernate.entity.Promotion, String> MiddleNameColumn;

    @FXML
    private TableColumn<hibernate.entity.Promotion, String> NameColumn;

    @FXML
    private TableColumn<hibernate.entity.Promotion, Integer> NumberColumn;

    @FXML
    private TableColumn<hibernate.entity.Promotion, Integer> Semester;

    @FXML
    private TableColumn<hibernate.entity.Promotion, String> SurnameColumn;

    @FXML
    private HBox Hbox;

    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

    public void back(ActionEvent event) {
        switchWorkStudPage(event);
    }

    public void displayPromotion() {
        ObservableList<hibernate.entity.Promotion> promotions = DisplayDate.tablePromotion();
        TableService.setDataInPromotionTable(promotions,PromotionTable,NumberColumn,NameColumn,SurnameColumn,MiddleNameColumn,Semester,Date,Content);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayPromotion();
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
