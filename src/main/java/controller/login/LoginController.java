package controller.login;

import interfaces.WindowActions.WindowControl;
import data.AddData;
import enums.UserStatus;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.hibernate.Session;

import services.ScreenService;
import start.zine.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends HelloApplication implements Initializable, WindowControl {
    @FXML
    private Label magazineCurator;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button minimizeWindowButton;
    @FXML
    private Button closeWindowButton;
    @FXML
    private Button maximizeWindowButton;
    @FXML
    private BorderPane borderPane;
    @FXML
    private HBox Hbox;

    private Session session;

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
            session = HibernateUtil.getSession();
            if(session != null){
                session.beginTransaction();

                User user = session.createQuery("FROM User WHERE Email = :email and Password = :password", User.class)
                        .setParameter("email", emailField.getText())
                        .setParameter("password", passwordField.getText())
                        .getSingleResultOrNull();

                if (user != null) {
                    if (UserStatus.USER == user.getStatus()) {
                        curatorEmail = emailField.getText();
                        idCurator = user.getCurators().getId();
                        System.out.println(idCurator);

                        Curators curators = session.get(Curators.class, idCurator);
                        if(curators != null){
                            curatorGroupName = curators.getGroup();
                        }else{
                            throw new IllegalArgumentException("куратор за таким id не знайдено");
                        }

                        switchScene((Node) event.getSource(), "/fxml/curator/WorkGroupPane.fxml");
                        System.out.print(curatorGroupName);
                    } else if(UserStatus.ADMIN == user.getStatus()){
                        AddData.insertCategoriesIfNotExist();
                        switchScene((Node) event.getSource(), "/fxml/admin/AdminMain.fxml");
                    }
                } else {
                    loadAndShowLoginWarning("/fxml/notifications/WarningLoginFxml.fxml");
                }

                session.getTransaction().commit();
            } else{
                throw new IllegalArgumentException("Сесія null");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("немає інтернету");
        }finally {
            HibernateUtil.closeSession(session);
        }
    }

    public void clearPassword(){
        passwordField.clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setMinimizeWindowButton(){
        ScreenService.minimized_Window(minimizeWindowButton);
    }

    @Override
    public void setMaximizeWindowButton(){
        ScreenService.maximized_Window(maximizeWindowButton.getScene().getWindow());
    }

    @Override
    public void setCloseWindow(){
        ScreenService.close_Window();
    }

    @Override
    public void setDragWindow(MouseEvent dragEvent){
        ScreenService.paneDragged(dragEvent,Hbox);
    }

    @Override
    public void setPressedWindow(MouseEvent mouseEvent) {
        ScreenService.panePressed(mouseEvent);
    }

}
