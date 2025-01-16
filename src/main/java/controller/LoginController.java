package controller;

import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.Curators;
import hibernate.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;
import start.zine.HelloApplication;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends HelloApplication implements Initializable {
    @FXML
    private Label magazineCurator;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    private final String hql = "FROM User WHERE Email = :email and Password = :password";
    private final LoginController loginController = this;

    private Dialog<Boolean> dialog;

    public static String curatorEmail;
    public static String curatorGroupName;

    private void loadAndShowLoginWarning(String linkFxml){
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
                loginController.clearPassword();
            });
            Stage dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();
            dialogStage.getIcons().add(new Image(getClass().getResource("/icon/info-icon.png").toExternalForm())); // Замініть "icon.png" на шлях до вашого файл
            dialog.setTitle("Помилка!");
            dialog.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void login(ActionEvent event) {
        int idCurator;
        try  {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            User user = session.createQuery(hql, User.class)
                    .setParameter("email", emailField.getText())
                    .setParameter("password", passwordField.getText())
                    .getSingleResultOrNull();

            if (user != null) {
                if (user.isStatus() == true) {
                    curatorEmail = emailField.getText();
                    idCurator = user.getCurators().getId();
                    System.out.println(idCurator);

                    Curators curators = session.get(Curators.class, idCurator);
                    if(curators != null){
                        curatorGroupName = curators.getGroup();
                    }else{
                        throw new IllegalArgumentException("куратор за таким id не знайдено");
                    }

                    switchScene((Node) event.getSource(), "/fxml/WorkGroupPage.fxml");
                    System.out.print(curatorGroupName);
                } else if(user.isStatus() == false){
                    switchScene((Node) event.getSource(), "/fxml/admin/AdminMain.fxml");
                }
            } else {
                loadAndShowLoginWarning("/fxml/WarningLoginFxml.fxml");
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void clearPassword(){
        passwordField.clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
