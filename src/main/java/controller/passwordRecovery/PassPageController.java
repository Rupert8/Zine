package controller.passwordRecovery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import services.email.EmailService;
import start.zine.HelloApplication;

public class PassPageController extends HelloApplication {
    @FXML
    public TextField EmailField;

    public static String userEmail;

    public void recoveryPassword(ActionEvent event) {
        String email = EmailField.getText();
        userEmail = email;

        System.out.println("=== Скидання пароля ===");
        System.out.print("Введіть ваш email: ");

        // Надсилаємо код на email
        EmailService.sendVerificationCode(email);
        switchToCodeFxml(event);
    }
}
