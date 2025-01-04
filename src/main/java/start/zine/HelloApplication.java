package start.zine;

import controller.LoginController;
import data.DisplayDate;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    private Parent root;
    private Stage stage;
    private Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);
        stage.setTitle("Журнал куратора");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public Dialog<Boolean> loadAndShowDialog(String linkFxml, Dialog<Boolean> saveDialog){
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
        return saveDialog;
    }

    public void switchNewPanel(ActionEvent event, String fxml) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(true);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public void switchScene(Node currentNode, String fxmlFile) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(200), currentNode);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(event -> {
            try {
                // Load the new FXML after the fade-out animation finishes
                Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));

                // Create a new scene
                Stage stage = (Stage) currentNode.getScene().getWindow();
                Scene scene = new Scene(root);
                // Add your CSS file here:
                //scene.getStylesheets().add(getClass().getResource("/css/workPlanPageCss/changeButtonColor.css").toExternalForm());

                // Apply fade-in animation for the new scene
                stage.setScene(scene);
                FadeTransition fadeIn = new FadeTransition(Duration.millis(200), root);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        fadeOut.play();
    }

    public void switchWorkPlanPage(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/WorkPlanPage.fxml");
    }

    public void switchWorkStudPage(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/WorkStudPage.fxml");
    }

    public void switchWorkGroupPage(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/WorkGroupPage.fxml");
    }

    public void switchWorkGeneralInfoPage(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/generalInfoFxml/GeneralData.fxml");
    }

    public void switchWorkEducationInfoPage(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/generalInfoFxml/EducationInfo.fxml");
    }

    public void switchWorkLaborActivityPage(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/generalInfoFxml/StudentJob.fxml");
    }

    public void switchWorkParentsInfoPage(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/generalInfoFxml/ParentsInfo.fxml");
    }

    public void switchWorkMilitaryServicePage(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/generalInfoFxml/MilitaryService.fxml");
    }

    public void switchWorkGroupActivityPage(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/socialActivityFxml/GroupActivity.fxml");
    }

    public void switchWorkSocialActivityPage(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/socialActivityFxml/SocialActivity.fxml");
    }

    public void switchWorkIndividualSupportPage(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/individualSupportFxml/IndividualSupport.fxml");
    }

    public void switchWorkPromotionPage(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/promotionFxml/Promotion.fxml");
    }

    public void switchWorkSocialPassport(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/socialPassport/CuratorSocialPassport.fxml");
    }

    public void switchAddInformationAboutStudent(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/curator/AddInformationAboutStudent.fxml");
    }

    public void switchToAdminMain(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/admin/AdminMain.fxml");
    }

    public void switchToAdminCurator(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/admin/AdminCurator.fxml");
    }

    public void switchToAdminGroup(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/admin/AdminGroup.fxml");
    }

    public void switchToAdminSocialPassport(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/admin/AdminSocialPassport.fxml");
    }

    public void switchToPassPage(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/passPage.fxml");
    }

    public void switchToLoginPage(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/Login.fxml");
    }

    public void switchToExtendedStudentInfo(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/admin/adminDialogFxml/ExtendedInformationAboutStudent.fxml");
    }

    public void switchToAddSocialCategoryInfo(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/admin/adminDialogFxml/AddSocialCategoryDialogPane.fxml");
    }
    public static void main(String[] args) {
        //Session session = HibernateUtil.getSessionFactory().openSession();
        //HibernateUtil.shutdown();
        launch();
    }
}