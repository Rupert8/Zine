package controller.passwordRecovery;

import data.UpdateData;
import interfaces.WindowActions.WindowControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.ScreenService;
import services.email.EmailService;
import start.zine.StartApplication;

import java.io.IOException;

import static controller.passwordRecovery.PassPageController.userEmail;

public class VerifyCodeController extends StartApplication implements WindowControl {
    @FXML
    private Pane CodePane;

    @FXML
    private Pane NewPasswordPane;

    @FXML
    private TextField CodeField;

    @FXML
    private TextField NewPasswordField;

    @FXML
    private HBox Hbox;
    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

    public void loadAndShowCodeWarning(String linkFxml){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(linkFxml));
            DialogPane dialogPane = loader.load();

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
            loadAndShowCodeWarning("/fxml/notifications/successNotifications/SuccessVerifyCode.fxml");
            NewPasswordPane.setVisible(true);
        }else{
            loadAndShowCodeWarning("/fxml/recoveryPassword/Message.fxml");
            throw new IllegalArgumentException("Код неправильний");
        }
    }

    public void setNewPassword(ActionEvent event){
        String newPassword = NewPasswordField.getText();
        UpdateData.updateUserPassword(userEmail, newPassword);
        loadAndShowCodeWarning("/fxml/notifications/successNotifications/SuccessPasswordChanged.fxml");
        switchToLoginPage(event);
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
