package controller;

import data.DeleteData;
import data.DisplayDate;
import hibernate.entity.WorkPlan;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import start.zine.HelloApplication;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

import static controller.WorkGroupController.curatorFullName;

public class WorkPlanController extends HelloApplication {
    @FXML
    private TableColumn<WorkPlan, Boolean> CompletionColumn;

    @FXML
    private TableColumn<WorkPlan, Date> ExecutionDateColumn;

    @FXML
    private TableColumn<WorkPlan, String> NameEventColumn;

    @FXML
    private TableColumn<WorkPlan, Integer> NumberColumn;

    @FXML
    private TableColumn<WorkPlan, String> Performer;

    @FXML
    private TableView<WorkPlan> PlanTable;

    @FXML
    private ComboBox<Integer> SemesterComboBox;

    @FXML
    private DatePicker EndDatePicker;

    @FXML
    private DatePicker StartDatePicker;

    @FXML
    private Label CuratorName;

    @FXML
    public Dialog<Boolean> saveDialog;

    public static int planId;
    public static String passEventName;
    public static String passPerformer;
    public static Date passExecutionDate;
    public static int passSemester;
    public static String passDone;

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
        Performer.setCellValueFactory(new PropertyValueFactory<WorkPlan, String>("performer"));
        CompletionColumn.setCellValueFactory(new PropertyValueFactory<WorkPlan, Boolean>("completionNote"));
    }

    public void displayPlanData(){
        int semester = SemesterComboBox.getValue();
        Date startDate = Date.valueOf(StartDatePicker.getValue());
        Date endDate = Date.valueOf(EndDatePicker.getValue());

        ObservableList<WorkPlan> list = DisplayDate.getDataWithParametrPlanInfo(startDate, endDate, semester);
        if(list == null){
            System.out.println("List is null");
        }else {
            setDataInPlanTable(list);
        }

    }
    private void setPlanComboBox(){
        SemesterComboBox.getItems().addAll(1,2,3,4,5,6,7,8);
    }

    public void displayDataBySemester(){
        Integer semester = SemesterComboBox.getValue();
        if(semester != null){
            ObservableList<WorkPlan> semesterList = DisplayDate.getDataBySemesterPlanInfo(semester);
            setDataInPlanTable(semesterList);
        }else {
            System.out.println("Semester is null");
        }

    }

    public void displayPlanInfo(){
        ObservableList<WorkPlan> planList = DisplayDate.getFullPlanInfo();
        setDataInPlanTable(planList);
    }

    private void selectRows(){
        PlanTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                WorkPlan workPlan = newValue;

                planId = workPlan.getId();
                passEventName = workPlan.getEventName();
                passPerformer = workPlan.getPerformer();
                passExecutionDate = workPlan.getExecutionDate();
                passSemester = workPlan.getSemester();
                passDone = workPlan.getCompletionNote();

                System.out.println("Selected plan: " + workPlan);
            }
        });
    }

    private void deletePlanData(){
        DeleteData.deletePlanDataById(planId);
    }

    public void delete(){
        deletePlanData();
        displayDataBySemester();
        setPlanComboBox();
    }

    private void loadAndShowAddDialog(String linkFxml){
        try {
            // Завантаження FXML файлу
            FXMLLoader loader = new FXMLLoader(getClass().getResource(linkFxml));
            DialogPane dialogPane = loader.load();

            // Створення діалогового вікна
            Dialog<Boolean> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.getDialogPane().getScene().getWindow().setOnCloseRequest(event -> {
                dialog.close();
            });
            saveDialog = dialog;
            dialog.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showAddDialogPane(){
        saveDialog = loadAndShowDialog("/fxml/dialogPane.fxml",saveDialog);
    }

    public void showUpdateDialogPane(){
        saveDialog =  loadAndShowDialog("/fxml/UpdateDialogPane.fxml",saveDialog);
    }

    public void setCuratorName(){
        CuratorName.setText(curatorFullName);
    }

    public void initialize() {
        //displayPlanData();


        Platform.runLater(() -> {
            displayPlanInfo();
            selectRows();
            setPlanComboBox();
            setCuratorName();
        });
    }
}
