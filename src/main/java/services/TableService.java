package services;

import hibernate.entity.*;
import jakarta.mail.Address;
import javafx.beans.property.SimpleStringProperty;
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

    public static void setDataInGeneralInfoTable(ObservableList<StudentInfo> generalInfo, TableView<StudentInfo> GeneralInfoTable, TableColumn<StudentInfo, Integer> id, TableColumn<StudentInfo, String> studentName, TableColumn<StudentInfo, String> studentSurname, TableColumn<StudentInfo, String> studentMiddleName, TableColumn<StudentInfo,Date> dateOfBirthday, TableColumn<StudentInfo,String> phoneNumber,TableColumn<StudentInfo,String> address)  {
        GeneralInfoTable.setItems(generalInfo);

        id.setCellValueFactory(new PropertyValueFactory<StudentInfo, Integer>("id"));
        studentName.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("name"));
        studentMiddleName.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("middleName"));
        studentSurname.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("surname"));
        dateOfBirthday.setCellValueFactory(new PropertyValueFactory<StudentInfo, Date>("date_of_birth"));
        address.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("address"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<StudentInfo, String>("phoneNumber"));
    }

    public static void setDataInEducationInfoTable(ObservableList<EducationInfo> educationInfo, TableView<EducationInfo> EducationInfoTable, TableColumn<EducationInfo, Integer> id, TableColumn<EducationInfo, String> studentName, TableColumn<EducationInfo, String> studentSurname, TableColumn<EducationInfo, String> studentMiddleName, TableColumn<EducationInfo,Date> endDate, TableColumn<EducationInfo,String>  schoolName, TableColumn<EducationInfo,Float> averageGrade)  {
        EducationInfoTable.setItems(educationInfo);

        id.setCellValueFactory(new PropertyValueFactory<EducationInfo, Integer>("id"));
        studentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getName()));
        studentMiddleName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getMiddleName()));
        studentSurname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getSurname()));
        endDate.setCellValueFactory(new PropertyValueFactory<EducationInfo, Date>("endDate"));
        schoolName.setCellValueFactory(new PropertyValueFactory<EducationInfo, String>("schoolName"));
        averageGrade.setCellValueFactory(new PropertyValueFactory<EducationInfo, Float>("gradeAvarage"));
    }

    public static void setDataInMilitaryServiceTable(ObservableList<MilitaryService> militaryService, TableView<MilitaryService> MilitaryServiceTable, TableColumn<MilitaryService, Integer> id, TableColumn<MilitaryService, String> studentName, TableColumn<MilitaryService, String> studentSurname, TableColumn<MilitaryService, String> studentMiddleName, TableColumn<MilitaryService,Date> startDate, TableColumn<MilitaryService,Date>  endDate, TableColumn<MilitaryService,String> unit)  {
        MilitaryServiceTable.setItems(militaryService);

        id.setCellValueFactory(new PropertyValueFactory<MilitaryService, Integer>("id"));
        studentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getName()));
        studentMiddleName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getMiddleName()));
        studentSurname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getSurname()));
        startDate.setCellValueFactory(new PropertyValueFactory<MilitaryService, Date>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<MilitaryService, Date>("endDate"));
        unit.setCellValueFactory(new PropertyValueFactory<MilitaryService, String>("unit"));
    }

    public static void setDataInStudentParentsTable(ObservableList<StudentParents> studentParents, TableView<StudentParents> StudentParentsTable, TableColumn<StudentParents, Integer> id, TableColumn<StudentParents, String> studentName, TableColumn<StudentParents, String> studentSurname, TableColumn<StudentParents, String> studentMiddleName, TableColumn<StudentParents,String> pipFather, TableColumn<StudentParents,String>  pipMother, TableColumn<StudentParents,String> phoneFather, TableColumn<StudentParents,String> phoneMother,TableColumn<StudentParents,String> note)  {
        StudentParentsTable.setItems(studentParents);

        id.setCellValueFactory(new PropertyValueFactory<StudentParents, Integer>("id"));
        studentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getName()));
        studentMiddleName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getMiddleName()));
        studentSurname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getSurname()));
        pipFather.setCellValueFactory(new PropertyValueFactory<StudentParents, String>("fatherFullName"));
        pipMother.setCellValueFactory(new PropertyValueFactory<StudentParents, String>("motherFullName"));
        phoneFather.setCellValueFactory(new PropertyValueFactory<StudentParents, String>("phoneFather"));
        phoneMother.setCellValueFactory(new PropertyValueFactory<StudentParents, String>("phoneMother"));
        note.setCellValueFactory(new PropertyValueFactory<StudentParents, String>("note"));
    }

    public static void setDataInStudentJobTable(ObservableList<StudentJob> studentJob, TableView<StudentJob> StudentJobTable, TableColumn<StudentJob, Integer> id, TableColumn<StudentJob, String> studentName, TableColumn<StudentJob, String> studentSurname, TableColumn<StudentJob, String> studentMiddleName, TableColumn<StudentJob,Date> startDate, TableColumn<StudentJob,Date>  endDate, TableColumn<StudentJob,String> place, TableColumn<StudentJob,String> position)  {
        StudentJobTable.setItems(studentJob);

        id.setCellValueFactory(new PropertyValueFactory<StudentJob, Integer>("id"));
        studentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getName()));
        studentMiddleName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getMiddleName()));
        studentSurname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getSurname()));
        startDate.setCellValueFactory(new PropertyValueFactory<StudentJob, Date>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<StudentJob, Date>("endDate"));
        place.setCellValueFactory(new PropertyValueFactory<StudentJob, String>("place"));
        position.setCellValueFactory(new PropertyValueFactory<StudentJob, String>("position"));
    }

    public static void setDataInIndividualSupportTable(ObservableList<IndividualSupport> individualSupport, TableView<IndividualSupport> IndividualSupportTable, TableColumn<IndividualSupport, Integer> id, TableColumn<IndividualSupport, String> studentName, TableColumn<IndividualSupport, String> studentSurname, TableColumn<IndividualSupport, String> studentMiddleName, TableColumn<IndividualSupport,Integer> semester, TableColumn<IndividualSupport,Date>  date, TableColumn<IndividualSupport,String> content)  {
        IndividualSupportTable.setItems(individualSupport);

        id.setCellValueFactory(new PropertyValueFactory<IndividualSupport, Integer>("id"));
        studentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getName()));
        studentMiddleName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getMiddleName()));
        studentSurname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getSurname()));
        semester.setCellValueFactory(new PropertyValueFactory<IndividualSupport, Integer>("semestr"));
        date.setCellValueFactory(new PropertyValueFactory<IndividualSupport, Date>("date"));
        content.setCellValueFactory(new PropertyValueFactory<IndividualSupport, String>("content"));
    }

    public static void setDataInPromotionTable(ObservableList<Promotion> promotion, TableView<Promotion> PromotionTable, TableColumn<Promotion, Integer> id, TableColumn<Promotion, String> studentName, TableColumn<Promotion, String> studentSurname, TableColumn<Promotion, String> studentMiddleName, TableColumn<Promotion,Integer> semester, TableColumn<Promotion,Date>  date, TableColumn<Promotion,String> content)  {
        PromotionTable.setItems(promotion);

        id.setCellValueFactory(new PropertyValueFactory<Promotion, Integer>("id"));
        studentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getName()));
        studentMiddleName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getMiddleName()));
        studentSurname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getSurname()));
        semester.setCellValueFactory(new PropertyValueFactory<Promotion, Integer>("semestr"));
        date.setCellValueFactory(new PropertyValueFactory<Promotion, Date>("startDate"));
        content.setCellValueFactory(new PropertyValueFactory<Promotion, String>("content"));
    }

    public static void setDataInSocialActivityTable(ObservableList<SocialActivity> socialActivities, TableView<SocialActivity> SocialActivityTable, TableColumn<SocialActivity, Integer> id, TableColumn<SocialActivity, String> studentName, TableColumn<SocialActivity, String> studentSurname, TableColumn<SocialActivity, String> studentMiddleName, TableColumn<SocialActivity,Integer> semester, TableColumn<SocialActivity,Date>  date, TableColumn<SocialActivity,String> activity)  {
        SocialActivityTable.setItems(socialActivities);

        id.setCellValueFactory(new PropertyValueFactory<SocialActivity, Integer>("id"));
        studentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getName()));
        studentMiddleName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getMiddleName()));
        studentSurname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getSurname()));
        semester.setCellValueFactory(new PropertyValueFactory<SocialActivity, Integer>("semestr"));
        date.setCellValueFactory(new PropertyValueFactory<SocialActivity, Date>("date"));
        activity.setCellValueFactory(new PropertyValueFactory<SocialActivity, String>("activity"));
    }

    public static void setDataInCircleActivityTable(ObservableList<CircleActivity> groupActivities, TableView<CircleActivity> GroupActivityTable, TableColumn<CircleActivity, Integer> id, TableColumn<CircleActivity, String> studentName, TableColumn<CircleActivity, String> studentSurname, TableColumn<CircleActivity, String> studentMiddleName, TableColumn<CircleActivity,Integer> semester, TableColumn<CircleActivity,String>  circleName, TableColumn<CircleActivity,String> note)  {
        GroupActivityTable.setItems(groupActivities);

        id.setCellValueFactory(new PropertyValueFactory<CircleActivity, Integer>("id"));
        studentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getName()));
        studentMiddleName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getMiddleName()));
        studentSurname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getSurname()));
        semester.setCellValueFactory(new PropertyValueFactory<CircleActivity, Integer>("semestr"));
        circleName.setCellValueFactory(new PropertyValueFactory<CircleActivity, String>("circleName"));
        note.setCellValueFactory(new PropertyValueFactory<CircleActivity, String>("note"));
    }

    public static void setDataInCuratorSocialPassportTable(ObservableList<SocialPassport> socialPassports, TableView<SocialPassport> SocialPassportTable, TableColumn<SocialPassport, Integer> id, TableColumn<SocialPassport, String> studentName, TableColumn<SocialPassport, String> studentSurname, TableColumn<SocialPassport, String> studentMiddleName, TableColumn<SocialPassport,Integer> semester, TableColumn<SocialPassport,String>  category)  {
        SocialPassportTable.setItems(socialPassports);

        id.setCellValueFactory(new PropertyValueFactory<SocialPassport, Integer>("id"));
        studentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getName()));
        studentMiddleName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getMiddleName()));
        studentSurname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentInfo().getSurname()));
        semester.setCellValueFactory(new PropertyValueFactory<SocialPassport, Integer>("semestr"));
        category.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSpCategoryName().getCategory()));
    }
}
