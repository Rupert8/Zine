package controller.login;

import interfaces.WindowActions.LoadablePane;
import interfaces.WindowActions.WindowControl;
import data.AddData;
import enums.UserStatus;
import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.Curators;
import hibernate.entity.User;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.hibernate.Session;

import services.ScreenService;
import start.zine.StartApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class LoginController extends StartApplication implements Initializable, WindowControl, LoadablePane {
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
    private HBox Hbox;

    @FXML
    private StackPane LoadLoginstackPane;
    @FXML
    private BorderPane borderPane;

    private Session session;

    private final LoginController loginController = this;

    private Dialog<Boolean> dialog;

    private Preferences login = Preferences.userRoot().node("Login");

    public static String curatorEmail;
    public static String curatorGroupName;

    private static boolean currentLoginControllerInitialized = false;

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
        loadPane();
    }

    public void processLogin(){
        int idCurator;
        try {
            session = HibernateUtil.getSession();
            if (session != null) {
                session.beginTransaction();

                User user = session.createQuery("FROM User WHERE Email = :email and Password = :password", User.class)
                        .setParameter("email", emailField.getText().trim())
                        .setParameter("password", passwordField.getText().trim())
                        .getSingleResultOrNull();

                if (user != null) {
                    if (UserStatus.USER == user.getStatus()) {
                        curatorEmail = emailField.getText();
                        idCurator = user.getCurators().getId();

                        login.put("Login", curatorEmail);
                        Curators curators = session.get(Curators.class, idCurator);
                        if (curators != null) {
                            curatorGroupName = curators.getGroup();
                        } else {
                            throw new IllegalArgumentException("Куратор за таким id не знайдено");
                        }

                        switchScene(borderPane, "/fxml/curator/WorkGroupPane.fxml");
                    } else if (UserStatus.ADMIN == user.getStatus()) {
                        AddData.insertCategoriesIfNotExist();
                        switchScene(borderPane, "/fxml/admin/AdminMain.fxml");
                    }
                } else {
                    throw new IllegalArgumentException("Невірний email або пароль");
                }

                session.getTransaction().commit();
            } else {
                throw new IllegalStateException("Сесія Hibernate повернула null");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        } finally {
            HibernateUtil.closeSession(session);
        }
    }


    public void clearPassword(){
        passwordField.clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String saveLogin = login.get("Login", "");

        if(currentLoginControllerInitialized){
            return;
        }

        currentLoginControllerInitialized = true;

        emailField.setText(saveLogin);
        currentLoginControllerInitialized = true;

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

    @Override
    public void loadPane() {
            LoadLoginstackPane.setVisible(true);
            borderPane.setDisable(true);

//            if(!HibernateUtil.isSessionConnected()){
//                LoadLoginstackPane.setVisible(false);
//                borderPane.setDisable(false);
//                loadAndShowLoginWarning("/fxml/notifications/warningNotifications/LostInternetConnection.fxml");
//                return;
//            }

                Task<Void> loadDataTask = new Task<>() {
                    @Override
                    protected Void call(){
                        processLogin();
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        LoadLoginstackPane.setVisible(false);
                        borderPane.setDisable(false);
                    }

                    @Override
                    protected void failed() {
                        LoadLoginstackPane.setVisible(false);
                        borderPane.setDisable(false);

                        Throwable error = getException();
                        if (error.getCause() instanceof IllegalArgumentException) {
                            loadAndShowLoginWarning("/fxml/notifications/warningNotifications/WarningLoginFxml.fxml");
                        } else {
                            loadAndShowLoginWarning("/fxml/notifications/warningNotifications/LostInternetConnection.fxml");
                        }
                    }
                };

                new Thread(loadDataTask).start();

        }
}
