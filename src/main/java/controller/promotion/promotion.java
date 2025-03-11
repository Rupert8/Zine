package controller.promotion;

import data.DisplayDate;
import hibernate.entity.IndividualSupport;
import hibernate.entity.Promotion;
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

public class promotion extends HelloApplication implements Initializable {
    @FXML
    private TableColumn<Promotion ,String> Content;

    @FXML
    private TableColumn<Promotion, java.sql.Date> Date;

    @FXML
    private TableView<Promotion> PromotionTable;

    @FXML
    private TableColumn<Promotion, String> MiddleNameColumn;

    @FXML
    private TableColumn<Promotion, String> NameColumn;

    @FXML
    private TableColumn<Promotion, Integer> NumberColumn;

    @FXML
    private TableColumn<Promotion, Integer> Semester;

    @FXML
    private TableColumn<Promotion, String> SurnameColumn;
    public void back(ActionEvent event) {
        switchWorkStudPage(event);
    }

    public void displayPromotion() {
        ObservableList<Promotion> promotions = DisplayDate.tablePromotion();
        TableService.setDataInPromotionTable(promotions,PromotionTable,NumberColumn,NameColumn,SurnameColumn,MiddleNameColumn,Semester,Date,Content);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayPromotion();
    }
}
