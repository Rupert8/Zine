package controller;

import controller.admin.AdminMainController;
import data.DisplayDate;
import data.UpdateData;
import hibernate.entity.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.hibernate.sql.Update;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ExtendedInformationAboutStudent extends AdminMainController implements Initializable {
    @FXML
    private ToggleGroup RadioGroup;

    @FXML
    private Label FullNameStudentGeneralInfoLabel,FullNameStudentSocialLabel,FullNameStudentPromotionLabel,FullStudentNameSocialPassport,FullNameStudentSupportLabel;

    @FXML   // Інформація про освіту
    private DatePicker EndDateEducation;
    @FXML
    private TextField SchoolNameEducation,GradeAvarageEducation;
    @FXML
    private RadioButton UpdateEducationInfoRadioButton;
    @FXML
    private Button UpdateEducationInfoButton;
    public static Date endDateEducation;
    public static String schoolNameEducation;
    public static float gradeAverageEducation;

    @FXML   // Інформація про Військову службу
    private DatePicker StartDateMilitary,EndDateMilitary;
    @FXML
    private TextField UnitMilitary;
    @FXML
    private Label StartDateMilitaryLabel,EndDateMilitaryLabel,UnitMilitaryLabel;
    @FXML
    private RadioButton UpdateMilitaryInfoRadioButton;
    public static Date startDateMilitary;
    public static Date endDateMilitary;
    public static String unitMilitary;

    @FXML   //Інформація про роботу
    private DatePicker StartDateJob,EndDateJob;
    @FXML
    private TextField PlaceJob,PositionJob;
    @FXML
    private Label StartDateJobLabel,EndDateJobLabel,PlaceJobLabel,PositionJobLabel;
    @FXML
    private ComboBox<String> ChooseJobComboBox;
    @FXML
    private RadioButton UpdateJobInfoRadioButton;
    public static Date startDateJob;
    public static Date endDateJob;
    public static String placeJob;
    public static String positionJob;

    @FXML   //Інформація про батьків студента
    private TextField PIPFatherParents,PIPMotherParents;
    @FXML
    private TextField PhoneFatherParents,PhoneMotherParents;
    @FXML
    private RadioButton UpdateParentsInfoRadioButton;
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
    @FXML
    private Label SemesterSocialActivityLabel,DateSocialActivityLabel,ActivitySocialActivityLabel;
    @FXML
    private ComboBox<String> ChooseSocialActivityComboBox;
    public static int semesterSocial;
    public static Date dateSocial;
    public static String activitySocial;

    @FXML   //Інформація про гурткову діяльність
    private ComboBox<Integer> SemesterGroup;
    @FXML
    private TextField GroupName,NoteGroup;
    @FXML
    private Label SemesterGroupActivityLabel,NameGroupActivityLabel,NoteGroupActivityLabel;
    @FXML
    private ComboBox<String> ChooseGroupActivityComboBox;
    public static int semesterGroup;
    public static String groupNameGroup;
    public static String noteGroup;

    @FXML
    private DatePicker DateSupport;
    @FXML
    private ComboBox<Integer> SemesterSupport;
    @FXML
    private TextField ContentSupport;
    @FXML
    private Label SemesterSupportLabel,DateSupportLabel,ContentSupportLabel;
    @FXML
    private ComboBox<String> ChooseIndividualSupportComboBox;
    public static int semesterSupport;
    public static Date dateSupport;
    public static String contentSupport;

    @FXML
    private DatePicker DatePromotion;
    @FXML
    private ComboBox<Integer> SemesterPromotion;
    @FXML
    private TextField ContentPromotion;
    @FXML
    private Label SemesterPromotionLabel,DatePromotionLabel,ContentPromotionLabel;
    @FXML
    private ComboBox<String> ChoosePromotionComboBox;
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
    private ComboBox<String> ChooseSocialPassportComboBox;
    @FXML
    private Label StartDateSocialPassportLabel,EndDateSocialPassportLabel,SemesterSocialPassportLabel,CategorySocialPassportLabel,NoteSocialPassportLabel;
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

    //Зміна інформації про студента
    @FXML
    private Button CancelUpdateStudentInfoButton;
    @FXML
    private Button UpdateStudentInfoButton;
    @FXML
    private Button BackButton;


    private boolean statusPane = false;
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

    public void setStudentJobInfoField(){
        String place = ChooseJobComboBox.getValue();
        StudentJob studentJob = DisplayDate.selectStudentJobInfo(place);
        if(studentJob != null){
            StartDateJob.setVisible(true);
            EndDateJob.setVisible(true);
            PlaceJob.setVisible(true);
            PositionJob .setVisible(true);

            StartDateJobLabel.setVisible(true);
            EndDateJobLabel.setVisible(true);
            PlaceJobLabel.setVisible(true);
            PositionJobLabel.setVisible(true);

            ChooseJobComboBox.setVisible(false);

            StartDateJob.setValue(startDateJob.toLocalDate());
            EndDateJob.setValue(endDateJob.toLocalDate());
            PlaceJob.setText(placeJob);
            PositionJob.setText(positionJob);
        }
    }

    public void setStudentJobInfoFieldOneResult(){
        StudentJob studentJob = DisplayDate.selectStudentJobInfoOneResult();
        if(studentJob != null){
            StartDateJob.setVisible(true);
            EndDateJob.setVisible(true);
            PlaceJob.setVisible(true);
            PositionJob .setVisible(true);

            StartDateJobLabel.setVisible(true);
            EndDateJobLabel.setVisible(true);
            PlaceJobLabel.setVisible(true);
            PositionJobLabel.setVisible(true);

            ChooseJobComboBox.setVisible(false);

            StartDateJob.setValue(startDateJob.toLocalDate());
            EndDateJob.setValue(endDateJob.toLocalDate());
            PlaceJob.setText(placeJob);
            PositionJob.setText(positionJob);
        }else{
            StartDateJob.setVisible(true);
            EndDateJob.setVisible(true);
            PlaceJob.setVisible(true);
            PositionJob .setVisible(true);

            StartDateJobLabel.setVisible(true);
            EndDateJobLabel.setVisible(true);
            PlaceJobLabel.setVisible(true);
            PositionJobLabel.setVisible(true);
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

    public void setSocialActivityInfoField(){
        String activity = ChooseSocialActivityComboBox.getValue();
        SocialActivity socialActivity = DisplayDate.selectSocialActivityInfo(activity);
        if(socialActivity != null){
            SemesterSocial.setVisible(true);
            DateSocial.setVisible(true);
            ActivitySocial.setVisible(true);

            SemesterSocialActivityLabel.setVisible(true);
            DateSocialActivityLabel.setVisible(true);
            ActivitySocialActivityLabel.setVisible(true);

            ChooseSocialActivityComboBox.setVisible(false);

            SemesterSocial.setValue(semesterSocial);
            DateSocial.setValue(dateSocial.toLocalDate());
            ActivitySocial.setText(activitySocial);
        }
    }

    public void setSocialActivityInfoFieldOneResult(){
        SocialActivity socialActivity = DisplayDate.selectSocialActivityInfoOneResult();
        if(socialActivity != null){
            SemesterSocial.setVisible(true);
            DateSocial.setVisible(true);
            ActivitySocial.setVisible(true);

            SemesterSocialActivityLabel.setVisible(true);
            DateSocialActivityLabel.setVisible(true);
            ActivitySocialActivityLabel.setVisible(true);

            ChooseSocialActivityComboBox.setVisible(false);

            SemesterSocial.setValue(semesterSocial);
            DateSocial.setValue(dateSocial.toLocalDate());
            ActivitySocial.setText(activitySocial);
        }else{
            SemesterSocial.setVisible(true);
            DateSocial.setVisible(true);
            ActivitySocial.setVisible(true);

            SemesterSocialActivityLabel.setVisible(true);
            DateSocialActivityLabel.setVisible(true);
            ActivitySocialActivityLabel.setVisible(true);
        }
    }

    public void setStudentGroupInfoField(){
        String groupName = ChooseGroupActivityComboBox.getValue();
        CircleActivity circleActivity = DisplayDate.selectGroupActivityInfo(groupName);
        if(circleActivity != null){
            SemesterGroup.setVisible(true);
            GroupName.setVisible(true);
            NoteGroup.setVisible(true);

            SemesterGroupActivityLabel.setVisible(true);
            NameGroupActivityLabel.setVisible(true);
            NoteGroupActivityLabel.setVisible(true);

            ChooseGroupActivityComboBox.setVisible(false);

            SemesterGroup.setValue(semesterGroup);
            GroupName.setText(groupNameGroup);
            NoteGroup.setText(noteGroup);
        }

    }

    private void setStudentGroupInfoFieldOneResult(){
        CircleActivity circleActivity = DisplayDate.selectGroupActivityInfoOneResult();
        if(circleActivity != null){
            SemesterGroup.setVisible(true);
            GroupName.setVisible(true);
            NoteGroup.setVisible(true);

            SemesterGroupActivityLabel.setVisible(true);
            NameGroupActivityLabel.setVisible(true);
            NoteGroupActivityLabel.setVisible(true);

            ChooseGroupActivityComboBox.setVisible(false);

            SemesterGroup.setValue(semesterGroup);
            GroupName.setText(groupNameGroup);
            NoteGroup.setText(noteGroup);
        }else{
            SemesterGroup.setVisible(true);
            GroupName.setVisible(true);
            NoteGroup.setVisible(true);

            SemesterGroupActivityLabel.setVisible(true);
            NameGroupActivityLabel.setVisible(true);
            NoteGroupActivityLabel.setVisible(true);
        }

    }

    public void setStudentIndividualSupportInfoField(){
        String contentName = ChooseIndividualSupportComboBox.getValue();
        IndividualSupport support = DisplayDate.selectStudentIndividualSupportInfo(contentName);
        if(support != null){
            SemesterSupport.setVisible(true);
            DateSupport.setVisible(true);
            ContentSupport.setVisible(true);

            SemesterSupportLabel.setVisible(true);
            DateSupportLabel.setVisible(true);
            ContentSupportLabel.setVisible(true);

            ChooseIndividualSupportComboBox.setVisible(false);

            SemesterSupport.setValue(semesterSupport);
            DateSupport.setValue(dateSupport.toLocalDate());
            ContentSupport.setText(contentSupport);
        }
    }

    private void setStudentIndividualSupportInfoFieldOneResult(){
        IndividualSupport support = DisplayDate.selectStudentIndividualSupportInfoOneResult();
        if(support != null){
            SemesterSupport.setVisible(true);
            DateSupport.setVisible(true);
            ContentSupport.setVisible(true);

            SemesterSupportLabel.setVisible(true);
            DateSupportLabel.setVisible(true);
            ContentSupportLabel.setVisible(true);

            ChooseIndividualSupportComboBox.setVisible(false);

            SemesterSupport.setValue(semesterSupport);
            DateSupport.setValue(dateSupport.toLocalDate());
            ContentSupport.setText(contentSupport);
        }else{
            SemesterSupport.setVisible(true);
            DateSupport.setVisible(true);
            ContentSupport.setVisible(true);

            SemesterSupportLabel.setVisible(true);
            DateSupportLabel.setVisible(true);
            ContentSupportLabel.setVisible(true);
        }
    }

    public void setStudentPromotionInfoField(){
        String contentName = ChoosePromotionComboBox.getValue();
        Promotion promotion = DisplayDate.selectStudentPromotionInfo(contentName);
        if(promotion != null){
            SemesterPromotion.setVisible(true);
            DatePromotion.setVisible(true);
            ContentPromotion.setVisible(true);

            SemesterPromotionLabel.setVisible(true);
            DatePromotionLabel.setVisible(true);
            ContentPromotionLabel.setVisible(true);

            ChoosePromotionComboBox.setVisible(false);

            SemesterPromotion.setValue(semesterPromotion);
            DatePromotion.setValue(datePromotion.toLocalDate());
            ContentPromotion.setText(contentPromotion);
        }
    }

    private void setStudentPromotionInfoFieldOneResult(){
        Promotion promotion = DisplayDate.selectStudentPromotionInfoOneResult();
        if(promotion != null){
            SemesterPromotion.setVisible(true);
            DatePromotion.setVisible(true);
            ContentPromotion.setVisible(true);

            SemesterPromotionLabel.setVisible(true);
            DatePromotionLabel.setVisible(true);
            ContentPromotionLabel.setVisible(true);

            ChoosePromotionComboBox.setVisible(false);

            SemesterPromotion.setValue(semesterPromotion);
            DatePromotion.setValue(datePromotion.toLocalDate());
            ContentPromotion.setText(contentPromotion);
        }else{
            SemesterPromotion.setVisible(true);
            DatePromotion.setVisible(true);
            ContentPromotion.setVisible(true);

            SemesterPromotionLabel.setVisible(true);
            DatePromotionLabel.setVisible(true);
            ContentPromotionLabel.setVisible(true);
        }
    }

    public void setSocialPassportInfoField(){
        String categoryName = ChooseSocialPassportComboBox.getValue();
        SocialPassport socialPassport = DisplayDate.selectStudentSocialPassportInfo(categoryName);
        if(socialPassport != null){
            StartDateSocialPassport.setVisible(true);
            EndDateSocialPassport.setVisible(true);
            CategorySocialPassport.setVisible(true);
            SemesterSocialPassport.setVisible(true);
            NoteSocialPassport.setVisible(true);

            StartDateSocialPassportLabel.setVisible(true);
            EndDateSocialPassportLabel.setVisible(true);
            CategorySocialPassportLabel.setVisible(true);
            SemesterSocialPassportLabel.setVisible(true);
            NoteSocialPassportLabel.setVisible(true);

            ChooseSocialPassportComboBox.setVisible(false);

            StartDateSocialPassport.setValue(startDateSocialPassport.toLocalDate());
            EndDateSocialPassport.setValue(endDateSocialPassport.toLocalDate());
            CategorySocialPassport.setValue(categorySocialPassport);
            SemesterSocialPassport.setValue(semesterSocialPassport);
            NoteSocialPassport.setText(noteSocialPassport);

        }
    }

    public void setSocialPassportInfoFieldIfOneResult(){
        SocialPassport socialPassport = DisplayDate.selectStudentSocialPassportInfoIfOneResult();
        if(socialPassport != null){
            StartDateSocialPassport.setVisible(true);
            EndDateSocialPassport.setVisible(true);
            CategorySocialPassport.setVisible(true);
            SemesterSocialPassport.setVisible(true);
            NoteSocialPassport.setVisible(true);

            StartDateSocialPassportLabel.setVisible(true);
            EndDateSocialPassportLabel.setVisible(true);
            CategorySocialPassportLabel.setVisible(true);
            SemesterSocialPassportLabel.setVisible(true);
            NoteSocialPassportLabel.setVisible(true);

            ChooseSocialPassportComboBox.setVisible(false);

            StartDateSocialPassport.setValue(startDateSocialPassport.toLocalDate());
            EndDateSocialPassport.setValue(endDateSocialPassport.toLocalDate());
            CategorySocialPassport.setValue(categorySocialPassport);
            SemesterSocialPassport.setValue(semesterSocialPassport);
            NoteSocialPassport.setText(noteSocialPassport);

        }else{
            StartDateSocialPassport.setVisible(true);
            EndDateSocialPassport.setVisible(true);
            CategorySocialPassport.setVisible(true);
            SemesterSocialPassport.setVisible(true);
            NoteSocialPassport.setVisible(true);

            StartDateSocialPassportLabel.setVisible(true);
            EndDateSocialPassportLabel.setVisible(true);
            CategorySocialPassportLabel.setVisible(true);
            SemesterSocialPassportLabel.setVisible(true);
            NoteSocialPassportLabel.setVisible(true);
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

//    private void getStudentSocialPassportCategory(){
//        List<String> socialPassportList = DisplayDate.selectStudentSocialPassportCategory(studentName,studentSurname,studentMiddleName);
//        ChooseSocialPassportComboBox.getItems().addAll(socialPassportList);
//    }

    public void loadAndSetSocialPassportInfo(){
        List<SocialPassport> socialPassportList = DisplayDate.selectStudentSocialPassportInfoList();
        if(socialPassportList.size() > 1){
            ChooseSocialPassportComboBox.getItems().addAll(DisplayDate.selectStudentSocialPassportCategory(studentName,studentSurname,studentMiddleName));
        }else{
            ChooseSocialPassportComboBox.setVisible(false);
            setSocialPassportInfoFieldIfOneResult();
        }
    }

    public void loadAndSetJobInfo(){
        List<StudentJob> studentJobList = DisplayDate.selectStudentJobInfoList();
        if(studentJobList.size() > 1){
            ChooseJobComboBox.getItems().addAll(DisplayDate.selectStudentJobPlace(studentName,studentSurname,studentMiddleName));
        }else{
            ChooseJobComboBox.setVisible(false);
            setStudentJobInfoFieldOneResult();
        }
    }

    public void loadAndSetSocialActivityInfo(){
        List<SocialActivity> socialActivityList = DisplayDate.selectSocialActivityList();
        if(socialActivityList != null){
            if(socialActivityList.size() > 1){
                ChooseSocialActivityComboBox.getItems().addAll(DisplayDate.selectSocialActivity(studentName,studentSurname,studentMiddleName));
            }else{
                ChooseSocialActivityComboBox.setVisible(false);
                setSocialActivityInfoFieldOneResult();
            }
        }else{
            ChooseSocialActivityComboBox.setVisible(false);

            SemesterSocial.setVisible(true);
            DateSocial.setVisible(true);
            ActivitySocial.setVisible(true);

            SemesterSocialActivityLabel.setVisible(true);
            DateSocialActivityLabel.setVisible(true);
            ActivitySocialActivityLabel.setVisible(true);
            throw new IllegalArgumentException("немає значень");
        }

    }

    public void loadAndSetGroupActivityInfo(){
        List<CircleActivity> circleActivityList = DisplayDate.selectGroupActivityList();
        if(circleActivityList.size() > 1){
            ChooseGroupActivityComboBox.getItems().addAll(DisplayDate.selectGroupName(studentName,studentSurname,studentMiddleName));
        }else{
            ChooseGroupActivityComboBox.setVisible(false);
            setStudentGroupInfoFieldOneResult();
        }
    }

    public void loadAndSetIndividualSupportInfo(){
        List<IndividualSupport> individualSupportsList = DisplayDate.selectStudentIndividualSupportInfoList();
        if(individualSupportsList.size() > 1){
            ChooseIndividualSupportComboBox.getItems().addAll(DisplayDate.selectIndividualSupportContentName(studentName,studentSurname,studentMiddleName));
        }else{
            ChooseIndividualSupportComboBox.setVisible(false);
            setStudentIndividualSupportInfoFieldOneResult();
        }
    }

    public void loadAndSetPromotionInfo(){
        List<Promotion> promotionsList = DisplayDate.selectStudentPromotionInfoList();
        if(promotionsList.size() > 1) {
            ChoosePromotionComboBox.getItems().addAll(DisplayDate.selectPromotionContentName(studentName,studentSurname,studentMiddleName));
        }else{
            ChoosePromotionComboBox.setVisible(false);
            setStudentPromotionInfoFieldOneResult();
        }
    }

    public void updateStudentInfo(){
        CancelUpdateStudentInfoButton.setVisible(true);
        BackButton.setVisible(false);
        UpdateStudentInfoButton.setVisible(false);

        EndDateEducation.setDisable(true);
        SchoolNameEducation.setDisable(true);
        GradeAvarageEducation.setDisable(true);

        StartDateMilitary.setDisable(true);
        EndDateMilitary.setDisable(true);
        UnitMilitary.setDisable(true);

        StartDateJob.setDisable(true);
        EndDateJob.setDisable(true);
        PlaceJob.setDisable(true);
        PositionJob.setDisable(true);

        PIPFatherParents.setDisable(true);
        PIPMotherParents.setDisable(true);
        PhoneFatherParents.setDisable(true);
        PhoneMotherParents.setDisable(true);

        UpdateParentsInfoRadioButton.setVisible(true);
        UpdateEducationInfoRadioButton.setVisible(true);
        UpdateMilitaryInfoRadioButton.setVisible(true);
        UpdateJobInfoRadioButton.setVisible(true);

    }

    public void cancelUpdateStudentInfo(){
        CancelUpdateStudentInfoButton.setVisible(false);
        BackButton.setVisible(true);
        UpdateStudentInfoButton.setVisible(true);

        UpdateEducationInfoButton.setVisible(false);

        EndDateEducation.setDisable(false);
        SchoolNameEducation.setDisable(false);
        GradeAvarageEducation.setDisable(false);

        StartDateMilitary.setDisable(false);
        EndDateMilitary.setDisable(false);
        UnitMilitary.setDisable(false);

        StartDateJob.setDisable(false);
        EndDateJob.setDisable(false);
        PlaceJob.setDisable(false);
        PositionJob.setDisable(false);

        PIPFatherParents.setDisable(false);
        PIPMotherParents.setDisable(false);
        PhoneFatherParents.setDisable(false);
        PhoneMotherParents.setDisable(false);

        UpdateParentsInfoRadioButton.setVisible(false);
        UpdateEducationInfoRadioButton.setVisible(false);
        UpdateMilitaryInfoRadioButton.setVisible(false);
        UpdateJobInfoRadioButton.setVisible(false);
        UpdateParentsInfoRadioButton.setSelected(false);
        UpdateEducationInfoRadioButton.setSelected(false);
        UpdateMilitaryInfoRadioButton.setSelected(false);
        UpdateJobInfoRadioButton.setSelected(false);
    }

    @FXML
    public void handleRadioButtonAction(ActionEvent event) {

        if (UpdateEducationInfoRadioButton.isSelected()) {
            EndDateEducation.setDisable(false);
            SchoolNameEducation.setDisable(false);
            GradeAvarageEducation.setDisable(false);

            UpdateEducationInfoButton.setVisible(true);

            UpdateParentsInfoRadioButton.setDisable(true);
            UpdateMilitaryInfoRadioButton.setDisable(true);
            UpdateJobInfoRadioButton.setDisable(true);
            System.out.print("Привіт");
        } else if (UpdateMilitaryInfoRadioButton.isSelected()) {
            System.out.println("Військова служба вибрана");
        } else if (UpdateJobInfoRadioButton.isSelected()) {
            System.out.println("Трудова діяльність вибрана");
        } else if (UpdateParentsInfoRadioButton.isSelected()) {

        }
    }


    public void setNewValueEducationInfo(){
        Date endDate = Date.valueOf(EndDateEducation.getValue());
        String schoolName = SchoolNameEducation.getText();
        float grade = Float.parseFloat(GradeAvarageEducation.getText());
        UpdateData.updateStudentEducationInfo(endDate,schoolName,grade);
        cancelUpdateStudentInfo();
        //setStudentEducationInfoField();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            setStudentEducationInfoField();
            setStudentMilitaryInfoField();
            setStudentParentsInfoField();
            setInvalidPassportInfoField();
            setManyChildrenFamily();

            setStudentNameInLabel();

            loadAndSetSocialPassportInfo();
            loadAndSetJobInfo();
            loadAndSetSocialActivityInfo();
            loadAndSetGroupActivityInfo();
            loadAndSetIndividualSupportInfo();
            loadAndSetPromotionInfo();

    }

}
