package controller.curator;

import interfaces.WindowActions.WindowControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.ScreenService;
import start.zine.HelloApplication;

import java.net.URL;
import java.util.ResourceBundle;

import static controller.login.LoginController.curatorGroupName;
import static controller.curator.WorkGroupController.curatorFullName;

public class WorkStudController extends HelloApplication implements Initializable, WindowControl {
    @FXML
    private ComboBox<String> GeneralInfoComboBox;

    @FXML
    private ComboBox<String> IndividualSupportComboBox;

    @FXML
    private ComboBox<String> PromotionComboBox;

    @FXML
    private ComboBox<String> SocialActivityComboBox;

    @FXML
    private ComboBox<String> SocialPassportComboBox;

    @FXML
    private Button addInformationAboutStudentButton;

    @FXML
    private Button GroupNameButton;

    @FXML
    private Label CuratorName;

    @FXML
    private HBox MenuBarHBox;
    @FXML
    public Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;



    private void setAllComboBox(){
        GeneralInfoComboBox.getItems().setAll("Дані про освіту","Служба в ЗСУ","Інформація про батьків","Трудова Діяльність");
        SocialActivityComboBox.getItems().setAll("Громадська діяльність","Гурткова Діяльність");
        IndividualSupportComboBox.getItems().setAll("Індивідуальний супровід");
        PromotionComboBox.getItems().setAll("Заохочення");
        SocialPassportComboBox.getItems().setAll("Соціальний паспорт");
    }

    public void switchAddInformationAboutStudentPane(ActionEvent event) {
        switchAddInformationAboutStudent(event);
    }

    public void switchGeneralDataPage(){
        GeneralInfoComboBox.setOnAction(event -> {
            String selectedModel = GeneralInfoComboBox.getSelectionModel().getSelectedItem();
            if (selectedModel.equals("Загальні Дані")){
                switchWorkGeneralInfoPage(event);
            } else if (selectedModel.equals("Дані про освіту")) {
                switchWorkEducationInfoPage(event);
            } else if (selectedModel.equals("Служба в ЗСУ")) {
                switchWorkMilitaryServicePage(event);
            } else if (selectedModel.equals("Інформація про батьків")) {
                switchWorkParentsInfoPage(event);
            } else if (selectedModel.equals("Трудова Діяльність")) {
                switchWorkLaborActivityPage(event);
            }
        });
    }

    public void switchPlanPage(ActionEvent event) {
        switchWorkPlanPage(event);
    }

    public void switchSocialActivityPage(){
        SocialActivityComboBox.setOnAction(event -> {
            String selectedModel = SocialActivityComboBox.getSelectionModel().getSelectedItem();
            if (selectedModel.equals("Громадська діяльність")){
                switchWorkSocialActivityPage(event);
            } else if (selectedModel.equals("Гурткова Діяльність")) {
                switchWorkGroupActivityPage(event);
            }
        });
    }

    public void switchIndividualSupportPage(){
        IndividualSupportComboBox.setOnAction(event -> {
            String selectedModel = IndividualSupportComboBox.getSelectionModel().getSelectedItem();
            if (selectedModel.equals("Індивідуальний супровід")) {
                switchWorkIndividualSupportPage(event);
            }
        });
    }

    public void switchPromotionPage(){
        PromotionComboBox.setOnAction(event -> {
            String selectedModel = PromotionComboBox.getSelectionModel().getSelectedItem();
            if(selectedModel.equals("Заохочення")){
                switchWorkPromotionPage(event);
            }
        });
    }

    public void switchSocialPassportPage(){
        SocialPassportComboBox.setOnAction(event -> {
            String selectedModel = SocialPassportComboBox.getSelectionModel().getSelectedItem();
            if(selectedModel.equals("Соціальний паспорт")){
                switchWorkSocialPassport(event);
            }
        });
    }

    public void switchGroupPage(ActionEvent event) {
        switchWorkGroupPage(event);
    }

    public void switchStudPage(ActionEvent event) {
        switchWorkPlanPage(event);
    }

    private void showAndHideComboBox() {
        GeneralInfoComboBox.setOnMouseEntered(event -> handleComboBoxShow(GeneralInfoComboBox));
        SocialActivityComboBox.setOnMouseEntered(event -> handleComboBoxShow(SocialActivityComboBox));
        IndividualSupportComboBox.setOnMouseEntered(event -> handleComboBoxShow(IndividualSupportComboBox));
        PromotionComboBox.setOnMouseEntered(event -> handleComboBoxShow(PromotionComboBox));
        SocialPassportComboBox.setOnMouseEntered(event -> handleComboBoxShow(SocialPassportComboBox));
    }

    private void handleComboBoxShow(ComboBox<?> activeComboBox) {
        ComboBox<?>[] comboBoxes = {
                GeneralInfoComboBox, SocialActivityComboBox,
                IndividualSupportComboBox, PromotionComboBox, SocialPassportComboBox
        };

        for (ComboBox<?> comboBox : comboBoxes) {
            if (comboBox != activeComboBox && comboBox.isShowing()) {
                comboBox.hide(); // Закрити всі ComboBox, окрім активного
            }
        }

        if (!activeComboBox.isShowing()) {
            activeComboBox.show(); // Відкрити активний ComboBox
        }
    }

    public void setCuratorName(){
        CuratorName.setText(curatorFullName);
    }

    public void setGroupName(){
        GroupNameButton.setText(curatorGroupName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAndHideComboBox();
        setAllComboBox();
        switchGeneralDataPage();
        switchSocialActivityPage();
        switchIndividualSupportPage();
        switchPromotionPage();
        switchSocialPassportPage();
        setCuratorName();
        setGroupName();
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
