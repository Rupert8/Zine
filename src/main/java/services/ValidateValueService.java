package services;

import data.SearchStudentData;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import static controller.login.LoginController.curatorGroupName;

public class ValidateValueService {
    public static boolean isStudentSelected(TextField name,TextField surName,TextField middleName){
        return !name.getText().isEmpty() && !surName.getText().isEmpty() && !middleName.getText().isEmpty();
    }

    public static boolean isEducationFieldEmpty(DatePicker date, TextField schoolName,TextField averageGrade){
        return date.getValue() != null && !schoolName.getText().isEmpty() && !averageGrade.getText().isEmpty();
    }

    public static boolean isExistEducation(String name,String surname,String middleName){
        return SearchStudentData.validateStudentEducationData(name,surname,middleName);
    }

    public static boolean isMilitaryFieldEmpty(DatePicker startDate, DatePicker endDate,TextField unit){
        return startDate.getValue() != null && endDate.getValue() != null && !unit.getText().isEmpty();
    }

    public static boolean isExistMilitary(String name,String surname,String middleName){
        return SearchStudentData.validateStudentMilitary(name,surname,middleName);
    }

    public static boolean isStudentJobFieldEmpty(DatePicker startDate,TextField place,TextField position){
        return startDate.getValue() != null && !place.getText().isEmpty() && !position.getText().isEmpty();
    }

    public static boolean isStudentParentsFieldEmpty(TextField pipFather,TextField pipMother,TextField phoneFather, TextField phoneMother){
        return !pipFather.getText().isEmpty() && !pipMother.getText().isEmpty() && !phoneFather.getText().isEmpty() && !phoneMother.getText().isEmpty();
    }

    public static boolean isExistStudentParents(String name,String surname,String middleName){
        return SearchStudentData.validateStudentParents(name,surname,middleName);
    }

    public static boolean isSocialActivityFieldEmpty(ComboBox<Integer> semester,DatePicker date,TextField NameActivity){
        return semester.getValue() != null && date.getValue() != null && !NameActivity.getText().isEmpty();
    }

    public static boolean isGroupActivityFieldEmpty(ComboBox<Integer> semester,TextField groupName){
        return semester.getValue() != null && !groupName.getText().isEmpty();
    }

    public static boolean isIndividualSupportFieldEmpty(ComboBox<Integer> semester,DatePicker date,TextField content){
        return semester.getValue() != null && date.getValue() != null && !content.getText().isEmpty();
    }

    public static boolean isPromotionFieldEmpty(ComboBox<Integer> semester,DatePicker date,TextField content){
        return semester.getValue() != null && date.getValue() != null && !content.getText().isEmpty();
    }

    public static boolean isSocialPassportFieldEmpty(DatePicker startDate,ComboBox<Integer> semester,ComboBox<String> socialCategory){
        return startDate.getValue() != null && semester.getValue() != null && socialCategory.getValue() != null;
    }

    public static boolean isInvalidSocialPassportFieldEmpty(DatePicker startDate,ComboBox<Integer> semester,ComboBox<String> socialCategory){
        return startDate.getValue() != null && semester.getValue() != null && socialCategory.getValue() != null;
    }

    public static boolean isInvalidSocialPassportExist(String name,String surname,String middleName){
        return SearchStudentData.validateInvalid(name,surname,middleName,curatorGroupName);
    }

    public static boolean isManyChildrenFieldEmpty(DatePicker startDate,ComboBox<Integer> semester,TextField countChildren,TextField lessThen18,TextField moreThen18){
        return startDate.getValue() != null && semester.getValue() != null && !countChildren.getText().isEmpty() && !lessThen18.getText().isEmpty() && !moreThen18.getText().isEmpty();
    }

    public static boolean isManyChildrenExist(String name,String surname,String middleName){
        return SearchStudentData.validateManyChildrenFamily(name,surname,middleName,curatorGroupName);
    }
}
