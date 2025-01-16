package controller.curator;

import services.ClearValueService;
import services.TextFieldService;
import data.AddData;
import data.DisplayDate;
import data.SearchStudentData;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import static controller.LoginController.curatorGroupName;


public class AddInformationAboutStudentController extends TextFieldService implements Initializable {
    @FXML
    private Button GeneralInfoButton,SocialActivityButton,IndividualSupportButton,PromotionButton,SocialPassportButton;
    @FXML
    private TabPane GeneralInfoTab,SocialActivityTab,IndividualSupportTab,PromotionTab,SocialPassportTab;

    @FXML   // Дані про освіту
    private TextField SurnameEducation,NameEducation,MiddleNameEducation,SchoolNameEducation,GradeAvarageEducation;
    @FXML
    private ComboBox<String> SemesterEducation;
    @FXML
    private DatePicker EndDateEducation;
    @FXML
    private ComboBox<String> StudentPIPEducation;

    @FXML   //Дані про військову службу
    private TextField SurnameMilitary,NameMilitary,MiddleNameMilitary,UnitMilitary;
    @FXML
    public ComboBox<String> StudentPIPMilitary;
    @FXML
    private DatePicker StartDateMilitary,EndDateMilitary;

    @FXML   //Дані про роботу
    private TextField SurnameJob,NameJob,MiddleNameJob,PlaceJob,PositionJob;
    @FXML
    private DatePicker StartDateJob,EndDateJob;
    @FXML
    private ComboBox<String> StudentPIPJob;

    @FXML   //Дані про батьків
    private TextField SurnameParents,NameParents,MiddleNameParents;
    @FXML
    private TextField PIPFatherParents,PIPMotherParents;
    @FXML
    private TextField PhoneFatherParents,PhoneMotherParents;
    @FXML
    private ComboBox<String> StudentPIPParents;

    @FXML   //Дані про громадьську діяльність
    private TextField SurnameSocial,NameSocial,MiddleNameSocial;
    @FXML
    private ComboBox<Integer> SemesterSocial;
    @FXML
    private ComboBox<String> StudentPIPSocial;
    @FXML
    private TextField ActivitySocial;
    @FXML
    private DatePicker DateSocial;

    @FXML   //Дані про групову діяльність
    private TextField SurnameGroup,NameGroup,MiddleNameGroup;
    @FXML
    private ComboBox<String> StudentPIPGroup;
    @FXML
    private ComboBox<Integer> SemesterGroup;
    @FXML
    private TextField GroupName,NoteGroup;

    @FXML   //Дані про індивідуальний супровід
    private TextField SurnameSupport,NameSupport,MiddleNameSupport;
    @FXML
    private ComboBox<String> StudentPIPSupport;
    @FXML
    private ComboBox<Integer> SemesterSupport;
    @FXML
    private DatePicker DateSupport;
    @FXML
    private TextField ContentSupport;

    @FXML   //Дані про заохочення
    private TextField SurnamePromotion,NamePromotion,MiddleNamePromotion;
    @FXML
    private ComboBox<String> StudentPIPPromotion;
    @FXML
    private ComboBox<Integer> SemesterPromotion;
    @FXML
    private DatePicker DatePromotion;
    @FXML
    private TextField ContentPromotion;

    @FXML   //Дані про основні категорії соціального паспорту
    private ComboBox<String> StudentPIPSocialPassport;
    @FXML
    private ComboBox<Integer> SemesterSocialPassport;
    @FXML
    private ComboBox<String> CategorySocialPassport;
    @FXML
    private TextField SurNameSocialPassport,NameSocialPassport,MiddleNameSocialPassport;
    @FXML
    private DatePicker StartDateSocialPassport,EndDateSocialPassport;
    @FXML
    private TextField NoteSocialPassport;
    @FXML
    private RadioButton AdultStudentStatusRadioButton;

    @FXML   //Дані про інвалідність
    private ComboBox<String> StudentPIPInvalidPassport;
    @FXML
    private ComboBox<Integer> SemesterInvalidPassport;
    @FXML
    private ComboBox<String> CategoryInvalidPassport;
    @FXML
    private TextField SurnameInvalidPassport,NameInvalidPassport,MiddleNameInvalidPassport;
    @FXML
    private DatePicker StartDateInvalidPassport,EndDateInvalidPassport;
    @FXML
    private TextField NoteInvalidPassport;

    @FXML   //Дані про багатодітну сім'ю
    private TextField SurnameFamily,NameFamily,MiddleNameFamily;
    @FXML
    private DatePicker StartDateFamily,EndDateFamily;
    @FXML
    private ComboBox<Integer> SemesterFamily;
    @FXML
    private ComboBox<String> StudentPIPFamily;
    @FXML
    private TextField CountFamily,LessThan18Family,MuchThan18Family,NoteFamily;

    private void switchScene(Node currentNode, String fxmlFile) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(200), currentNode);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(event -> {
            try {
                // Load the new FXML after the fade-out animation finishes
                Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));

                // Create a new scene
                Stage stage = (Stage) currentNode.getScene().getWindow();
                Scene scene = new Scene(root);
                // Add your CSS file here:
                //scene.getStylesheets().add(getClass().getResource("/css/workPlanPageCss/changeButtonColor.css").toExternalForm());

                // Apply fade-in animation for the new scene
                stage.setScene(scene);
                FadeTransition fadeIn = new FadeTransition(Duration.millis(200), root);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        fadeOut.play();
    }
    public void back(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/WorkStudPage.fxml");
    }

    public void visiblePane(){
        if(GeneralInfoButton.isFocused()){
            GeneralInfoTab.setVisible(true);
            SocialActivityTab.setVisible(false);
            IndividualSupportTab.setVisible(false);
            PromotionTab.setVisible(false);
            SocialPassportTab.setVisible(false);
        } else if(SocialActivityButton.isFocused()) {
            GeneralInfoTab.setVisible(false);
            SocialActivityTab.setVisible(true);
            IndividualSupportTab.setVisible(false);
            PromotionTab.setVisible(false);
            SocialPassportTab.setVisible(false);
        } else if(IndividualSupportButton.isFocused()) {
            GeneralInfoTab.setVisible(false);
            SocialActivityTab.setVisible(false);
            IndividualSupportTab.setVisible(true);
            PromotionTab.setVisible(false);
            SocialPassportTab.setVisible(false);
        } else if(PromotionButton.isFocused()) {
            GeneralInfoTab.setVisible(false);
            SocialActivityTab.setVisible(false);
            IndividualSupportTab.setVisible(false);
            PromotionTab.setVisible(true);
            SocialPassportTab.setVisible(false);
        } else if(SocialPassportButton.isFocused()) {
            GeneralInfoTab.setVisible(false);
            SocialActivityTab.setVisible(false);
            IndividualSupportTab.setVisible(false);
            PromotionTab.setVisible(false);
            SocialPassportTab.setVisible(true);
        }
    }

    private void setStudentPIP() {
        List<String> list = DisplayDate.getStudentFullNames(curatorGroupName);
        ObservableList<String> pipStudent = FXCollections.observableArrayList(list);
        StudentPIPEducation.getItems().addAll(pipStudent);
        StudentPIPMilitary.getItems().addAll(pipStudent);
        StudentPIPJob.getItems().addAll(pipStudent);
        StudentPIPParents.getItems().addAll(pipStudent);
        StudentPIPSocial.getItems().addAll(pipStudent);
        StudentPIPGroup.getItems().addAll(pipStudent);
        StudentPIPSupport.getItems().addAll(pipStudent);
        StudentPIPPromotion.getItems().addAll(pipStudent);
        StudentPIPSocialPassport.getItems().addAll(pipStudent);
        StudentPIPInvalidPassport.getItems().addAll(pipStudent);
        StudentPIPFamily.getItems().addAll(pipStudent);
    }

    public void onEducationStudentSelected() {
        String selectedStudent = StudentPIPEducation.getValue();
        TextFieldService.populateStudentFields(selectedStudent, SurnameEducation, NameEducation, MiddleNameEducation);
    }

    public void onMilitaryStudentSelected() {
        String selectedStudent = StudentPIPMilitary.getValue();
        TextFieldService.populateStudentFields(selectedStudent, SurnameMilitary, NameMilitary, MiddleNameMilitary);
    }

    public void onJobStudentSelected() {
        String selectedStudent = StudentPIPJob.getValue();
        TextFieldService.populateStudentFields(selectedStudent, SurnameJob, NameJob, MiddleNameJob);
    }

    public void onFamilyStudentSelected() {
        String selectedStudent = StudentPIPParents.getValue();
        TextFieldService.populateStudentFields(selectedStudent, SurnameParents, NameParents, MiddleNameParents);
    }

    public void onSocialActivityStudentSelected() {
        String selectedStudent = StudentPIPSocial.getValue();
        TextFieldService.populateStudentFields(selectedStudent, SurnameSocial, NameSocial, MiddleNameSocial);
    }

    public void onGroupActivityStudentSelected(){
        String selectedStudent = StudentPIPGroup.getValue();
        TextFieldService.populateStudentFields(selectedStudent, SurnameGroup, NameGroup, MiddleNameGroup);
    }

    public void onIndividualSupportStudentSelected() {
        String selectedStudent = StudentPIPSupport.getValue();
        TextFieldService.populateStudentFields(selectedStudent, SurnameSupport, NameSupport, MiddleNameSupport);
    }

    public void onPromotionStudentSelected(){
        String selectedStudent = StudentPIPPromotion.getValue();
        TextFieldService.populateStudentFields(selectedStudent, SurnamePromotion, NamePromotion, MiddleNamePromotion);
    }

    public void onSocialPassportStudentSelected(){
        String selectedStudent = StudentPIPSocialPassport.getValue();
        TextFieldService.populateStudentFields(selectedStudent,SurNameSocialPassport,NameSocialPassport, MiddleNameSocialPassport);
    }

    public void onInvalidPassportStudentSelected(){
        String selectedStudent = StudentPIPInvalidPassport.getValue();
        TextFieldService.populateStudentFields(selectedStudent,SurnameInvalidPassport,NameInvalidPassport, MiddleNameInvalidPassport);
    }

    public void onFamilyPassportStudentSelected(){
        String selectedStudent = StudentPIPFamily.getValue();
        TextFieldService.populateStudentFields(selectedStudent,SurnameFamily,NameFamily,MiddleNameFamily);
    }

    public void addEducationInfo(){
        String name = NameEducation.getText();
        String surname = SurnameEducation.getText();
        String middleName = MiddleNameEducation.getText();
        String schoolName = SchoolNameEducation.getText();
        Date endDate = Date.valueOf(EndDateEducation.getValue());
        float averageGrade = Float.valueOf(GradeAvarageEducation.getText());

        int id = SearchStudentData.getIdStudent(name,surname,middleName);
        AddData.addEducationInfo(id,endDate,schoolName,averageGrade);
        ClearValueService.clearEducationInfo(EndDateEducation,SchoolNameEducation,GradeAvarageEducation);
    }

    public void addMilitaryInfo(){
        String name = NameMilitary.getText();
        String surname = SurnameMilitary.getText();
        String middleName = MiddleNameMilitary.getText();
        Date startDate = Date.valueOf(StartDateMilitary.getValue());
        Date endDate = Date.valueOf(EndDateMilitary.getValue());
        String unit = UnitMilitary.getText();

        int id = SearchStudentData.getIdStudent(name,surname,middleName);
        AddData.addMilitaryInfo(id,startDate,endDate,unit);
        ClearValueService.clearMilitaryInfo(StartDateMilitary,EndDateMilitary,UnitMilitary);
    }

    public void addJobInfo(){
        String name = NameJob.getText();
        String surname = SurnameJob.getText();
        String middleName = MiddleNameJob.getText();
        Date startDate = Date.valueOf(StartDateJob.getValue());
        Date endDate = Date.valueOf(EndDateJob.getValue());
        String place = PlaceJob.getText();
        String position = PositionJob.getText();

        int id = SearchStudentData.getIdStudent(name,surname,middleName);
        AddData.addJobInfo(id,startDate,endDate,place,position);
        ClearValueService.clearJobInfo(StartDateJob,EndDateJob,PlaceJob,PositionJob);
    }

    public void addFamilyInfo(){
        String name = NameParents.getText();
        String surname = SurnameParents.getText();
        String middleName = MiddleNameParents.getText();
        String pipFather = PIPFatherParents.getText();
        String pipMother = PIPMotherParents.getText();
        String phoneFather = PhoneFatherParents.getText();
        String phoneMother = PhoneMotherParents.getText();

        int id = SearchStudentData.getIdStudent(name,surname,middleName);
        AddData.addParentsInfo(id,pipFather,pipMother,phoneFather,phoneMother);
        ClearValueService.clearFamilyInfo(PIPFatherParents,PIPMotherParents,PhoneFatherParents,PhoneMotherParents);
    }

    public void addSocialActivityInfo(){
        String name = NameSocial.getText();
        String surname = SurnameSocial.getText();
        String middleName = MiddleNameSocial.getText();
        int semester = SemesterSocial.getValue();
        Date date = Date.valueOf(DateSocial.getValue());
        String activity = ActivitySocial.getText();

        int id = SearchStudentData.getIdStudent(name,surname,middleName);
        AddData.addSocialActivityInfo(id,semester,date,activity);
        ClearValueService.clearSocialActivityInfo(SemesterSocial,DateSocial,ActivitySocial);
    }

    public void addGroupActivityInfo(){
        String name = NameGroup.getText();
        String surname = SurnameGroup.getText();
        String middleName = MiddleNameGroup.getText();
        int semester = SemesterGroup.getValue();
        String groupName = GroupName.getText();
        String note = NoteGroup.getText();

        int id = SearchStudentData.getIdStudent(name,surname,middleName);
        AddData.addGroupActivityInfo(id,semester,groupName,note);
        ClearValueService.clearGroupActivity(SemesterGroup,GroupName,NoteGroup);
    }

    public void addIndividualSupportInfo(){
        String name = NameSupport.getText();
        String surname = SurnameSupport.getText();
        String middleName = MiddleNameSupport.getText();
        int semester = SemesterSupport.getValue();
        Date date = Date.valueOf(DateSupport.getValue());
        String content = ContentSupport.getText();

        int id  = SearchStudentData.getIdStudent(name,surname,middleName);
        AddData.addIndividualSupportInfo(id,semester,date,content);
        ClearValueService.clearIndividualSupport(SemesterSupport,DateSupport,ContentSupport);
    }

    public void addPromotionInfo(){
        String name = NamePromotion.getText();
        String surname = SurnamePromotion.getText();
        String middleName = MiddleNamePromotion.getText();
        int semester = SemesterPromotion.getValue();
        Date date = Date.valueOf(DatePromotion.getValue());
        String content = ContentPromotion.getText();

        int id = SearchStudentData.getIdStudent(name,surname,middleName);
        AddData.addPromotionInfo(id,semester,date,content);
        ClearValueService.clearPromotionInfo(SemesterPromotion,DatePromotion,ContentPromotion);
    }

    public void addSocialPassportInfo(){
        String name = NameSocialPassport.getText();
        String surname = SurNameSocialPassport.getText();
        String middleName = MiddleNameSocialPassport.getText();
        int semester = SemesterSocialPassport.getValue();
        String nameCategory = CategorySocialPassport.getValue();
        Date startDate = Date.valueOf(StartDateSocialPassport.getValue());
        Date endDate = Date.valueOf(EndDateSocialPassport.getValue());
        String note = NoteSocialPassport.getText();
        boolean statusAdult;
        if(AdultStudentStatusRadioButton.isSelected()){
            statusAdult = true;
        }else{
            statusAdult = false;
        }

        int id = SearchStudentData.getIdStudent(name,surname,middleName);
        System.out.print(id);
        AddData.addGeneralSocialPassportInfo(id,nameCategory,semester,startDate,endDate,note,statusAdult);
        ClearValueService.clearSocialPassportField(StartDateSocialPassport,EndDateSocialPassport,SemesterSocialPassport,CategorySocialPassport,NoteSocialPassport,AdultStudentStatusRadioButton);
    }

    public void addInvalidPassportInfo(){
         String name = NameInvalidPassport.getText();
         String surname = SurnameInvalidPassport.getText();
         String middleName = MiddleNameInvalidPassport.getText();
         int semester = SemesterInvalidPassport.getValue();
         String category = CategoryInvalidPassport.getValue();
         Date startDate = Date.valueOf(StartDateInvalidPassport.getValue());
         Date endDate = Date.valueOf(EndDateInvalidPassport.getValue());
         String note = NoteInvalidPassport.getText();

         int id = SearchStudentData.getIdStudent(name,surname,middleName);
         AddData.addInvalidPassportCategoryInfo(id,category,semester,startDate,endDate,note);
    }

    public void addManyChildrenFamily(){
        String name = NameFamily.getText();
        String surname = SurnameFamily.getText();
        String middleName = MiddleNameFamily.getText();
        int semester = SemesterFamily.getValue();
        Date startDate = Date.valueOf(StartDateFamily.getValue());
        Date endDate = Date.valueOf(EndDateFamily.getValue());
        String note = NoteFamily.getText();
        int countChildren = Integer.parseInt(CountFamily.getText());
        int lessThan18 = Integer.parseInt(LessThan18Family.getText());
        int muchThan18 = Integer.parseInt(MuchThan18Family.getText());

        int id = SearchStudentData.getIdStudent(name,surname,middleName);
        AddData.addManyChildrenPassportInfo(id,semester,startDate,endDate,note,countChildren,lessThan18,muchThan18);

    }

    public void setSemesterComboBox(){
        SemesterSocial.getItems().addAll(1,2,3,4,5,6,7,8);
        SemesterGroup.getItems().addAll(1,2,3,4,5,6,7,8);
        SemesterSupport.getItems().addAll(1,2,3,4,5,6,7,8);
        SemesterPromotion.getItems().addAll(1,2,3,4,5,6,7,8);
        SemesterSocialPassport.getItems().addAll(1,2,3,4,5,6,7,8);
        SemesterInvalidPassport.getItems().addAll(1,2,3,4,5,6,7,8);
        SemesterFamily.getItems().addAll(1,2,3,4,5,6,7,8);
    }

    public void setCategoryComboBox(){
        ObservableList<String> list = DisplayDate.getCategoryInComboBox();
        ObservableList<String> filteredList = list.filtered(category ->
                !category.equals("Багатодітна родина") && !category.equals("Інвалід"));
        CategorySocialPassport.getItems().addAll(filteredList);
    }

    public void setInvalidCategoryComboBox(){
         CategoryInvalidPassport.getItems().addAll("І група інвалідності","ІІ група інвалідності","ІІІ група інвалідності","Дитяча інвалідність");
    }
     
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setStudentPIP();
        setSemesterComboBox();
        setCategoryComboBox();
        setInvalidCategoryComboBox();
    }

}
