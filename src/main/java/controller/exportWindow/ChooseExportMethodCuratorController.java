package controller.exportWindow;

import data.DisplayDate;
import hibernate.entity.*;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import start.zine.StartApplication;

import java.util.List;

import static controller.curator.WorkGroupController.*;
import static controller.login.LoginController.curatorEmail;
import static services.email.EmailSender.exportAndSendStudentInfoExcelByEmail;
import static services.exportExel.ExelExportService.exportStudentInfoOnDisk;

public class ChooseExportMethodCuratorController extends StartApplication {
    @FXML
    private RadioButton SaveDiskRadioButton,SendEmailRadioButton;

    private List<StudentInfo> studentInfoList;
    private List<EducationInfo> educationInfoList;
    private List<MilitaryService> militaryList;
    private List<StudentParents> parentsList;
    private List<StudentJob> jobList;
    private List<CircleActivity> circleActivityList;
    private List<SocialActivity> socialActivityList;
    private List<Promotion> promotionList;
    private List<IndividualSupport> individualSupportList;
    private List<SocialPassport> socialPassportList;

    public void chooseExportMethod() {
        if(SendEmailRadioButton.isSelected()) {
            try{
                getDataForExport(studentWorkId);
                exportAndSendStudentInfoExcelByEmail(curatorEmail, studentWorkSurname, studentWorkName, studentWorkMiddleName, studentInfoList, educationInfoList, militaryList, parentsList, jobList, socialActivityList, circleActivityList, individualSupportList, promotionList, socialPassportList);
                loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessEmailExportNotification.fxml");
                closeDialog();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(SaveDiskRadioButton.isSelected()) {
            try {
                getDataForExport(studentWorkId);
                exportStudentInfoOnDisk(studentWorkName,studentWorkSurname,studentWorkMiddleName,studentInfoList,educationInfoList,militaryList,parentsList,jobList,circleActivityList,socialActivityList,promotionList,individualSupportList,socialPassportList);
                loadAndShowLoginSuccess("/fxml/notifications/successNotifications/SuccessExportNotification.fxml");
                closeDialog();
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void getDataForExport(int studentId){
        studentInfoList = DisplayDate.loadByStudentId(StudentInfo.class,"id", studentId);
        educationInfoList = DisplayDate.loadByStudentId(EducationInfo.class, "studentInfo.id", studentId);
        militaryList = DisplayDate.loadByStudentId(MilitaryService.class, "studentInfo.id", studentId);
        parentsList = DisplayDate.loadByStudentId(StudentParents.class, "studentInfo.id", studentId);
        jobList = DisplayDate.loadByStudentId(StudentJob.class, "studentInfo.id", studentId);
        circleActivityList = DisplayDate.loadByStudentId(CircleActivity.class, "studentInfo.id", studentId);
        socialActivityList = DisplayDate.loadByStudentId(SocialActivity.class, "studentInfo.id", studentId);
        promotionList = DisplayDate.loadByStudentId(Promotion.class, "studentInfo.id", studentId);
        individualSupportList = DisplayDate.loadByStudentId(IndividualSupport.class, "studentInfo.id", studentId);
        socialPassportList = DisplayDate.loadByStudentId(SocialPassport.class, "studentInfo.id", studentId);
    }

    public void closeDialog() {
        saveDialog.setResult(Boolean.TRUE);
        saveDialog.close();
    }
}
