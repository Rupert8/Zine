package controller.admin.adminDialog;

import data.DeleteData;
import data.SearchStudentData;
import hibernate.entity.SpCategoryName;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.exception.ConstraintViolationException;
import services.TableService;
import data.AddData;
import data.DisplayDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import start.zine.HelloApplication;
import tableView.SocialPassportCategoryPrototype;
import tableView.SocialPassportPrototype;

import java.net.URL;
import java.util.ResourceBundle;

import static controller.admin.AdminSocialPassportController.saveDialog;

public class AddSocialCategoryDialogPane extends HelloApplication implements Initializable {
    @FXML
    private TableView<SocialPassportCategoryPrototype> SocialPassportCategory;

    @FXML
    private TableColumn<SocialPassportCategoryPrototype, String> NameCategory;

    @FXML
    private TextField CategoryNameTextField;

    @FXML
    private Button DeleteCategoryButton;

    public ObservableList<SocialPassportCategoryPrototype> category = FXCollections.observableArrayList();
    public static int categoryId;

    public void close(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

    private void addCategoryInfo(){
        if(isEmpty()){
            if(!isExist()){
                String nameCategory = CategoryNameTextField.getText();
                AddData.addSocialPassportCategoryInfo(nameCategory);
            }else{
                loadAndShowLoginAlarm("/fxml/notifications/WarningExistSocialPassportCategory.fxml");
            }
        }else{
            loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
        }
    }

    public void selectTableRows(){
        SocialPassportCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                SocialPassportCategoryPrototype socialPassportCategoryPrototype = newValue;
                categoryId = SearchStudentData.getIdCategorySocialPassport(socialPassportCategoryPrototype.getNameCategory());
                DeleteCategoryButton.setVisible(true);

            }
        });
    }

    public void deleteCategoryInfo() {
        try {
            DeleteData.deleteCategorySocialPassport(categoryId);
            setCategoryColumn(); // Оновлення таблиці
            DeleteCategoryButton.setVisible(false);

        } catch (ConstraintViolationException e) {
            loadAndShowLoginAlarm("/fxml/notifications/WarningExistCategory.fxml");
        }
    }

    public void setButtonAddCategory(){
        addCategoryInfo();
        setCategoryColumn();
        CategoryNameTextField.clear();
    }

    public void setCategoryTable(){
        TableService.setDataInSocialPassportCategoryTable(category,SocialPassportCategory,NameCategory);
    }

    public void setCategoryColumn(){
        getCategoryInfo();
        setCategoryTable();

    }
    public void getCategoryInfo(){
        category = DisplayDate.getSocialPassportCategoryInfo();
    }

    public boolean isEmpty(){
        return !CategoryNameTextField.getText().isEmpty();
    }

    public boolean isExist(){
        String categoryName = CategoryNameTextField.getText();
        return SearchStudentData.validateSocialPassportCategoryName(categoryName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCategoryColumn();
        selectTableRows();
    }
}
