package controller.admin.adminDialog;

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
import tableView.SocialPassportCategoryPrototype;

import java.net.URL;
import java.util.ResourceBundle;

import static controller.admin.AdminSocialPassportController.saveDialog;

public class AddSocialCategoryDialogPane implements Initializable {
    @FXML
    private TableView<SocialPassportCategoryPrototype> SocialPassportCategory;

    @FXML
    private TableColumn<SocialPassportCategoryPrototype, String> NameCategory;

    @FXML
    private TextField CategoryNameTextField;

    public ObservableList<SocialPassportCategoryPrototype> category = FXCollections.observableArrayList();


    public void close(){
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }

    private void addCategoryInfo(){
        String nameCategory = CategoryNameTextField.getText();
        AddData.addSocialPassportCategoryInfo(nameCategory);

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCategoryColumn();
    }
}
