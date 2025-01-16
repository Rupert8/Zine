package controller.passwordRecovery;

import data.UpdateData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.email.EmailSender;
import services.email.EmailService;
import start.zine.HelloApplication;

import java.io.IOException;

import static controller.passwordRecovery.PassPageController.userEmail;

public class VerifyCodeController extends HelloApplication {
    @FXML
    private Pane CodePane;

    @FXML
    private Pane NewPasswordPane;

    @FXML
    private TextField CodeField;

    @FXML
    private TextField NewPasswordField;

    private void loadAndShowCodeWarning(String linkFxml){
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
            dialog.getDialogPane().getScene().getWindow().setOnHidden(event -> {
                CodeField.clear();
            });
            Stage dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();
            dialogStage.getIcons().add(new Image(getClass().getResource("/icon/info-icon.png").toExternalForm())); // Замініть "icon.png" на шлях до вашого файл
            dialog.setTitle("Помилка!");
            dialog.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void verifyInputCode(){
        String code = CodeField.getText();
        boolean trueCode = EmailService.isVerifyCode(code);

        if(trueCode){
            System.out.print("код правильний");
            NewPasswordPane.setVisible(true);
        }else{
            loadAndShowCodeWarning("/recoveryPassword/Message.fxml");
            throw new IllegalArgumentException("Код неправильний");
        }
    }

    public void setNewPassword(ActionEvent event){
        String newPassword = NewPasswordField.getText();
        UpdateData.updateUserPassword(userEmail, newPassword);
        switchToLoginPage(event);
    }
}
