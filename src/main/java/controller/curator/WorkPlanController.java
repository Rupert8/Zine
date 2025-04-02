package controller.curator;

import interfaces.WindowActions.WindowControl;
import data.DeleteData;
import data.DisplayDate;
import data.SearchStudentData;
import hibernate.entity.WorkPlan;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.ClearValueService;
import services.ScreenService;
import start.zine.HelloApplication;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static controller.login.LoginController.curatorGroupName;
import static controller.curator.WorkGroupController.curatorFullName;

public class WorkPlanController extends HelloApplication implements Initializable, WindowControl {
    @FXML
    private TableColumn<WorkPlan, Boolean> CompletionColumn;

    @FXML
    private Button SortDateButton;

    @FXML
    private Pane PlanPane;

    @FXML
    private Label SortDateLabel;

    @FXML
    private TableColumn<WorkPlan, Date> ExecutionDateColumn;

    @FXML
    private TableColumn<WorkPlan, String> NameEventColumn;

    @FXML
    private TableColumn<WorkPlan, Integer> NumberColumn;

    @FXML
    private TableColumn<WorkPlan, String> ConfirmationNote;

    @FXML
    private TableView<WorkPlan> PlanTable;

    @FXML
    private ComboBox<Integer> SortSemesterComboBox;

    @FXML
    private DatePicker EndDatePicker;

    @FXML
    private DatePicker StartDatePicker;

    @FXML
    private Label CuratorName;

    @FXML
    public static Dialog<Boolean> saveDialog;

    @FXML
    public ComboBox<String> SortComboBox;

    @FXML
    public Button DeletePlanButton,UpdatePlanButton;

    @FXML
    public Button GroupNameButton;
    @FXML
    public Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;


    @FXML
    private HBox MenuBarHBox;
    @FXML
    private BorderPane BorderSortPane;
    @FXML
    private HBox WorkPlanPaneHBox,WorkPlanHBox,WorkStudHBox,GroupHbox;
    @FXML
    private HBox SortLabelHBox,ShowAllHbox,ChooseSemesterHbox,SearchButtonHbox,StartDateHbox,EndDateHbox,DateHbox;
    @FXML
    private HBox DeleteHbox,UpdateHbox,AddHbox;

    public static int planId;
    public static String passEventName;
    public static String passPerformer;
    public static Date passExecutionDate;
    public static int passSemester;
    public static String passDone;
    public static String passConfirmationNote;

    public static String performerNameForAdd;

    public void switchGroupPage(ActionEvent event) {
        switchWorkGroupPage(event);
    }

    public void switchStudPage(ActionEvent event) {
        switchWorkStudPage(event);
    }

    private void setDataInPlanTable(ObservableList<WorkPlan> planInfo){
        PlanTable.setItems(planInfo);

        NumberColumn.setCellValueFactory(new PropertyValueFactory<WorkPlan, Integer>("id"));
        NameEventColumn.setCellValueFactory(new PropertyValueFactory<WorkPlan, String>("eventName"));
        ExecutionDateColumn.setCellValueFactory(new PropertyValueFactory<WorkPlan, Date>("executionDate"));
        CompletionColumn.setCellValueFactory(new PropertyValueFactory<WorkPlan, Boolean>("completionNote"));
        ConfirmationNote.setCellValueFactory(new PropertyValueFactory<WorkPlan, String>("confirmationNote"));

    }

    public void displayPlanData(){
        Date startDate = Date.valueOf(StartDatePicker.getValue());
        Date endDate = Date.valueOf(EndDatePicker.getValue());

        ObservableList<WorkPlan> list = DisplayDate.getDataWithParameterPlanInfo(startDate, endDate,curatorFullName);
        if(list.isEmpty()){
            loadAndShowLoginAlarm("/fxml/notifications/emptyResultNotifications/NoRecordsDateFound.fxml");
        }else {
            setDataInPlanTable(list);
        }

    }
    private void setPlanComboBox(){
        if(SortSemesterComboBox.getItems().isEmpty()){
            SortSemesterComboBox.getItems().addAll(1,2,3,4,5,6,7,8);
        }
    }

    public void displayDataBySemester(){
        Integer semester = SortSemesterComboBox.getValue();

        ObservableList<WorkPlan> semesterList = DisplayDate.getDataBySemesterPlanInfo(semester,curatorFullName);
        if(semesterList.isEmpty()) {
            loadAndShowLoginAlarm("/fxml/notifications/emptyResultNotifications/NoRecordsSemesterFound.fxml");
        }else{
            setDataInPlanTable(semesterList);
        }
    }

    public void displayPlanInfo(){
        ObservableList<WorkPlan> planList = DisplayDate.getFullPlanInfo(curatorFullName);
        setDataInPlanTable(planList);
    }

    private void selectRows(){
        PlanTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                WorkPlan workPlan = newValue;

                passEventName = workPlan.getEventName();
                passPerformer = workPlan.getPerformer();
                passExecutionDate = workPlan.getExecutionDate();
                passSemester = workPlan.getSemester();
                passDone = workPlan.getCompletionNote();
                passConfirmationNote = workPlan.getConfirmationNote();

                planId = SearchStudentData.getIdWorkPlan(passEventName,passPerformer);
                UpdatePlanButton.setVisible(true);
                DeletePlanButton.setVisible(true);
                System.out.println("Selected plan: " + workPlan);
            }
        });
    }

    private void deletePlanData(){
        DeleteData.deletePlanDataById(planId);
        displayPlanInfo();
    }

    public void delete(){
        deletePlanData();
        displayPlanInfo();
        setPlanComboBox();
        loadAndShowSuccessNotification("/fxml/notifications/successNotifications/SuccessDeleteNotification.fxml");
    }

    public void showAddDialogPane(){
        saveDialog = loadAndShowDialog("/fxml/dialogPane.fxml",saveDialog);
        performerNameForAdd = CuratorName.getText();
        saveDialog.setOnHidden(event -> startWorkPlan());
        PlanTable.getSelectionModel().clearSelection();
    }

    public void showUpdateDialogPane(){
        if(passConfirmationNote.equals("Затверджено")){
            loadAndShowLoginAlarm("/fxml/notifications/warningNotifications/WarningConfirmationNote.fxml");
        }else{
            saveDialog =  loadAndShowDialog("/fxml/UpdateDialogPane.fxml",saveDialog);
            PlanTable.getSelectionModel().clearSelection();
            saveDialog.setOnHidden(event -> startWorkPlan());
        }
    }

    public void setCuratorName(){
        CuratorName.setText(curatorFullName);
    }

    public void setSortComboBox(){
        SortComboBox.getItems().addAll("Показати все","Датою","Семестром");
    }

    public void selectedSortComboBox(){
        if(SortComboBox.getValue().equals("Показати все")){
            StartDatePicker.setVisible(false);
            EndDatePicker.setVisible(false);
            SortDateLabel.setVisible(false);
            SortDateButton.setVisible(false);
            SortSemesterComboBox.setVisible(false);
            displayPlanInfo();
            PlanTable.getSelectionModel().clearSelection();
        }else if(SortComboBox.getValue().equals("Датою")){
            StartDatePicker.setVisible(true);
            EndDatePicker.setVisible(true);
            SortDateLabel.setVisible(true);
            SortDateButton.setVisible(true);
            SortSemesterComboBox.setVisible(false);
            SortDateButton.setVisible(true);
            displayPlanInfo();
            ClearValueService.clearSortByDateField(StartDatePicker, EndDatePicker);
            PlanTable.getSelectionModel().clearSelection();
        }else if(SortComboBox.getValue().equals("Семестром")){
            StartDatePicker.setVisible(false);
            EndDatePicker.setVisible(false);
            SortDateLabel.setVisible(false);
            SortSemesterComboBox.setVisible(true);
            SortDateButton.setVisible(false);
            displayPlanInfo();
            ClearValueService.clearSortBySemesterField(SortSemesterComboBox);
            PlanTable.getSelectionModel().clearSelection();
        }


    }

    public void startWorkPlan(){
        displayPlanInfo();
        selectRows();
        setPlanComboBox();
        setCuratorName();
        setSortComboBox();
        setGroupNameButton();
        DeletePlanButton.setVisible(false);
        UpdatePlanButton.setVisible(false);
        StartDatePicker.setVisible(false);
        EndDatePicker.setVisible(false);
        SortSemesterComboBox.setVisible(false);
        SortDateLabel.setVisible(false);
        SortDateButton.setVisible(false);
        SortComboBox.setValue("Показати все");
        PlanTable.getSelectionModel().clearSelection();
        //MenuBarHBox.setPickOnBounds(false);
        WorkPlanHBox.setPickOnBounds(false);
        WorkStudHBox.setPickOnBounds(false);
        GroupHbox.setPickOnBounds(false);
        WorkPlanPaneHBox.setPickOnBounds(false);
        SortLabelHBox.setPickOnBounds(false);
        ShowAllHbox.setPickOnBounds(false);
        AddHbox.setPickOnBounds(false);
        UpdateHbox.setPickOnBounds(false);
        DeleteHbox.setPickOnBounds(false);
        ChooseSemesterHbox.setPickOnBounds(false);
        SearchButtonHbox.setPickOnBounds(false);
        StartDateHbox.setPickOnBounds(false);
        EndDateHbox.setPickOnBounds(false);
        DateHbox.setPickOnBounds(false);
        AddHbox.setPickOnBounds(false);
        UpdateHbox.setPickOnBounds(false);
        DeleteHbox.setPickOnBounds(false);
        BorderSortPane.setPickOnBounds(false);
    }

    public void setGroupNameButton(){
        GroupNameButton.setText(curatorGroupName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //displayPlanData();
        startWorkPlan();
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
        ScreenService.paneDragged(dragEvent,MenuBarHBox);
    }

    @Override
    public void setPressedWindow(MouseEvent mouseEvent) {
        ScreenService.panePressed(mouseEvent);
    }
}
