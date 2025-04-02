package controller.passwordRecovery;

import interfaces.WindowActions.WindowControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.ScreenService;
import services.email.EmailService;
import start.zine.HelloApplication;

public class PassPageController extends HelloApplication implements WindowControl {
    @FXML
    public TextField EmailField;

    public static String userEmail;

    @FXML
    private HBox Hbox;
    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

    public void recoveryPassword(ActionEvent event) {
        String email = EmailField.getText();
        userEmail = email;

        System.out.println("=== Скидання пароля ===");
        System.out.print("Введіть ваш email: ");

        // Надсилаємо код на email
        EmailService.sendVerificationCode(email);
        switchToCodeFxml(event);
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
