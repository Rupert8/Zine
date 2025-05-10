package controller.exportWindow;

import data.DisplayDate;
import hibernate.entity.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import start.zine.StartApplication;

import java.util.List;

import static controller.admin.AdminSocialPassportController.saveDialog;
import static services.email.EmailSender.exportAndSendSocialPassportExcelByEmail;
import static services.exportExel.ExelExportService.exportSocialPassportOnDisk;

public class ChooseExportMethodSocialPassportController extends StartApplication {
    @FXML
    private RadioButton SaveDiskRadioButton,SendEmailRadioButton;
    @FXML
    private Pane AdminEmailPane;
    @FXML
    private Pane ChooseExportMethodPane;
    @FXML
    private TextField AdminEmail;
    @FXML
    private Button applyButtonForEmail,applyButton;

    private List<SocialPassport> socialPassports;

    public void chooseExportMethod() {
        if(SendEmailRadioButton.isSelected()) {
            hideChooseExportMethod();
        }else if(SaveDiskRadioButton.isSelected()) {
            try {
                getDataForExport();
                exportSocialPassportOnDisk(socialPassports);
                loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessExportNotification.fxml");
                closeDialog();
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void hideChooseExportMethod() {
        applyButton.setVisible(false);
        ChooseExportMethodPane.setVisible(false);
        AdminEmailPane.setVisible(true);
        applyButtonForEmail.setVisible(true);
    }

    public void sentExelOnEmail(){
        try{
            getDataForExport();
            exportAndSendSocialPassportExcelByEmail(AdminEmail.getText(),socialPassports);
            loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessEmailExportNotification.fxml");
            closeDialog();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getDataForExport(){
        socialPassports = DisplayDate.selectStudentSocialPassportInfoForExport();
    }

    public void closeDialog() {
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }
}
