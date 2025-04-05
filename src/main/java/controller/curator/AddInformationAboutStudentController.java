package controller.curator;

import interfaces.WindowActions.WindowControl;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.ClearValueService;
import services.ScreenService;
import services.TextFieldService;
import data.AddData;
import data.DisplayDate;
import data.SearchStudentData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import services.ValidateValueService;
import start.zine.HelloApplication;

import java.awt.*;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import static controller.login.LoginController.curatorGroupName;


public class AddInformationAboutStudentController extends HelloApplication implements Initializable, WindowControl {
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
    @FXML
    private RadioButton AdultStudentInvalidStatusRadioButton;

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
    @FXML
    private RadioButton AdultStudentManyChildrenStatusRadioButton;

    @FXML // Window
    private HBox Hbox;
    @FXML
    private Button minimizeWindowButton,maximizeWindowButton,closeWindowButton;

    @FXML
    private HBox StudentHBOx;

    public void back(ActionEvent event) {
        switchScene((Node) event.getSource(), "/fxml/curator/WorkWithStudentPane.fxml");
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
        if(ValidateValueService.isStudentSelected(NameEducation,SurnameEducation,MiddleNameEducation)){
            if(ValidateValueService.isEducationFieldEmpty(EndDateEducation,SchoolNameEducation,GradeAvarageEducation)){
                if(!ValidateValueService.isExistEducation(NameEducation.getText(),SurnameEducation.getText(),MiddleNameEducation.getText())){
                    String name = NameEducation.getText();
                    String surname = SurnameEducation.getText();
                    String middleName = MiddleNameEducation.getText();
                    String schoolName = SchoolNameEducation.getText();
                    Date endDate = Date.valueOf(EndDateEducation.getValue());
                    float averageGrade = Float.valueOf(GradeAvarageEducation.getText());


                    int id = SearchStudentData.getIdStudent(name,surname,middleName,curatorGroupName);
                    AddData.addEducationInfo(id,endDate,schoolName,averageGrade);
                    loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
                    ClearValueService.clearEducationInfo(EndDateEducation,SchoolNameEducation,GradeAvarageEducation);
                }else{
                    loadAndShowLoginAlarm("/fxml/notifications/WarningExistEducationInfo.fxml");
                }
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
            }
        }else {
            loadAndShowLoginAlarm("/fxml/notifications/WarningStudentSelected.fxml");
        }

    }

    public void addMilitaryInfo(){
        if(ValidateValueService.isStudentSelected(NameMilitary,SurnameMilitary,MiddleNameMilitary)){
            if(ValidateValueService.isMilitaryFieldEmpty(StartDateMilitary,EndDateMilitary,UnitMilitary)){
                if(!ValidateValueService.isExistMilitary(NameMilitary.getText(),SurnameMilitary.getText(),MiddleNameMilitary.getText())){
                    String name = NameMilitary.getText();
                    String surname = SurnameMilitary.getText();
                    String middleName = MiddleNameMilitary.getText();
                    Date startDate = Date.valueOf(StartDateMilitary.getValue());
                    Date endDate = Date.valueOf(EndDateMilitary.getValue());
                    String unit = UnitMilitary.getText();

                    int id = SearchStudentData.getIdStudent(name,surname,middleName,curatorGroupName);
                    AddData.addMilitaryInfo(id,startDate,endDate,unit);
                    loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
                    ClearValueService.clearMilitaryInfo(StartDateMilitary,EndDateMilitary,UnitMilitary);
                }else{
                    loadAndShowLoginAlarm("/fxml/notifications/WarningExistMilitaryInfo.fxml");
                }
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
            }
        }else {
            loadAndShowLoginAlarm("/fxml/notifications/WarningStudentSelected.fxml");
        }
    }

    public void addJobInfo(){
        if(ValidateValueService.isStudentSelected(NameJob,SurnameJob,MiddleNameJob)){
            if(ValidateValueService.isStudentJobFieldEmpty(StartDateJob,PlaceJob,PositionJob)){
                String name = NameJob.getText();
                String surname = SurnameJob.getText();
                String middleName = MiddleNameJob.getText();
                Date startDate = Date.valueOf(StartDateJob.getValue());
                Date endDate = Date.valueOf(EndDateJob.getValue());
                String place = PlaceJob.getText();
                String position = PositionJob.getText();

                int id = SearchStudentData.getIdStudent(name,surname,middleName,curatorGroupName);
                AddData.addJobInfo(id,startDate,endDate,place,position);
                loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
                ClearValueService.clearJobInfo(StartDateJob,EndDateJob,PlaceJob,PositionJob);
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
            }
        }else {
            loadAndShowLoginAlarm("/fxml/notifications/WarningStudentSelected.fxml");
        }
    }

    public void addFamilyInfo(){
        if(ValidateValueService.isStudentSelected(NameParents,SurnameParents,MiddleNameParents)){
            if(ValidateValueService.isStudentParentsFieldEmpty(PIPFatherParents,PIPMotherParents,PhoneFatherParents,PhoneMotherParents)){
                if(!ValidateValueService.isExistStudentParents(NameParents.getText(),SurnameParents.getText(),MiddleNameParents.getText())){
                    String name = NameParents.getText();
                    String surname = SurnameParents.getText();
                    String middleName = MiddleNameParents.getText();
                    String pipFather = PIPFatherParents.getText();
                    String pipMother = PIPMotherParents.getText();
                    String phoneFather = PhoneFatherParents.getText();
                    String phoneMother = PhoneMotherParents.getText();

                    int id = SearchStudentData.getIdStudent(name,surname,middleName,curatorGroupName);
                    AddData.addParentsInfo(id,pipFather,pipMother,phoneFather,phoneMother);
                    loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
                    ClearValueService.clearFamilyInfo(PIPFatherParents,PIPMotherParents,PhoneFatherParents,PhoneMotherParents);
                }else{
                    loadAndShowLoginAlarm("/fxml/notifications/WarningExistParentsInfo.fxml");
                }
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
            }
        }else {
            loadAndShowLoginAlarm("/fxml/notifications/WarningStudentSelected.fxml");
        }
    }

    public void addSocialActivityInfo(){
        if(ValidateValueService.isStudentSelected(NameSocial,SurnameSocial,MiddleNameSocial)){
            if(ValidateValueService.isSocialActivityFieldEmpty(SemesterSocial,DateSocial,ActivitySocial)){
                String name = NameSocial.getText();
                String surname = SurnameSocial.getText();
                String middleName = MiddleNameSocial.getText();
                int semester = SemesterSocial.getValue();
                Date date = Date.valueOf(DateSocial.getValue());
                String activity = ActivitySocial.getText();

                int id = SearchStudentData.getIdStudent(name,surname,middleName,curatorGroupName);
                AddData.addSocialActivityInfo(id,semester,date,activity);
                loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
                ClearValueService.clearSocialActivityInfo(SemesterSocial,DateSocial,ActivitySocial);
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
            }
        }else {
            loadAndShowLoginAlarm("/fxml/notifications/WarningStudentSelected.fxml");
        }

    }

    public void addGroupActivityInfo(){
        if(ValidateValueService.isStudentSelected(NameGroup,SurnameGroup,MiddleNameGroup)){
            if(ValidateValueService.isGroupActivityFieldEmpty(SemesterGroup,GroupName)){
                String name = NameGroup.getText();
                String surname = SurnameGroup.getText();
                String middleName = MiddleNameGroup.getText();
                int semester = SemesterGroup.getValue();
                String groupName = GroupName.getText();
                String note = NoteGroup.getText();

                int id = SearchStudentData.getIdStudent(name,surname,middleName,curatorGroupName);
                AddData.addGroupActivityInfo(id,semester,groupName,note);
                loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
                ClearValueService.clearGroupActivity(SemesterGroup,GroupName);
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
            }
        }else {
            loadAndShowLoginAlarm("/fxml/notifications/WarningStudentSelected.fxml");
        }

    }

    public void addIndividualSupportInfo(){
        if(ValidateValueService.isStudentSelected(NameSupport,SurnameSupport,MiddleNameSupport)){
            if(ValidateValueService.isIndividualSupportFieldEmpty(SemesterSupport,DateSupport,ContentSupport)){
                String name = NameSupport.getText();
                String surname = SurnameSupport.getText();
                String middleName = MiddleNameSupport.getText();
                int semester = SemesterSupport.getValue();
                Date date = Date.valueOf(DateSupport.getValue());
                String content = ContentSupport.getText();

                int id  = SearchStudentData.getIdStudent(name,surname,middleName,curatorGroupName);
                AddData.addIndividualSupportInfo(id,semester,date,content);
                loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
                ClearValueService.clearIndividualSupport(SemesterSupport,DateSupport,ContentSupport);
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
            }
        }else {
            loadAndShowLoginAlarm("/fxml/notifications/WarningStudentSelected.fxml");
        }
    }

    public void addPromotionInfo(){
        if(ValidateValueService.isStudentSelected(NamePromotion,SurnamePromotion,MiddleNamePromotion)){
            if(ValidateValueService.isPromotionFieldEmpty(SemesterPromotion,DatePromotion,ContentPromotion)){
                String name = NamePromotion.getText();
                String surname = SurnamePromotion.getText();
                String middleName = MiddleNamePromotion.getText();
                int semester = SemesterPromotion.getValue();
                Date date = Date.valueOf(DatePromotion.getValue());
                String content = ContentPromotion.getText();

                int id = SearchStudentData.getIdStudent(name,surname,middleName,curatorGroupName);
                AddData.addPromotionInfo(id,semester,date,content);
                loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
                ClearValueService.clearPromotionInfo(SemesterPromotion,DatePromotion,ContentPromotion);
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
            }
        }else {
            loadAndShowLoginAlarm("/fxml/notifications/WarningStudentSelected.fxml");
        }
    }

    public void addSocialPassportInfo(){
        if(ValidateValueService.isStudentSelected(NameSocialPassport,SurNameSocialPassport,MiddleNameSocialPassport)){
            if(ValidateValueService.isSocialPassportFieldEmpty(StartDateSocialPassport,SemesterSocialPassport,CategorySocialPassport)){
                String name = NameSocialPassport.getText();
                String surname = SurNameSocialPassport.getText();
                String middleName = MiddleNameSocialPassport.getText();
                int semester = SemesterSocialPassport.getValue();
                String nameCategory = CategorySocialPassport.getValue();
                Date startDate = Date.valueOf(StartDateSocialPassport.getValue());
                Date endDate = null;
                if(EndDateSocialPassport.getValue() != null){
                    endDate = Date.valueOf(EndDateSocialPassport.getValue());
                }
                String note = NoteSocialPassport.getText();
                boolean statusAdult;
                if(AdultStudentStatusRadioButton.isSelected()){
                    statusAdult = true;
                }else{
                    statusAdult = false;
                }

                int id = SearchStudentData.getIdStudent(name,surname,middleName,curatorGroupName);
                System.out.print(id);
                AddData.addGeneralSocialPassportInfo(id,nameCategory,semester,startDate,endDate,note,statusAdult);
                loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
                ClearValueService.clearSocialPassportField(StartDateSocialPassport,EndDateSocialPassport,SemesterSocialPassport,CategorySocialPassport,NoteSocialPassport,AdultStudentStatusRadioButton);
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
            }
        }else {
            loadAndShowLoginAlarm("/fxml/notifications/WarningStudentSelected.fxml");
        }
    }

    public void addInvalidPassportInfo(){
        if(ValidateValueService.isStudentSelected(NameInvalidPassport,SurnameInvalidPassport,MiddleNameInvalidPassport)){
            if(ValidateValueService.isInvalidSocialPassportFieldEmpty(StartDateInvalidPassport,SemesterInvalidPassport,CategoryInvalidPassport)){
                if(!ValidateValueService.isInvalidSocialPassportExist(NameInvalidPassport.getText(),SurnameInvalidPassport.getText(),MiddleNameInvalidPassport.getText())){
                    String name = NameInvalidPassport.getText();
                    String surname = SurnameInvalidPassport.getText();
                    String middleName = MiddleNameInvalidPassport.getText();
                    int semester = SemesterInvalidPassport.getValue();
                    String category = CategoryInvalidPassport.getValue();
                    Date startDate = Date.valueOf(StartDateInvalidPassport.getValue());
                    Date endDate = null;
                    if(EndDateInvalidPassport.getValue() != null){
                        endDate = Date.valueOf(EndDateInvalidPassport.getValue());
                    }
                    String note = NoteInvalidPassport.getText();
                    boolean statusAdult;
                    if(AdultStudentInvalidStatusRadioButton.isSelected()){
                        statusAdult = true;
                    }else{
                        statusAdult = false;
                    }

                    int id = SearchStudentData.getIdStudent(name,surname,middleName,curatorGroupName);
                    AddData.addInvalidPassportCategoryInfo(id,category,semester,startDate,endDate,note,statusAdult);
                    loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
                }else{
                    loadAndShowLoginAlarm("/fxml/notifications/WarningExistInvalidPassport.fxml");
                }
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
            }
        }else {
            loadAndShowLoginAlarm("/fxml/notifications/WarningStudentSelected.fxml");
        }
    }

    public void addManyChildrenFamily(){
        if(ValidateValueService.isStudentSelected(NameFamily,SurnameFamily,MiddleNameFamily)){
            if(ValidateValueService.isManyChildrenFieldEmpty(StartDateFamily,SemesterFamily,CountFamily,LessThan18Family,MuchThan18Family)){
                if(!ValidateValueService.isManyChildrenExist(NameFamily.getText(),SurnameFamily.getText(),MiddleNameFamily.getText())){
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
                    boolean statusAdult;
                    if(AdultStudentManyChildrenStatusRadioButton.isSelected()){
                        statusAdult = true;
                    }else{
                        statusAdult = false;
                    }

                    int id = SearchStudentData.getIdStudent(name,surname,middleName,curatorGroupName);
                    AddData.addManyChildrenPassportInfo(id,semester,startDate,endDate,note,countChildren,lessThan18,muchThan18,statusAdult);
                    loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessAddNotification.fxml");
                }else{
                    loadAndShowLoginAlarm("/fxml/notifications/WarningExistManyChildrenFamily.fxml");
                }
            }else {
                loadAndShowLoginAlarm("/fxml/notifications/WarningEmptyField.fxml");
            }
        }else {
            loadAndShowLoginAlarm("/fxml/notifications/WarningStudentSelected.fxml");
        }
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

    private void correctLoadPane(){
        StudentHBOx.setPickOnBounds(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setStudentPIP();
        setSemesterComboBox();
        setCategoryComboBox();
        setInvalidCategoryComboBox();
        correctLoadPane();
    }

    @Override
    public void setMinimizeWindowButton() {
        ScreenService.minimized_Window(minimizeWindowButton);
    }

    @Override
    public void setMaximizeWindowButton() {
        ScreenService.maximized_Window(maximizeWindowButton.getScene().getWindow());
    }

    @Override
    public void setCloseWindow() {
        ScreenService.close_Window();
    }

    @Override
    public void setDragWindow(MouseEvent dragEvent) {
        ScreenService.paneDragged(dragEvent,Hbox);
    }

    @Override
    public void setPressedWindow(MouseEvent mouseEvent) {
        ScreenService.panePressed(mouseEvent);
    }
}
