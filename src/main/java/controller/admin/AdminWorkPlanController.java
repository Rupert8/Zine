package controller.admin;

import data.DeleteData;
import data.DisplayDate;
import data.SearchStudentData;
import hibernate.entity.WorkPlan;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import start.zine.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static controller.WorkGroupController.curatorFullName;

public class AdminWorkPlanController extends HelloApplication implements Initializable {
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
    private TableColumn<WorkPlan, String> PerformerColumn;

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

    public static int AdminPlanId;
    public static String passEventName;
    public static String passPerformer;
    public static Date passExecutionDate;
    public static int passSemester;
    public static String passDone;
    public static String passConfirmationNote;

    public static String performerNameForAdd;


    private void setDataInPlanTable(ObservableList<WorkPlan> planInfo){
        PlanTable.setItems(planInfo);

        NumberColumn.setCellValueFactory(new PropertyValueFactory<WorkPlan, Integer>("id"));
        NameEventColumn.setCellValueFactory(new PropertyValueFactory<WorkPlan, String>("eventName"));
        ExecutionDateColumn.setCellValueFactory(new PropertyValueFactory<WorkPlan, Date>("executionDate"));
        PerformerColumn.setCellValueFactory(new PropertyValueFactory<WorkPlan, String>("performer"));
        CompletionColumn.setCellValueFactory(new PropertyValueFactory<WorkPlan, Boolean>("completionNote"));
        ConfirmationNote.setCellValueFactory(new PropertyValueFactory<WorkPlan, String>("confirmationNote"));

    }

    public void displayPlanData(){
        Date startDate = Date.valueOf(StartDatePicker.getValue());
        Date endDate = Date.valueOf(EndDatePicker.getValue());

        ObservableList<WorkPlan> list = DisplayDate.getDataWithParameterForAdminPlanInfo(startDate, endDate);
        if(list == null){
            System.out.println("List is null");
        }else {
            setDataInPlanTable(list);
        }

    }
    private void setPlanComboBox(){
        SortSemesterComboBox.getItems().addAll(1,2,3,4,5,6,7,8);
    }

    public void displayDataBySemester(){
        Integer semester = SortSemesterComboBox.getValue();
        if(semester != null){
            ObservableList<WorkPlan> semesterList = DisplayDate.getDataBySemesterForAdminPlanInfo(semester);
            setDataInPlanTable(semesterList);
        }else {
            throw new IllegalArgumentException("Семестер не може бути null");
        }

    }

    public void displayPlanInfo(){
        ObservableList<WorkPlan> planList = DisplayDate.getPlanForAdminInfo();
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

                AdminPlanId = SearchStudentData.getIdWorkPlan(passEventName,passPerformer);

                UpdatePlanButton.setVisible(true);
                DeletePlanButton.setVisible(true);
                System.out.println("Selected plan: " + workPlan);
            }
        });
    }

    private void deletePlanData(){
        DeleteData.deletePlanDataById(AdminPlanId);
        displayPlanInfo();
    }

    public void delete(){
        deletePlanData();
        displayDataBySemester();
        setPlanComboBox();
    }

    public void showAddDialogPane(){
        saveDialog = loadAndShowDialog("/fxml/admin/adminDialogFxml/AddPlanDialog.fxml",saveDialog);
        PlanTable.getSelectionModel().clearSelection();
    }

    public void showUpdateDialogPane(){
        saveDialog =  loadAndShowDialog("/fxml/admin/adminDialogFxml/UpdateAdminPlanDialog.fxml",saveDialog);
        PlanTable.getSelectionModel().clearSelection();
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
            PlanTable.getSelectionModel().clearSelection();
        }else if(SortComboBox.getValue().equals("Семестром")){
            StartDatePicker.setVisible(false);
            EndDatePicker.setVisible(false);
            SortDateLabel.setVisible(false);
            SortSemesterComboBox.setVisible(true);
            SortDateButton.setVisible(false);
            displayPlanInfo();
            PlanTable.getSelectionModel().clearSelection();
        }


    }

    public void start(){
        DeletePlanButton.setVisible(false);
        UpdatePlanButton.setVisible(false);
        StartDatePicker.setVisible(false);
        EndDatePicker.setVisible(false);
        SortSemesterComboBox.setVisible(false);
        SortDateLabel.setVisible(false);
        SortDateButton.setVisible(false);
        SortComboBox.setValue("Показати все");
        displayPlanInfo();
        PlanTable.getSelectionModel().clearSelection();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //displayPlanData();
        displayPlanInfo();
        selectRows();
        setPlanComboBox();
        setSortComboBox();
        start();
    }
}
