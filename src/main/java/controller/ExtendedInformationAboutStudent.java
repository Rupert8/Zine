package controller;

import controller.admin.AdminMainController;
import data.DisplayDate;
import hibernate.entity.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ExtendedInformationAboutStudent extends AdminMainController implements Initializable {
    @FXML
    private Label FullNameStudentGeneralInfoLabel,FullNameStudentSocialLabel,FullNameStudentPromotionLabel,FullStudentNameSocialPassport,FullNameStudentSupportLabel;

    @FXML   // Інформація про освіту
    private DatePicker EndDateEducation;
    @FXML
    private TextField SchoolNameEducation,GradeAvarageEducation;
    public static Date endDateEducation;
    public static String schoolNameEducation;
    public static float gradeAverageEducation;

    @FXML   // Інформація про Військову службу
    private DatePicker StartDateMilitary,EndDateMilitary;
    @FXML
    private TextField UnitMilitary;
    public static Date startDateMilitary;
    public static Date endDateMilitary;
    public static String unitMilitary;

    @FXML   //Інформація про роботу
    private DatePicker StartDateJob,EndDateJob;
    @FXML
    private TextField PlaceJob,PositionJob;
    public static Date startDateJob;
    public static Date endDateJob;
    public static String placeJob;
    public static String positionJob;

    @FXML   //Інформація про батьків студента
    private TextField PIPFatherParents,PIPMotherParents;
    @FXML
    private TextField PhoneFatherParents,PhoneMotherParents;
    public static String pipFatherParent;
    public static String pipMotherParent;
    public static String phoneFatherParent;
    public static String phoneMotherParent;

    @FXML   //Інформація про громадську діяльність
    private ComboBox<Integer> SemesterSocial;
    @FXML
    private TextField ActivitySocial;
    @FXML
    private DatePicker DateSocial;
    public static int semesterSocial;
    public static Date dateSocial;
    public static String activitySocial;

    @FXML   //Інформація про гурткову діяльність
    private ComboBox<Integer> SemesterGroup;
    @FXML
    private TextField GroupName,NoteGroup;
    public static int semesterGroup;
    public static String groupNameGroup;
    public static String noteGroup;

    @FXML
    private DatePicker DateSupport;
    @FXML
    private ComboBox<Integer> SemesterSupport;
    @FXML
    private TextField ContentSupport;
    public static int semesterSupport;
    public static Date dateSupport;
    public static String contentSupport;

    @FXML
    private DatePicker DatePromotion;
    @FXML
    private ComboBox<Integer> SemesterPromotion;
    @FXML
    private TextField ContentPromotion;
    public static int semesterPromotion;
    public static Date datePromotion;
    public static String contentPromotion;

    @FXML
    private DatePicker StartDateSocialPassport,EndDateSocialPassport;
    @FXML
    private ComboBox<String> CategorySocialPassport;
    @FXML
    private ComboBox<Integer> SemesterSocialPassport;
    @FXML
    private TextField NoteSocialPassport;
    public static Date startDateSocialPassport;
    public static Date endDateSocialPassport;
    public static String categorySocialPassport;
    public static int semesterSocialPassport;
    public static String noteSocialPassport;

    @FXML
    private DatePicker StartDateInvalid,EndDateInvalid;
    @FXML
    private ComboBox<Integer> SemesterInvalid;
    @FXML
    private ComboBox<String> CategoryInvalid;
    @FXML
    private TextField NoteInvalid;
    public static Date startDateInvalid;
    public static Date endDateInvalid;
    public static String categoryInvalid;
    public static String noteInvalid;
    public static int semesterInvalid;

    @FXML
    private DatePicker StartDateFamily,EndDateFamily;
    @FXML
    private ComboBox<Integer> SemesterFamily;
    @FXML
    private TextField  CountChildrenFamily,LessThan18Family,MuchThan18Family,NoteFamily;
    public static Date startDateFamily;
    public static Date endDateFamily;
    public static int countChildrenFamily;
    public static int lessThan18Family;
    public static int muchThan18Family;
    public static String noteFamily;
    public static int semesterFamily;


    private final StudentInfo studentInfo = new StudentInfo();

    public void back(ActionEvent event) {
        switchToAdminMain(event);
    }

    private void setStudentEducationInfoField(){
        EducationInfo educationInfo = DisplayDate.selectStudentEducationInfo(studentId);
        if(educationInfo != null){
            endDateEducation = educationInfo.getEndDate();
            schoolNameEducation = educationInfo.getSchoolName();
            gradeAverageEducation = educationInfo.getGradeAvarage();
            EndDateEducation.setValue(endDateEducation.toLocalDate());
            SchoolNameEducation.setText(schoolNameEducation);
            GradeAvarageEducation.setText(Float.toString(gradeAverageEducation));
        }

    }

    private void setStudentMilitaryInfoField(){
        MilitaryService militaryService = DisplayDate.selectStudentMilitaryInfo();
        if(militaryService != null){
            startDateMilitary = militaryService.getStartDate();
            endDateMilitary = militaryService.getEndDate();
            unitMilitary = militaryService.getUnit();
            StartDateMilitary.setValue(startDateMilitary.toLocalDate());
            EndDateMilitary.setValue(endDateMilitary.toLocalDate());
            UnitMilitary.setText(unitMilitary);

        }
    }

    private void setStudentJobInfoField(){
        StudentJob studentJob = DisplayDate.selectStudentJobInfo();
        if(studentJob != null){
            startDateJob = studentJob.getStartDate();
            endDateJob = studentJob.getEndDate();
            placeJob = studentJob.getPlace();
            positionJob = studentJob.getPosition();
            StartDateJob.setValue(startDateJob.toLocalDate());
            EndDateJob.setValue(endDateJob.toLocalDate());
            PlaceJob.setText(placeJob);
            PositionJob.setText(positionJob);
        }
    }

    private void setStudentParentsInfoField(){
        StudentParents studentParents = DisplayDate.selectStudentParentsInfo();
        if(studentParents != null){
            pipFatherParent = studentParents.getFatherFullName();
            pipMotherParent = studentParents.getMotherFullName();
            phoneFatherParent = studentParents.getPhoneFather();
            phoneMotherParent = studentParents.getPhoneMother();

            PIPFatherParents.setText(pipFatherParent);
            PIPMotherParents.setText(pipMotherParent);
            PhoneFatherParents.setText(phoneFatherParent);
            PhoneMotherParents.setText(phoneMotherParent);
        }
    }

    private void setStudentSocialActivityInfoField(){
        SocialActivity socialActivity = DisplayDate.selectStudentSocialInfo();
        if(socialActivity != null){

            SemesterSocial.setValue(semesterSocial);
            DateSocial.setValue(dateSocial.toLocalDate());
            ActivitySocial.setText(activitySocial);
        }
    }

    private void setStudentGroupInfoField(){
        CircleActivity circleActivity = DisplayDate.selectStudentGroupActivityInfo();
        if(circleActivity != null){
            SemesterGroup.setValue(semesterGroup);
            GroupName.setText(groupNameGroup);
            NoteGroup.setText(noteGroup);
        }

    }

    private void setStudentIndividualSupportInfoField(){
        IndividualSupport support = DisplayDate.selectStudentIndividualSupportInfo();
        if(support != null){
            SemesterSupport.setValue(semesterSupport);
            DateSupport.setValue(dateSupport.toLocalDate());
            ContentSupport.setText(contentSupport);
        }
    }

    private void setStudentPromotionInfoField(){
        Promotion promotion = DisplayDate.selectStudentPromotionInfo();
        if(promotion != null){
            SemesterPromotion.setValue(semesterPromotion);
            DatePromotion.setValue(datePromotion.toLocalDate());
            ContentPromotion.setText(contentPromotion);
        }
    }

    private void setSocialPassportInfoField(){
        SocialPassport socialPassport = DisplayDate.selectStudentSocialPassportInfo();
        if(socialPassport != null){
            StartDateSocialPassport.setValue(startDateSocialPassport.toLocalDate());
            EndDateSocialPassport.setValue(endDateSocialPassport.toLocalDate());
            CategorySocialPassport.setValue(categorySocialPassport);
            SemesterSocialPassport.setValue(semesterSocialPassport);
            NoteSocialPassport.setText(noteSocialPassport);

        }
    }

    private void setInvalidPassportInfoField(){
        SocialPassport socialPassport = DisplayDate.selectStudentInvalidPassportInfo();
        if(socialPassport != null){
            StartDateInvalid.setValue(startDateInvalid.toLocalDate());
            EndDateInvalid.setValue(endDateInvalid.toLocalDate());
            CategoryInvalid.setValue(categoryInvalid);
            SemesterInvalid.setValue(semesterInvalid);
            NoteInvalid.setText(noteInvalid);
        }
    }

    private void setManyChildrenFamily(){
        SocialPassport socialPassport = DisplayDate.selectStudentManyChildrenFamilyInfo();
        if(socialPassport != null){
            StartDateFamily.setValue(startDateFamily.toLocalDate());
            EndDateFamily.setValue(endDateFamily.toLocalDate());
            SemesterFamily.setValue(semesterFamily);
            CountChildrenFamily.setText(String.valueOf(countChildrenFamily));
            LessThan18Family.setText(String.valueOf(lessThan18Family));
            MuchThan18Family.setText(String.valueOf(muchThan18Family));
            NoteSocialPassport.setText(noteSocialPassport);

        }
    }

    private void setStudentNameInLabel(){
        String name = StudentInfo.getFullNameOneStudent(studentSurname,studentName,studentMiddleName);
        FullNameStudentGeneralInfoLabel.setText(name);
        FullNameStudentSocialLabel.setText(name);
        FullNameStudentPromotionLabel.setText(name);
        FullStudentNameSocialPassport.setText(name);
        FullNameStudentSupportLabel.setText(name);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setStudentEducationInfoField();
        setStudentMilitaryInfoField();
        setStudentJobInfoField();
        setStudentParentsInfoField();
        setStudentSocialActivityInfoField();
        setStudentGroupInfoField();
        setStudentIndividualSupportInfoField();
        setStudentPromotionInfoField();
        setSocialPassportInfoField();
        setInvalidPassportInfoField();
        setManyChildrenFamily();

        setStudentNameInLabel();
    }

}
