package controller.extendedInformationAboutStudent;

import controller.admin.AdminMainController;
import data.DisplayDate;
import data.UpdateData;
import hibernate.entity.*;
import interfaces.WindowActions.WindowControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import services.ScreenService;
import services.ValidateValueService;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ExtendedInformationAboutStudent extends AdminMainController implements Initializable {
    @FXML
    private Tab GeneralInfoTab,SocialAndGroupActivityTab,IndividualSupportTab,PromotionTab,SocialPassportTab;

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
    @FXML
    private Button UpdateMilitaryInfoButton;
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
    @FXML
    private Button UpdateJobInfoButton;
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
    @FXML
    private Button UpdateParentsInfoButton;
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
    @FXML
    private RadioButton UpdateSocialActivityRadioButton;
    @FXML
    private Button UpdateSocialActivityButton;
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
    @FXML
    private RadioButton UpdateGroupActivityRadioButton;
    @FXML
    private Button UpdateGroupActivityButton;
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
    @FXML
    private Button UpdateIndividualSupportButtonInDB;
    public static int semesterSupport;
    public static Date dateSupport;
    public static String contentSupport;
    public static String tmpContentSupport;

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
    @FXML
    private Button UpdatePromotionButtonInDB;
    public static int semesterPromotion;
    public static Date datePromotion;
    public static String contentPromotion;
    public static String tmpContentPromotion;

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
    @FXML
    private RadioButton UpdateSocialPassportRadioButton;
    @FXML
    private Button UpdateSocialPassportButton;
    public static Date startDateSocialPassport;
    public static Date endDateSocialPassport;
    public static String categorySocialPassport;
    public static int semesterSocialPassport;
    public static String noteSocialPassport;
    public static String tmpCategorySocialPassport;

    @FXML
    private DatePicker StartDateInvalid,EndDateInvalid;
    @FXML
    private ComboBox<Integer> SemesterInvalid;
    @FXML
    private ComboBox<String> CategoryInvalid;
    @FXML
    private TextField NoteInvalid;
    @FXML
    private RadioButton UpdateInvalidRadioButton;
    @FXML
    private Button UpdateInvalidPassportButton;
    public static Date startDateInvalid;
    public static Date endDateInvalid;
    public static String categoryInvalid;
    public static String noteInvalid;
    public static int semesterInvalid;
    public static String tmpCategoryInvalid = "Інвалід";

    @FXML
    private DatePicker StartDateFamily,EndDateFamily;
    @FXML
    private ComboBox<Integer> SemesterFamily;
    @FXML
    private TextField  CountChildrenFamily,LessThan18Family,MuchThan18Family,NoteFamily;
    @FXML
    private RadioButton UpdateFamilyRadioButton;
    @FXML
    private Button UpdateManyChildrenFamilyPassportButton;
    public static Date startDateFamily;
    public static Date endDateFamily;
    public static int countChildrenFamily;
    public static int lessThan18Family;
    public static int muchThan18Family;
    public static String noteFamily;
    public static int semesterFamily;
    public static String tmpManyChildrenFamily = "Багатодітна родина";

    //Зміна інформації про студента
    @FXML
    private Button CancelUpdateStudentInfoButton;
    @FXML
    private Button UpdateStudentInfoButton;
    @FXML
    private Button BackButton;
    @FXML
    private Button CancelUpdateSocialAndGroupActivityButton;
    @FXML
    private Button UpdateSocialAndGroupActivityButton;
    @FXML
    private Button BackSocialActivityButton;
    @FXML
    private Button CancelUpdateIndividualSupportButton;
    @FXML
    private Button UpdateIndividualSupportButton;
    @FXML
    private Button BackIndividualSupportButton;
    @FXML
    private Button CancelUpdatePromotionButton;
    @FXML
    private Button UpdatePromotionButton;
    @FXML
    private Button BackPromotionButton;
    @FXML
    private Button CancelUpdateSocialPassportButton;
    @FXML
    private Button UpdateAllSocialPassportButton;
    @FXML
    private Button BackAllSocialPassportButton;

    @FXML
    private HBox MenuBarHBox;
    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

    @FXML
    private StackPane loadingPane;

    @FXML
    private BorderPane WorkPlanBorderPane;

    private int semesterSocialValue;
    private int semesterGroupValue;
    private int semesterSupportValue;
    private int semesterInvalidValue;
    private int semesterFamilyValue;
    private int semesterSocialPassportValue;

    private boolean statusPane = false;
    public static int userStatus = 2;

    public void back(ActionEvent event) {
        if(userStatus == 0){
            switchToAdminMain(event);
        }else{
            switchWorkGroupPage(event);
        }

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
//            if(EndDateInvalid.getValue() == null){
//
//            }else{
//                EndDateInvalid.setValue(null);
//            }
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

    public void updateGeneralInfoStudentInfo(){
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

        SocialAndGroupActivityTab.setDisable(true);
        IndividualSupportTab.setDisable(true);
        PromotionTab.setDisable(true);
        SocialPassportTab.setDisable(true);

    }

    public void updateSocialAndGroupActivityStudentInfo(){
        CancelUpdateSocialAndGroupActivityButton.setVisible(true);
        BackSocialActivityButton.setVisible(false);
        UpdateSocialAndGroupActivityButton.setVisible(false);

        SemesterSocial.setDisable(true);
        ActivitySocial.setDisable(true);
        DateSocial.setDisable(true);

        SemesterGroup.setDisable(true);
        GroupName.setDisable(true);
        NoteGroup.setDisable(true);

        UpdateSocialActivityRadioButton.setVisible(true);
        UpdateGroupActivityRadioButton.setVisible(true);

        GeneralInfoTab.setDisable(true);
        PromotionTab.setDisable(true);
        IndividualSupportTab.setDisable(true);
        SocialPassportTab.setDisable(true);
    }

    public void updateIndividualSupportStudentInfo(){
        if(ValidateValueService.isIndividualSupportFieldEmpty(SemesterSupport,DateSupport,ContentSupport)){
            tmpContentSupport = ContentSupport.getText();
            CancelUpdateIndividualSupportButton.setVisible(true);
            BackIndividualSupportButton.setVisible(false);
            UpdateIndividualSupportButton.setVisible(false);
            UpdateIndividualSupportButtonInDB.setVisible(true);
            setSemesterComboBox(SemesterSupport);

            ContentSupport.setEditable(true);

            GeneralInfoTab.setDisable(true);
            SocialAndGroupActivityTab.setDisable(true);
            PromotionTab.setDisable(true);
            SocialPassportTab.setDisable(true);
        }else{
            loadAndShowLoginAlarm("/fxml/notifications/WarningUpdateStudentInfo.fxml");
            cancelUpdateIndividualSupportButton();
        }

    }

    public void updatePromotionStudentInfo(){
        if(ValidateValueService.isPromotionFieldEmpty(SemesterPromotion,DatePromotion,ContentPromotion)){
            tmpContentPromotion = ContentPromotion.getText();
            CancelUpdatePromotionButton.setVisible(true);
            BackPromotionButton.setVisible(false);
            UpdatePromotionButton.setVisible(false);
            UpdatePromotionButtonInDB.setVisible(true);
            setSemesterComboBox(SemesterPromotion);

            ContentPromotion.setEditable(true);

            GeneralInfoTab.setDisable(true);
            SocialAndGroupActivityTab.setDisable(true);
            IndividualSupportTab.setDisable(true);
            SocialPassportTab.setDisable(true);
        }else{
            loadAndShowLoginAlarm("/fxml/notifications/WarningUpdateStudentInfo.fxml");
            cancelUpdatePromotionInfo();
        }

    }

    public void updateAllSocialPassportStudentInfo(){
        String categoryName = CategorySocialPassport.getValue();

        CategorySocialPassport.getItems().clear();
        CategorySocialPassport.getItems().add(categoryName);
        CategorySocialPassport.setValue(categoryName);

        CancelUpdateSocialPassportButton.setVisible(true);
        BackAllSocialPassportButton.setVisible(false);
        UpdateAllSocialPassportButton.setVisible(false);

        StartDateSocialPassport.setDisable(true);
        EndDateSocialPassport.setDisable(true);
        SemesterSocialPassport.setDisable(true);
        CategorySocialPassport.setDisable(true);
        NoteSocialPassport.setDisable(true);

        StartDateInvalid.setDisable(true);
        EndDateInvalid.setDisable(true);
        SemesterInvalid.setDisable(true);
        CategoryInvalid.setDisable(true);
        NoteInvalid.setDisable(true);

        StartDateFamily.setDisable(true);
        EndDateFamily.setDisable(true);
        SemesterFamily.setDisable(true);
        CountChildrenFamily.setDisable(true);
        LessThan18Family.setDisable(true);
        MuchThan18Family.setDisable(true);
        NoteFamily.setDisable(true);

        UpdateSocialPassportRadioButton.setVisible(true);
        UpdateInvalidRadioButton.setVisible(true);
        UpdateFamilyRadioButton.setVisible(true);

        GeneralInfoTab.setDisable(true);
        SocialAndGroupActivityTab.setDisable(true);
        IndividualSupportTab.setDisable(true);
        PromotionTab.setDisable(true);
    }

    public void cancelUpdatePromotionInfo(){
        CancelUpdatePromotionButton.setVisible(false);
        BackPromotionButton.setVisible(true);
        UpdatePromotionButton.setVisible(true);
        UpdatePromotionButtonInDB.setVisible(false);

        ContentPromotion.setEditable(false);

        GeneralInfoTab.setDisable(false);
        SocialAndGroupActivityTab.setDisable(false);
        IndividualSupportTab.setDisable(false);
        SocialPassportTab.setDisable(false);
    }

    public void cancelUpdateIndividualSupportButton(){
        if(SemesterSupport.getValue() != null){
            semesterSupportValue = SemesterSupport.getValue();
        }

        CancelUpdateIndividualSupportButton.setVisible(false);
        BackIndividualSupportButton.setVisible(true);
        UpdateIndividualSupportButton.setVisible(true);
        UpdateIndividualSupportButtonInDB.setVisible(false);

        ContentSupport.setEditable(true);

        SemesterSupport.getItems().clear();
        SemesterSupport.getItems().add(semesterSupportValue);
        SemesterSupport.setValue(semesterSupportValue);

        GeneralInfoTab.setDisable(false);
        SocialAndGroupActivityTab.setDisable(false);
        PromotionTab.setDisable(false);
        SocialPassportTab.setDisable(false);
    }

    public void cancelUpdateSocialAndGroupActivityStudentInfo() {
        if(SemesterSocial.getValue() != null){
            semesterSocialValue = SemesterSocial.getValue();
        }
        if(SemesterGroup.getValue() != null){
            semesterGroupValue = SemesterGroup.getValue();
        }
        CancelUpdateSocialAndGroupActivityButton.setVisible(false);
        BackSocialActivityButton.setVisible(true);
        UpdateSocialAndGroupActivityButton.setVisible(true);

        SemesterSocial.getItems().clear();
        SemesterSocial.getItems().add(semesterSocialValue);
        SemesterSocial.setValue(semesterSocialValue);

        SemesterGroup.getItems().clear();
        SemesterGroup.getItems().add(semesterGroupValue);
        SemesterGroup.setValue(semesterGroupValue);

        UpdateSocialActivityButton.setVisible(false);
        UpdateGroupActivityButton.setVisible(false);

        SemesterSocial.setDisable(false);
        ActivitySocial.setDisable(false);
        DateSocial.setDisable(false);

        SemesterSocial.setEditable(false);
        ActivitySocial.setEditable(false);
        DateSocial.setEditable(false);

        SemesterGroup.setDisable(false);
        GroupName.setDisable(false);
        NoteGroup.setDisable(false);

        SemesterGroup.setEditable(false);
        GroupName.setEditable(false);
        NoteGroup.setEditable(false);

        UpdateSocialActivityRadioButton.setVisible(false);
        UpdateGroupActivityRadioButton.setVisible(false);

        UpdateSocialActivityRadioButton.setSelected(false);
        UpdateGroupActivityRadioButton.setSelected(false);

        UpdateSocialActivityRadioButton.setDisable(false);
        UpdateGroupActivityRadioButton.setDisable(false);

        GeneralInfoTab.setDisable(false);
        PromotionTab.setDisable(false);
        IndividualSupportTab.setDisable(false);
        SocialPassportTab.setDisable(false);
    }

    public void cancelUpdateStudentInfo(){
        CancelUpdateStudentInfoButton.setVisible(false);
        BackButton.setVisible(true);
        UpdateStudentInfoButton.setVisible(true);

        UpdateEducationInfoButton.setVisible(false);
        UpdateMilitaryInfoButton.setVisible(false);
        UpdateJobInfoButton.setVisible(false);
        UpdateParentsInfoButton.setVisible(false);

        EndDateEducation.setDisable(false);
        SchoolNameEducation.setDisable(false);
        GradeAvarageEducation.setDisable(false);

        EndDateEducation.setEditable(false);
        SchoolNameEducation.setEditable(false);
        GradeAvarageEducation.setEditable(false);

        StartDateMilitary.setDisable(false);
        EndDateMilitary.setDisable(false);
        UnitMilitary.setDisable(false);

        StartDateMilitary.setEditable(false);
        EndDateMilitary.setEditable(false);
        UnitMilitary.setEditable(false);

        StartDateJob.setDisable(false);
        EndDateJob.setDisable(false);
        PlaceJob.setDisable(false);
        PositionJob.setDisable(false);

        StartDateJob.setEditable(false);
        EndDateJob.setEditable(false);
        PlaceJob.setEditable(false);
        PositionJob.setEditable(false);

        PIPFatherParents.setDisable(false);
        PIPMotherParents.setDisable(false);
        PhoneFatherParents.setDisable(false);
        PhoneMotherParents.setDisable(false);

        PIPFatherParents.setEditable(false);
        PIPMotherParents.setEditable(false);
        PhoneFatherParents.setEditable(false);
        PhoneMotherParents.setEditable(false);

        UpdateParentsInfoRadioButton.setVisible(false);
        UpdateEducationInfoRadioButton.setVisible(false);
        UpdateMilitaryInfoRadioButton.setVisible(false);
        UpdateJobInfoRadioButton.setVisible(false);

        UpdateParentsInfoRadioButton.setDisable(false);
        UpdateEducationInfoRadioButton.setDisable(false);
        UpdateMilitaryInfoRadioButton.setDisable(false);
        UpdateJobInfoRadioButton.setDisable(false);

        UpdateParentsInfoRadioButton.setSelected(false);
        UpdateEducationInfoRadioButton.setSelected(false);
        UpdateMilitaryInfoRadioButton.setSelected(false);
        UpdateJobInfoRadioButton.setSelected(false);

        SocialAndGroupActivityTab.setDisable(false);
        PromotionTab.setDisable(false);
        IndividualSupportTab.setDisable(false);
        SocialPassportTab.setDisable(false);
    }

    public void cancelAllSocialPassportStudentInfo(){
        if(SemesterSocialPassport.getValue() != null){
            semesterSocialPassportValue = SemesterSocialPassport.getValue();
        }
        if(SemesterInvalid.getValue() != null){
            semesterInvalidValue = SemesterInvalid.getValue();
        }
        if(SemesterFamily.getValue() != null){
            semesterFamilyValue = SemesterFamily.getValue();
        }
        String categoryName = CategorySocialPassport.getValue();

        CancelUpdateSocialPassportButton.setVisible(false);
        BackAllSocialPassportButton.setVisible(true);
        UpdateAllSocialPassportButton.setVisible(true);

        UpdateSocialPassportButton.setVisible(false);
        UpdateInvalidPassportButton.setVisible(false);
        UpdateManyChildrenFamilyPassportButton.setVisible(false);

        SemesterSocialPassport.getItems().clear();
        SemesterSocialPassport.getItems().add(semesterSocialPassportValue);
        SemesterSocialPassport.setValue(semesterSocialPassportValue);

        CategorySocialPassport.getItems().clear();
        CategorySocialPassport.getItems().add(categoryName);
        CategorySocialPassport.setValue(categoryName);

        SemesterInvalid.getItems().clear();
        SemesterInvalid.getItems().add(semesterInvalidValue);
        SemesterInvalid.setValue(semesterInvalidValue);

        SemesterFamily.getItems().clear();
        SemesterFamily.getItems().add(semesterFamilyValue);
        SemesterFamily.setValue(semesterFamilyValue);

        StartDateSocialPassport.setDisable(false);
        EndDateSocialPassport.setDisable(false);
        SemesterSocialPassport.setDisable(false);
        CategorySocialPassport.setDisable(false);
        NoteSocialPassport.setDisable(false);

        NoteSocialPassport.setEditable(false);

        StartDateInvalid.setDisable(false);
        EndDateInvalid.setDisable(false);
        SemesterInvalid.setDisable(false);
        CategoryInvalid.setDisable(false);
        NoteInvalid.setDisable(false);

        NoteInvalid.setEditable(false);

        StartDateFamily.setDisable(false);
        EndDateFamily.setDisable(false);
        SemesterFamily.setDisable(false);
        CountChildrenFamily.setDisable(false);
        LessThan18Family.setDisable(false);
        MuchThan18Family.setDisable(false);
        NoteFamily.setDisable(false);

        CountChildrenFamily.setEditable(false);
        LessThan18Family.setEditable(false);
        MuchThan18Family.setEditable(false);
        NoteFamily.setEditable(false);

        UpdateSocialPassportRadioButton.setVisible(false);
        UpdateInvalidRadioButton.setVisible(false);
        UpdateFamilyRadioButton.setVisible(false);

        UpdateSocialPassportRadioButton.setSelected(false);
        UpdateInvalidRadioButton.setSelected(false);
        UpdateFamilyRadioButton.setSelected(false);

        UpdateSocialPassportRadioButton.setDisable(false);
        UpdateInvalidRadioButton.setDisable(false);
        UpdateFamilyRadioButton.setDisable(false);

        GeneralInfoTab.setDisable(false);
        SocialAndGroupActivityTab.setDisable(false);
        IndividualSupportTab.setDisable(false);
        PromotionTab.setDisable(false);
    }

    @FXML
    public void handleGeneralInfoRadioButtonAction() {
        if (UpdateEducationInfoRadioButton.isSelected()) {
            if(ValidateValueService.isEducationFieldEmpty(EndDateEducation,SchoolNameEducation,GradeAvarageEducation)){
                EndDateEducation.setDisable(false);
                SchoolNameEducation.setDisable(false);
                GradeAvarageEducation.setDisable(false);

                EndDateEducation.setEditable(true);
                SchoolNameEducation.setEditable(true);
                GradeAvarageEducation.setEditable(true);

                UpdateEducationInfoButton.setVisible(true);

                UpdateParentsInfoRadioButton.setDisable(true);
                UpdateMilitaryInfoRadioButton.setDisable(true);
                UpdateJobInfoRadioButton.setDisable(true);
            }else{
                loadAndShowLoginAlarm("/fxml/notifications/WarningUpdateStudentInfo.fxml");
                cancelUpdateStudentInfo();
            }
        } else if (UpdateMilitaryInfoRadioButton.isSelected()) {
            if(ValidateValueService.isMilitaryFieldEmpty(StartDateMilitary,EndDateMilitary,UnitMilitary)){
                StartDateMilitary.setDisable(false);
                EndDateMilitary.setDisable(false);
                UnitMilitary.setDisable(false);

                StartDateMilitary.setEditable(true);
                EndDateMilitary.setEditable(true);
                UnitMilitary.setEditable(true);

                UpdateMilitaryInfoButton.setVisible(true);

                UpdateParentsInfoRadioButton.setDisable(true);
                UpdateEducationInfoRadioButton.setDisable(true);
                UpdateJobInfoRadioButton.setDisable(true);
            }else{
                loadAndShowLoginAlarm("/fxml/notifications/WarningUpdateStudentInfo.fxml");
                cancelUpdateStudentInfo();
            }
        } else if (UpdateJobInfoRadioButton.isSelected()) {
            if(ValidateValueService.isStudentJobFieldEmpty(StartDateJob,PlaceJob,PositionJob)){
                StartDateJob.setDisable(false);
                EndDateJob.setDisable(false);
                PlaceJob.setDisable(false);
                PositionJob.setDisable(false);

                StartDateJob.setEditable(true);
                EndDateJob.setEditable(true);
                PlaceJob.setEditable(true);
                PositionJob.setEditable(true);

                UpdateJobInfoButton.setVisible(true);

                UpdateParentsInfoRadioButton.setDisable(true);
                UpdateEducationInfoRadioButton.setDisable(true);
                UpdateMilitaryInfoRadioButton.setDisable(true);
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningUpdateStudentInfo.fxml");
                cancelUpdateStudentInfo();
            }
        } else if (UpdateParentsInfoRadioButton.isSelected()) {
            if(ValidateValueService.isStudentParentsFieldEmpty(PIPFatherParents,PIPMotherParents,PhoneFatherParents,PhoneMotherParents)){
                PIPFatherParents.setDisable(false);
                PIPMotherParents.setDisable(false);
                PhoneFatherParents.setDisable(false);
                PhoneMotherParents.setDisable(false);

                PIPFatherParents.setEditable(true);
                PIPMotherParents.setEditable(true);
                PhoneFatherParents.setEditable(true);
                PhoneMotherParents.setEditable(true);

                UpdateParentsInfoButton.setVisible(true);

                UpdateEducationInfoRadioButton.setDisable(true);
                UpdateMilitaryInfoRadioButton.setDisable(true);
                UpdateJobInfoRadioButton.setDisable(true);
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningUpdateStudentInfo.fxml");
                cancelUpdateStudentInfo();
            }
        }
    }

    public void handleSocialAndGroupActivityInfoRadioButtonAction() {
        if (UpdateSocialActivityRadioButton.isSelected()) {
            if(ValidateValueService.isSocialActivityFieldEmpty(SemesterSocial,DateSocial,ActivitySocial)){
                SemesterSocial.setDisable(false);
                DateSocial.setDisable(false);
                ActivitySocial.setDisable(false);

                ActivitySocial.setEditable(true);

                UpdateSocialActivityButton.setVisible(true);

                setSemesterComboBox(SemesterSocial);
                UpdateGroupActivityRadioButton.setDisable(true);
            }else{
                loadAndShowLoginAlarm("/fxml/notifications/WarningUpdateStudentInfo.fxml");
                cancelUpdateSocialAndGroupActivityStudentInfo();
            }

        }else if (UpdateGroupActivityRadioButton.isSelected()) {
            if(ValidateValueService.isGroupActivityFieldEmpty(SemesterGroup,GroupName)){
                SemesterGroup.setDisable(false);
                GroupName.setDisable(false);
                NoteGroup.setDisable(false);

                GroupName.setEditable(true);
                NoteGroup.setEditable(true);

                UpdateGroupActivityButton.setVisible(true);

                setSemesterComboBox(SemesterGroup);
                UpdateSocialActivityRadioButton.setDisable(true);
            }else{
                loadAndShowLoginAlarm("/fxml/notifications/WarningUpdateStudentInfo.fxml");
                cancelUpdateSocialAndGroupActivityStudentInfo();
            }
        }
    }

    public void handleAllSocialPassportStudentInfoRadioButtonAction() {
        if (UpdateSocialPassportRadioButton.isSelected()) {
            if(ValidateValueService.isSocialPassportFieldEmpty(StartDateSocialPassport,SemesterSocialPassport,CategorySocialPassport)){
                tmpCategorySocialPassport = CategorySocialPassport.getValue();
                StartDateSocialPassport.setDisable(false);
                EndDateSocialPassport.setDisable(false);
                SemesterSocialPassport.setDisable(false);
                CategorySocialPassport.setDisable(false);
                NoteSocialPassport.setDisable(false);

                NoteSocialPassport.setEditable(true);

                setSemesterComboBox(SemesterSocialPassport);
                setSocialPassportCategoryComboBox();
                UpdateSocialPassportButton.setVisible(true);

                UpdateInvalidRadioButton.setDisable(true);
                UpdateFamilyRadioButton.setDisable(true);
            }else{
                loadAndShowLoginAlarm("/fxml/notifications/WarningUpdateStudentInfo.fxml");
                cancelAllSocialPassportStudentInfo();
            }

        }else if (UpdateInvalidRadioButton.isSelected()) {
            if(ValidateValueService.isInvalidSocialPassportFieldEmpty(StartDateInvalid,SemesterInvalid,CategoryInvalid)){
                StartDateInvalid.setDisable(false);
                EndDateInvalid.setDisable(false);
                SemesterInvalid.setDisable(false);
                CategoryInvalid.setDisable(false);
                NoteInvalid.setDisable(false);

                NoteInvalid.setEditable(true);

                setSemesterComboBox(SemesterInvalid);
                setInvalidCategoryComboBox();
                UpdateInvalidPassportButton.setVisible(true);

                UpdateSocialPassportRadioButton.setDisable(true);
                UpdateFamilyRadioButton.setDisable(true);
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningUpdateStudentInfo.fxml");
                cancelAllSocialPassportStudentInfo();
            }
        }else if (UpdateFamilyRadioButton.isSelected()) {
            if(ValidateValueService.isManyChildrenFieldEmpty(StartDateFamily,SemesterFamily,CountChildrenFamily,LessThan18Family,MuchThan18Family)){
                StartDateFamily.setDisable(false);
                EndDateFamily.setDisable(false);
                SemesterFamily.setDisable(false);
                CountChildrenFamily.setDisable(false);
                LessThan18Family.setDisable(false);
                MuchThan18Family.setDisable(false);
                NoteFamily.setDisable(false);

                CountChildrenFamily.setEditable(true);
                LessThan18Family.setEditable(true);
                MuchThan18Family.setEditable(true);
                NoteFamily.setEditable(true);

                setSemesterComboBox(SemesterFamily);
                UpdateManyChildrenFamilyPassportButton.setVisible(true);

                UpdateSocialPassportRadioButton.setDisable(true);
                UpdateInvalidRadioButton.setDisable(true);
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningUpdateStudentInfo.fxml");
                cancelAllSocialPassportStudentInfo();
            }

        }
    }

    public void setNewValueEducationInfo(){
            Date endDate = Date.valueOf(EndDateEducation.getValue());
            String schoolName = SchoolNameEducation.getText();
            float grade = Float.parseFloat(GradeAvarageEducation.getText());
            UpdateData.updateStudentEducationInfo(endDate,schoolName,grade);
            cancelUpdateStudentInfo();
    }

    public void setNewValueMilitaryInfo(){
        Date startDate = Date.valueOf(StartDateMilitary.getValue());
        Date endDate = Date.valueOf(EndDateMilitary.getValue());
        String unit = UnitMilitary.getText();
        UpdateData.updateStudentMilitaryInfo(startDate,endDate,unit);
        cancelUpdateStudentInfo();
    }

    public void setNewValueJobInfo(){
        Date startDate = Date.valueOf(StartDateJob.getValue());
        Date endDate = Date.valueOf(EndDateJob.getValue());
        String place = PlaceJob.getText();
        String position = PositionJob.getText();
        UpdateData.updateStudentJobInfo(startDate,endDate,place,position);
        cancelUpdateStudentInfo();
    }

    public void setNewValueParentsInfo(){
        String pipFather = PIPFatherParents.getText();
        String pipMother = PIPMotherParents.getText();
        String phoneFather = PhoneFatherParents.getText();
        String phoneMother = PhoneMotherParents.getText();
        UpdateData.updateStudentParentsInfo(pipFather,pipMother,phoneFather,phoneMother);
        cancelUpdateStudentInfo();
    }

    public void setNewValueSocialActivityInfo(){
        int semester = SemesterSocial.getValue();
        String activity = ActivitySocial.getText();
        Date date  = Date.valueOf(DateSocial.getValue());
        UpdateData.updateStudentSocialActivityInfo(semester,activity,date);
        cancelUpdateSocialAndGroupActivityStudentInfo();

    }

    public void setNewValueGroupActivityInfo(){
        int semester = SemesterGroup.getValue();
        String groupName = GroupName.getText();
        String note = NoteGroup.getText();
        UpdateData.updateStudentGroupActivityInfo(semester,groupName,note);
        cancelUpdateSocialAndGroupActivityStudentInfo();
    }

    public void setNewValueIndividualSupportInfo(){
        int semester = SemesterSupport.getValue();
        Date date  = Date.valueOf(DateSupport.getValue());
        String content = ContentSupport.getText();
        UpdateData.updateStudentIndividualSupportInfo(semester,date,content,tmpContentSupport);
        cancelUpdateIndividualSupportButton();
    }

    public void setNewValuePromotionInfo(){
        int semester = SemesterPromotion.getValue();
        Date date  = Date.valueOf(DatePromotion.getValue());
        String content = ContentPromotion.getText();
        UpdateData.updateStudentPromotionInfo(semester,date,content,tmpContentPromotion);
        cancelUpdatePromotionInfo();
    }

    public void setNewValueSocialPassport(){
        Date startDate = Date.valueOf(StartDateSocialPassport.getValue());
        Date endDate = null;
        if(EndDateSocialPassport.getValue() != null){
            endDate = Date.valueOf(EndDateSocialPassport.getValue());
        }
        int semester = SemesterSocialPassport.getValue();
        String category = CategorySocialPassport.getValue();
        String note = NoteSocialPassport.getText();
        UpdateData.updateStudentSocialPassportInfo(startDate,endDate,semester,category,note,tmpCategorySocialPassport);
        cancelAllSocialPassportStudentInfo();
    }

    public void setNewValueSocialInvalidPassport(){
        Date startDate = Date.valueOf(StartDateInvalid.getValue());
        Date endDate = null;
        if(EndDateInvalid.getValue() != null){
            endDate = Date.valueOf(EndDateInvalid.getValue());
        }
        int semester = SemesterInvalid.getValue();
        String category = CategoryInvalid.getValue();
        String note = NoteInvalid.getText();
        UpdateData.updateStudentInvalidPassportInfo(startDate,endDate,semester,category,note,tmpCategoryInvalid);
        cancelAllSocialPassportStudentInfo();
    }

    public void setNewValueManyChildrenFamilyPassport(){
        Date startDate = Date.valueOf(StartDateFamily.getValue());
        Date endDate = null;
        if(EndDateFamily.getValue() != null){
            endDate = Date.valueOf(EndDateFamily.getValue());
        }
        int semester = SemesterFamily.getValue();
        int countChildren = Integer.parseInt(CountChildrenFamily.getText());
        int lessThan18 = Integer.parseInt(LessThan18Family.getText());
        int muchThan18 = Integer.parseInt(MuchThan18Family.getText());
        String note = NoteFamily.getText();
        UpdateData.updateStudentManyChildrenFamilyPassportInfo(startDate,endDate,semester,countChildren,lessThan18,muchThan18,note,tmpManyChildrenFamily);
        cancelAllSocialPassportStudentInfo();
    }

    public void setSemesterComboBox(ComboBox<Integer> semesterComboBox) {
        ObservableList<Integer> existingItems = semesterComboBox.getItems();

        // Створюємо список семестрів, який потрібно додати
        ObservableList<Integer> semesterList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8);

        // Фільтруємо унікальні значення
        ObservableList<Integer> uniqueItems = FXCollections.observableArrayList();
        for (Integer semester : semesterList) {
            if (!existingItems.contains(semester)) {
                uniqueItems.add(semester);
            }
        }

        // Додаємо тільки унікальні значення до ComboBox
        semesterComboBox.getItems().addAll(uniqueItems);
    }


    public void setSocialPassportCategoryComboBox() {
        ObservableList<String> socialCategoryList = DisplayDate.getCategoryInComboBox();

        // Перевіряємо, чи список ComboBox не порожній і чи потрібно оновлювати
        if (!socialCategoryList.isEmpty()) {
            // Отримуємо існуючі елементи з ComboBox
            ObservableList<String> existingItems = CategorySocialPassport.getItems();

            // Фільтруємо нові елементи, які ще не додані в ComboBox
            ObservableList<String> uniqueItems = FXCollections.observableArrayList();
            for (String category : socialCategoryList) {
                if (!existingItems.contains(category)) {
                    uniqueItems.add(category);
                }
            }

            ObservableList<String> filteredList = uniqueItems.filtered(category ->
                    !category.equals("Багатодітна родина") && !category.equals("Інвалід"));

            CategorySocialPassport.getItems().addAll(filteredList);
        }
    }

    private void setInvalidCategoryComboBox(){
        CategoryInvalid.getItems().addAll("І група інвалідності","ІІ група інвалідності","ІІІ група інвалідності","Дитяча інвалідність");
    }

    public void loadPane() {
        // Спочатку ховаємо прогрес-бар
        loadingPane.setVisible(true);
        WorkPlanBorderPane.setDisable(true);

        // Завантажуємо дані в окремому потоці, щоб не блокувати інтерфейс
        Task<Void> loadDataTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
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

                return null;
            }

            @Override
            protected void succeeded() {
                // Коли завантаження завершено, ховаємо прогрес-бар
                loadingPane.setVisible(false);
                WorkPlanBorderPane.setDisable(false);
            }

            @Override
            protected void failed() {
                // Якщо щось пішло не так, ховаємо прогрес-бар і виводимо помилку
                loadingPane.setVisible(false);
                System.out.print("Сталася помилка при завантаженні даних");
            }
        };

        // Запускаємо завантаження в окремому потоці
        new Thread(loadDataTask).start();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPane();
    }

    @Override
    public void setDragWindow(MouseEvent dragEvent) {
        ScreenService.paneDragged(dragEvent,MenuBarHBox);
    }

    @Override
    public void setPressedWindow(MouseEvent mouseEvent) {
        ScreenService.panePressed(mouseEvent);
    }
}
