package controller.admin;

import interfaces.WindowActions.WindowControl;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.ScreenService;
import start.zine.HelloApplication;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminCuratorController extends HelloApplication implements Initializable, WindowControl {

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

    @FXML
    private HBox Hbox;
    @FXML
    private Button minimizeWindowButton,maximizeWindowButton;

    @FXML
    private HBox WorkPlanHBox,WorkTeacherHBox,CuratorPaneHBox,StudentHBox,GroupHBox,SocialPassportHBox;
    @FXML
    private HBox ExtendedInfoHbox,AddHbox;

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
        StudentHBox.setPickOnBounds(false);
        WorkTeacherHBox.setPickOnBounds(false);
        SocialPassportHBox.setPickOnBounds(false);
        GroupHBox.setPickOnBounds(false);
        WorkPlanHBox.setPickOnBounds(false);
        AddHbox.setPickOnBounds(false);
        ExtendedInfoHbox.setPickOnBounds(false);
        CuratorPaneHBox.setPickOnBounds(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        correctLoadPane();
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
