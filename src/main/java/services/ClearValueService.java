package services;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.util.Date;

public class ClearValueService {
    public static void clearSortByDateField(DatePicker startDate, DatePicker endDate) {
        startDate.setValue(null);
        endDate.setValue(null);
    }

    public static void clearSortBySemesterField(ComboBox<?> comboBox){
        comboBox.setValue(null);
    }

    public static void clearSocialPassportField(DatePicker StartDateSocialPassport, DatePicker EndDateSocialPassport, ComboBox<Integer> SemesterSocialPassport, ComboBox<String> CategorySocialPassport, TextField NoteSocialPassport, RadioButton AdultStudentStatusRadioButton) {
        StartDateSocialPassport.setValue(null);
        EndDateSocialPassport.setValue(null);

        SemesterSocialPassport.setValue(null);
        SemesterSocialPassport.setPromptText("Виберіть семестер");
        CategorySocialPassport.setValue(null);
        CategorySocialPassport.setPromptText("Виберіть категорію");

        NoteSocialPassport.clear();

        AdultStudentStatusRadioButton.setSelected(false);
    }

    public static void clearEducationInfo(DatePicker EndDateEducation,TextField SchoolNameEducation,TextField GradeAvarageEducation) {
        EndDateEducation.setValue(null);
        SchoolNameEducation.clear();
        GradeAvarageEducation.clear();
    }

    public static void clearMilitaryInfo(DatePicker StartDate,DatePicker EndDate,TextField Unit){
        StartDate.setValue(null);
        EndDate.setValue(null);
        Unit.clear();
    }

    public static void clearJobInfo(DatePicker StartDate,DatePicker EndDate,TextField Place,TextField Position){
        StartDate.setValue(null);
        EndDate.setValue(null);
        Place.clear();
        Position.clear();
    }

    public static void clearFamilyInfo(TextField PIPFather,TextField PIPMother,TextField PhoneFather,TextField PhoneMother){
        PIPFather.clear();
        PIPMother.clear();
        PhoneFather.clear();
        PhoneMother.clear();
    }

    public static void clearSocialActivityInfo(ComboBox<Integer> SemesterComboBox,DatePicker Date,TextField Activity){
        SemesterComboBox.setValue(null);
        Date.setValue(null);
        Activity.clear();
    }

    public static void clearGroupActivity(ComboBox<Integer> SemesterComboBox,TextField GroupName){
        SemesterComboBox.setValue(null);
        GroupName.clear();
    }

    public static void clearIndividualSupport(ComboBox<Integer> SemesterComboBox,DatePicker Date,TextField Content){
        SemesterComboBox.setValue(null);
        Date.setValue(null);
        Content.clear();
    }

    public static void clearPromotionInfo(ComboBox<Integer> SemesterComboBox,DatePicker Date,TextField Content){
        clearIndividualSupport(SemesterComboBox,Date,Content);
    }
}
