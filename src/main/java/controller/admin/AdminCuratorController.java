package controller.admin;

import data.DeleteData;
import data.DisplayDate;
import data.SearchStudentData;
import hibernate.entity.Curators;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import start.zine.HelloApplication;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminCuratorController extends HelloApplication implements Initializable{

    @FXML
    private TableColumn<Curators, String> CuratorGroupColumn;

    @FXML
    private TableColumn<Curators, String> CuratorMiddleNameColumn;

    @FXML
    private TableColumn<Curators, String> CuratorNameColumn;

    @FXML
    private TableColumn<Curators, Integer> CuratorNumberColumn;

    @FXML
    private TableColumn<Curators, String> CuratorSurnameColumn;

    @FXML
    private TableColumn<Curators, String> CuratorEmailColumn;

    @FXML
    private TableView<Curators> CuratorTable;

    @FXML
    private Button AdditionCuratorButton;

    public static Dialog<Boolean> saveDialog;

    public static int curatorId;
    public static String curatorName;
    public static String curatorSurname;
    public static String curatorMiddleName;
    public static String curatorGroupName;
    public static String curatorEmail;

    public void showAddDialog(){
        saveDialog = loadAndShowDialog("/fxml/admin/adminDialogFxml/AddCuratorDialogPane.fxml",saveDialog);
        saveDialog.setOnHidden(event -> correctLoadPane());
    }

    public void showAdditionDialog(){
        saveDialog = loadAndShowDialog("/fxml/admin/adminDialogFxml/AdditionalCuratorDialogPane.fxml",saveDialog);
        saveDialog.setOnHidden(event -> correctLoadPane());
    }

    private void setDataInPlanTable(ObservableList<Curators> curatorInfo){
        CuratorTable.setItems(curatorInfo);

        CuratorNumberColumn.setCellValueFactory(new PropertyValueFactory<Curators, Integer>("id"));
        CuratorNameColumn.setCellValueFactory(new PropertyValueFactory<Curators, String>("name"));
        CuratorSurnameColumn.setCellValueFactory(new PropertyValueFactory<Curators, String>("surname"));
        CuratorMiddleNameColumn.setCellValueFactory(new PropertyValueFactory<Curators, String>("middleName"));
        CuratorGroupColumn.setCellValueFactory(new PropertyValueFactory<Curators, String>("group"));
        CuratorEmailColumn.setCellValueFactory(new PropertyValueFactory<Curators, String>("Email"));
    }

    public void displayCurators(){
        ObservableList<Curators> curators = DisplayDate.getCurators();
        setDataInPlanTable(curators);

    }

    private void selectRows(){
        CuratorTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                Curators curators = newValue;

                curatorName = curators.getName();
                curatorSurname = curators.getSurname();
                curatorMiddleName = curators.getMiddleName();
                curatorEmail = curators.getEmail();
                curatorGroupName = curators.getGroup();

                curatorId = SearchStudentData.getIdCurator(curatorEmail);

                AdditionCuratorButton.setVisible(true);
            }
        });
    }

    public void correctLoadPane(){
        AdditionCuratorButton.setVisible(false);
        displayCurators();
        selectRows();
        CuratorTable.getSelectionModel().clearSelection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        correctLoadPane();
    }
}
