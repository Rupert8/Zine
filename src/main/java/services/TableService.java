package services;

import hibernate.entity.SocialPassport;
import hibernate.entity.SpCategoryName;
import hibernate.entity.StudentInfo;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tableView.SocialPassportCategoryPrototype;
import tableView.SocialPassportPrototype;

import java.sql.Date;

public class TableService {

    public static void setDataInSocialPassportTable(ObservableList<SocialPassportPrototype> studentInfo, TableView<SocialPassportPrototype> SocialPassportTable, TableColumn<SocialPassportPrototype, Integer> number, TableColumn<SocialPassportPrototype, String> studentName, TableColumn<SocialPassportPrototype, String> studentSurname, TableColumn<SocialPassportPrototype, String> studentMiddleName, TableColumn<SocialPassportPrototype,Integer> semester, TableColumn<SocialPassportPrototype,String> categoryName,TableColumn<SocialPassportPrototype,String> groupName)  {
        SocialPassportTable.setItems(studentInfo);

        number.setCellValueFactory(new PropertyValueFactory<SocialPassportPrototype, Integer>("id"));
        studentName.setCellValueFactory(new PropertyValueFactory<SocialPassportPrototype, String>("name"));
        studentMiddleName.setCellValueFactory(new PropertyValueFactory<SocialPassportPrototype, String>("middleName"));
        studentSurname.setCellValueFactory(new PropertyValueFactory<SocialPassportPrototype, String>("surname"));
        groupName.setCellValueFactory(new PropertyValueFactory<SocialPassportPrototype, String>("groupName"));
        semester.setCellValueFactory(new PropertyValueFactory<SocialPassportPrototype, Integer>("semester"));
        categoryName.setCellValueFactory(new PropertyValueFactory<SocialPassportPrototype, String>("category"));
    }

    public static void setDataInSocialPassportCategoryTable(ObservableList<SocialPassportCategoryPrototype> CategoryName,TableView<SocialPassportCategoryPrototype> SocialPassportCategoryTable, TableColumn<SocialPassportCategoryPrototype, String> nameCategory) {
        SocialPassportCategoryTable.setItems(CategoryName);

        nameCategory.setCellValueFactory(new PropertyValueFactory<SocialPassportCategoryPrototype, String>("nameCategory"));

        nameCategory.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER);
                }
            }
        });
    }


}
