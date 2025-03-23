package data;
import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import tableView.RemovedStudentPrototype;
import tableView.SocialPassportCategoryPrototype;
import tableView.SocialPassportPrototype;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static controller.ExtendedInformationAboutStudent.*;
import static controller.admin.AdminGroupController.*;

public class DisplayDate  {
    public static final String GET_CURATOR_AND_EMAIL = "SELECT c.id,c.name,c.surname,c.middleName,c.group, u.Email\n" +
                                                        "FROM Curators c\n" +
                                                        "LEFT JOIN User u ON c.id = u.curators.id";

    public static final String GET_SOCIAL_PASSPORT_INFO = "SELECT new tableView.SocialPassportPrototype(s.id,s.studentInfo.name,s.studentInfo.surname,s.studentInfo.middleName,c.category,s.studentInfo.groupName,s.semester)\n" +
                                                          "FROM SocialPassport s\n" +
                                                          "INNER JOIN SpCategoryName c on s.spCategoryName.id = c.id Where s.studentInfo.status = true";

    public static final String GET_SOCIAL_BY_GROUP_NAME_PASSPORT_INFO = "SELECT s.id,s.studentInfo.name,s.studentInfo.surname,s.studentInfo.middleName,s.studentInfo.groupName,c.category,s.semester\n" +
                                                                        "FROM SocialPassport s\n" +
                                                                        "INNER JOIN SpCategoryName c on s.spCategoryName.id = c.id\n" +
                                                                        "WHERE s.studentInfo.groupName = :groupName and s.studentInfo.status = true";

    public static final String GET_SOCIAL_BY_CATEGORY_NAME_PASSPORT_INFO = "SELECT s.id,s.studentInfo.name,s.studentInfo.surname,s.studentInfo.middleName,s.studentInfo.groupName,c.category,s.semester\n" +
                                                                           "FROM SocialPassport s\n" +
                                                                           "INNER JOIN SpCategoryName c on s.spCategoryName.id = c.id\n" +
                                                                           "WHERE c.category = :categoryName and s.studentInfo.status = true";

    public static final String GET_INVALID_INFO = "SELECT s\n" +
                                                  "FROM SocialPassport s\n" +
                                                  "WHERE s.studentInfo.id = :studentId  And s.invalidStatus = true";

    public static final String GET_STUDENT_SOCIAL_PASSPORT_INFO_LIST_FOR_EXPORT = "SELECT s\n" +
                                                                                  "FROM SocialPassport s Where s.studentInfo.status = true";

    public static final String GET_MANY_CHILDREN_FAMILY = "SELECT s FROM SocialPassport s Where s.studentInfo.id = :studentId AND s.manyChildrenStatus = true";
    public static final String GET_MANY_CHILDREN_FAMILY_FROM_TABLE = "SELECT m FROM SpManyChildrenFamily m Where m.socialPassport.id = :passportId";
    public static final String GET_INVALID_CATEGORY_INFO = "SELECT i FROM SpInvalidPeople i Where i.socialPassport.id = :passportId ";

    public static final String GET_SOCIAL_PASSPORT_CATEGORY_NAME = "SELECT c.category FROM SpCategoryName c";
    public static final String GET_SOCIAL_PASSPORT_CATEGORY_NAME_IN_TABLE = "SELECT c FROM SpCategoryName c Where c.id = :categoryId ";

    public static final String GET_STUDENT_CATEGORY = "SELECT new tableView.SocialPassportCategoryPrototype(c.category) FROM SpCategoryName c";
    public static final String GET_GROUP_INFO = "SELECT id,groupName,curator,profession FROM Groups WHERE Active = true";
    public static final String GET_CURATOR_NAME = "SELECT CONCAT(surname, ' ', name, ' ', middleName) FROM Curators Where group is null";
    public static final String GET_STUDENT_EDUCATION_INFO = "FROM EducationInfo WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_MILITARY_INFO = "FROM MilitaryService WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_JOB_INFO = "FROM StudentJob WHERE studentInfo.id = :studentId and place = :place";
    public static final String GET_STUDENT_JOB_INFO_ONE_RESULT = "FROM StudentJob WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_JOB_INFO_ONE_LIST = "FROM StudentJob WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_PARENTS_INFO = "FROM StudentParents WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_SOCIAL_INFO = "FROM SocialActivity WHERE studentInfo.id = :studentId and activity = :activity";
    public static final String GET_STUDENT_SOCIAL_INFO_ONE_RESULT = "FROM SocialActivity WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_SOCIAL_INFO_LIST = "FROM SocialActivity WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_GROUP_INFO = "FROM CircleActivity WHERE studentInfo.id = :studentId and circleName = :groupName";
    public static final String GET_STUDENT_GROUP_INFO_ONE_RESULT = "FROM CircleActivity WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_GROUP_INFO_LIST = "FROM CircleActivity WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_INDIVIDUAL_SUPPORT_INFO = "FROM IndividualSupport WHERE studentInfo.id = :studentId and content = :content";
    public static final String GET_STUDENT_INDIVIDUAL_SUPPORT_INFO_ONE_RESULT = "FROM IndividualSupport WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_INDIVIDUAL_SUPPORT_INFO_LIST = "FROM IndividualSupport WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_PROMOTION_INFO = "FROM Promotion WHERE studentInfo.id = :studentId and content = :content";
    public static final String GET_STUDENT_PROMOTION_INFO_ONE_RESULT = "FROM Promotion WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_PROMOTION_INFO_LIST = "FROM Promotion WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_SOCIAL_PASSPORT_INFO = "FROM SocialPassport s WHERE s.studentInfo.id = :studentId and s.invalidStatus = false and s.manyChildrenStatus = false and s.spCategoryName.category = :categoryName";
    public static final String GET_STUDENT_SOCIAL_PASSPORT_INFO_LIST = "FROM SocialPassport s WHERE s.studentInfo.id = :studentId and s.invalidStatus = false and s.manyChildrenStatus = false";

    public static Session session = null;


    public static ObservableList<StudentInfo> getDataStudentInfo(String groupName) {
        ObservableList<StudentInfo> studentInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<StudentInfo> resoultlist = session.createQuery("SELECT si.id, si.name,si.surname,si.middleName,si.date_of_birth,si.phoneNumber,si.address FROM StudentInfo si WHERE si.groupName = :groupName and si.status = true", StudentInfo.class).setParameter("groupName", groupName).list();
            studentInfo.addAll(resoultlist);

            session.getTransaction().commit();
            for (int i = 0; i < resoultlist.size(); i++) {
                resoultlist.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
        return studentInfo;
    }

    public static ObservableList<RemovedStudentPrototype> getRemovedStudentInfo() {
        ObservableList<RemovedStudentPrototype> studentInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<RemovedStudentPrototype> resoultlist = session.createQuery("SELECT NEW tableView.RemovedStudentPrototype(" +
                                                                            "s.id, s.name, s.surname, s.middleName, s.date_of_birth ,s.phoneNumber, s.address, s.removedDate) " +
                                                                            "FROM StudentInfo s WHERE s.status = false", RemovedStudentPrototype.class).getResultList();
            studentInfo.addAll(resoultlist);

            session.getTransaction().commit();
            for (int i = 0; i < resoultlist.size(); i++) {
                resoultlist.get(i).setStudentId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
        return studentInfo;
    }

    public static ObservableList<StudentInfo> getStudentInfo() {
        ObservableList<StudentInfo> studentInfo = FXCollections.observableArrayList();
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<StudentInfo> resultList = session.createQuery("FROM StudentInfo WHERE status = true", StudentInfo.class).getResultList();
            studentInfo.addAll(resultList);

            session.getTransaction().commit();
            for (int i = 0; i < resultList.size(); i++) {
                resultList.get(i).setId(i + 1);
            }
        }catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
        return studentInfo;
    }

    public static ObservableList<SocialPassportPrototype> getSocialPassportInfo() {
        ObservableList<SocialPassportPrototype> socialPassportInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<SocialPassportPrototype> resultList = session.createQuery(GET_SOCIAL_PASSPORT_INFO, SocialPassportPrototype.class).getResultList();
            socialPassportInfo.addAll(resultList);

            session.getTransaction().commit();
            for (int i = 0; i < resultList.size(); i++) {
                resultList.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        } finally {
            HibernateUtil.closeSession(session);
        }
        return socialPassportInfo;
    }

    public static ObservableList<SocialPassportCategoryPrototype> getSocialPassportCategoryInfo() {
        ObservableList<SocialPassportCategoryPrototype> socialPassportInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();


            List<SocialPassportCategoryPrototype> resultList = session.createQuery(GET_STUDENT_CATEGORY, SocialPassportCategoryPrototype.class).getResultList();
            socialPassportInfo.addAll(resultList);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        } finally {
            HibernateUtil.closeSession(session);
        }
        return socialPassportInfo;
    }

    public static ObservableList<String> getCategoryInComboBox() {
        ObservableList<String> nameCategory = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try {
            List<String> resultList = session.createQuery(GET_SOCIAL_PASSPORT_CATEGORY_NAME, String.class).getResultList();
            nameCategory.addAll(resultList);
        } finally {
            session.close();
        }
        return nameCategory;
    }


    public static ObservableList<WorkPlan> getDataWithParameterForAdminPlanInfo(Date startDate, Date endDate) {
        String hql = "FROM WorkPlan WHERE executionDate BETWEEN :startDate AND :endDate" ;
        ObservableList<WorkPlan> planInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<WorkPlan> resoultlist = session.createQuery(hql, WorkPlan.class).setParameter("startDate", startDate).setParameter("endDate", endDate).getResultList();
            planInfo.addAll(resoultlist);

            session.getTransaction().commit();

            for (int i = 0; i < resoultlist.size(); i++) {
                resoultlist.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
        return planInfo;
    }

    public static ObservableList<WorkPlan> getDataWithParameterPlanInfo(Date startDate, Date endDate,String performer) {
        String hql = "FROM WorkPlan WHERE executionDate BETWEEN :startDate AND :endDate AND (performer = :performer or performer = 'Адміністратор')" ;
        ObservableList<WorkPlan> planInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<WorkPlan> resoultlist = session.createQuery(hql, WorkPlan.class).setParameter("startDate", startDate).setParameter("endDate", endDate).setParameter("performer", performer).getResultList();
            planInfo.addAll(resoultlist);

            session.getTransaction().commit();

            for (int i = 0; i < resoultlist.size(); i++) {
                resoultlist.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
        return planInfo;
    }

    public static ObservableList<WorkPlan> getPlanForAdminInfo() {
        String hql = "FROM WorkPlan";
        ObservableList<WorkPlan> planInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<WorkPlan> resoultlist = session.createQuery(hql, WorkPlan.class).getResultList();
            planInfo.addAll(resoultlist);

            session.getTransaction().commit();
            for (int i = 0; i < resoultlist.size(); i++) {
                resoultlist.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
        return planInfo;
    }

    public static ObservableList<WorkPlan> getDataBySemesterForAdminPlanInfo(int semester) {
        String hql = "FROM WorkPlan WHERE semester = :semester";
        ObservableList<WorkPlan> planInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<WorkPlan> resoultlist = session.createQuery(hql, WorkPlan.class).setParameter("semester",semester).getResultList();
            planInfo.addAll(resoultlist);

            session.getTransaction().commit();
            for (int i = 0; i < resoultlist.size(); i++) {
                resoultlist.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
        return planInfo;
    }

    public static ObservableList<StudentInfo> getDataByGroupNameForAdminStudentInfo(String groupName) {
        String hql = "FROM StudentInfo WHERE groupName = :groupName and status = true";
        ObservableList<StudentInfo> studentInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<StudentInfo> resoultlist = session.createQuery(hql, StudentInfo.class).setParameter("groupName",groupName).getResultList();
            studentInfo.addAll(resoultlist);

            session.getTransaction().commit();
            for (int i = 0; i < resoultlist.size(); i++) {
                resoultlist.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
        return studentInfo;
    }

    public static ObservableList<SocialPassportPrototype> getDataByGroupNameForAdminSocialPassport(String groupName) {
        ObservableList<SocialPassportPrototype> studentInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<SocialPassportPrototype> resoultlist = session.createQuery(GET_SOCIAL_BY_GROUP_NAME_PASSPORT_INFO, SocialPassportPrototype.class).setParameter("groupName",groupName).getResultList();
            studentInfo.addAll(resoultlist);

            session.getTransaction().commit();
            for (int i = 0; i < resoultlist.size(); i++) {
                resoultlist.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
        return studentInfo;
    }

    public static ObservableList<SocialPassportPrototype> getDataByCategoryNameForAdminSocialPassport(String categoryName) {
        ObservableList<SocialPassportPrototype> studentInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<SocialPassportPrototype> resoultlist = session.createQuery(GET_SOCIAL_BY_CATEGORY_NAME_PASSPORT_INFO, SocialPassportPrototype.class).setParameter("categoryName",categoryName).getResultList();
            studentInfo.addAll(resoultlist);

            session.getTransaction().commit();
            for (int i = 0; i < resoultlist.size(); i++) {
                resoultlist.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
        return studentInfo;
    }

    public static ObservableList<WorkPlan> getDataBySemesterPlanInfo(int semester,String performer) {
        String hql = "FROM WorkPlan WHERE semester = :semester AND (performer = :performer OR performer = 'Адміністратор')";
        ObservableList<WorkPlan> planInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<WorkPlan> resoultlist = session.createQuery(hql, WorkPlan.class).setParameter("semester",semester).setParameter("performer", performer).getResultList();
            planInfo.addAll(resoultlist);

            for (int i = 0; i < resoultlist.size(); i++) {
                resoultlist.get(i).setId(i + 1);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
        return planInfo;
    }

    public static List<String> getStudentFullNames(String groupName) {
        List<String> studentNames = new ArrayList<>();
        session = HibernateUtil.getSession();
        try {
            List<StudentInfo> students = session.createQuery("FROM StudentInfo WHERE groupName = :groupName and status = true", StudentInfo.class)
                                                            .setParameter("groupName", groupName).list();

            for (StudentInfo student : students) {
                studentNames.add(student.getFullName());
            }
        } finally {
            session.close();
        }
        return studentNames;
    }

    public static List<String> getGroupName() {
        List<String> groupNames = new ArrayList<>();
        session = HibernateUtil.getSession();
        try {
            List<String> students = session.createQuery("SELECT groupName FROM Groups WHERE Active = true", String.class).list();
            groupNames.addAll(students);
        }catch (Exception e){
            e.printStackTrace();
        }
        return groupNames;
    }

    public static List<String> getGroupForAddCuratorName() {
        List<String> groupNames = new ArrayList<>();
        session = HibernateUtil.getSession();
        try {
            List<String> students = session.createQuery("SELECT groupName FROM Groups WHERE status = false and Active = true", String.class).list();
            groupNames.addAll(students);
        }catch (Exception e){
            e.printStackTrace();
        }
        return groupNames;
    }


    public static ObservableList<Curators> getCurators() {
        ObservableList<Curators> groupNames = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try {
            List<Curators> students = session.createQuery(GET_CURATOR_AND_EMAIL, Curators.class).list();
            groupNames.addAll(students);


            for (int i = 0; i < students.size(); i++) {
                students.get(i).setId(i + 1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return groupNames;
    }

    public static List<String> getCuratorName(){
        List<String> curatorName = new ArrayList<>();
        session = HibernateUtil.getSession();

        try{
            List<String> curators = session.createQuery(GET_CURATOR_NAME, String.class).list();
            curatorName.addAll(curators);
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException(e);
        }

        return curatorName;
    }

    public static Curators getCuratorByUserEmail(String email) {
        String hql = "SELECT curators FROM User  WHERE Email = :email";
        try (Session session = HibernateUtil.getFactory().openSession()) {
            return session.createQuery(hql, Curators.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }


    public static ObservableList<Groups> getFullGroupInfo() {
        ObservableList<Groups> groupNames = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try {
            List<Groups> group = session.createQuery(GET_GROUP_INFO, Groups.class).list();
            groupNames.addAll(group);

            for (int i = 0; i < group.size(); i++) {
                group.get(i).setId(i + 1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return groupNames;
    }

    public static ObservableList<WorkPlan> getFullPlanInfo(String performer) {
        ObservableList<WorkPlan> planNames = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try {
            List<WorkPlan> workPlan = session.createQuery(
                            "FROM WorkPlan Where performer = :performer or performer = 'Адміністратор'", WorkPlan.class)
                    .setParameter("performer", performer)
                    .getResultList();

            for (int i = 0; i < workPlan.size(); i++) {
                workPlan.get(i).setId(i + 1);
            }

            System.out.print("результат " + performer);
            planNames.addAll(workPlan);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planNames;
    }

    public static void setFullGroupInfo(int id){
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            Groups groups = session.get(Groups.class, id);
            groupName = groups.getGroupName();
            groupCurator = groups.getCurator();
            groupProfession = groups.getProfession();
            groupCourse = groups.getCourse();
            groupEducationAndProfessionProgram = groups.getEducationProgram();
            groupLevelOfEducation = groups.getLevelOfEducation();
            groupFormOfEducation = groups.getFormOfEducation();
            groupYearOfStudy = groups.getYearOfStudy();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static EducationInfo selectStudentEducationInfo(int id){
        EducationInfo educationInfo = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            educationInfo = session.createQuery(GET_STUDENT_EDUCATION_INFO, EducationInfo.class).setParameter("studentId", studentId).getSingleResult();
            endDateEducation = educationInfo.getEndDate();
            schoolNameEducation = educationInfo.getSchoolName();
            gradeAverageEducation = educationInfo.getGradeAvarage();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return educationInfo;
    }

    public static MilitaryService selectStudentMilitaryInfo(){
        MilitaryService militaryService = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            militaryService = session.createQuery(GET_STUDENT_MILITARY_INFO, MilitaryService.class).setParameter("studentId", studentId).getSingleResult();
            startDateMilitary = militaryService.getStartDate();
            endDateMilitary = militaryService.getEndDate();
            unitMilitary = militaryService.getUnit();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return militaryService;
    }

    public static StudentJob selectStudentJobInfo(String place){
        StudentJob studentJob = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            studentJob = session.createQuery(GET_STUDENT_JOB_INFO, StudentJob.class).setParameter("studentId", studentId).setParameter("place", place).getSingleResult();
            startDateJob = studentJob.getStartDate();
            endDateJob = studentJob.getEndDate();
            placeJob = studentJob.getPlace();
            positionJob = studentJob.getPosition();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return studentJob;
    }

    public static StudentJob selectStudentJobInfoOneResult(){
        StudentJob studentJob = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            studentJob = session.createQuery(GET_STUDENT_JOB_INFO_ONE_RESULT, StudentJob.class).setParameter("studentId", studentId).getSingleResult();
            startDateJob = studentJob.getStartDate();
            endDateJob = studentJob.getEndDate();
            placeJob = studentJob.getPlace();
            positionJob = studentJob.getPosition();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return studentJob;
    }

    public static List<String> selectStudentJobPlace(String name,String surname,String middleName){
        List<String> socialPassport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialPassport = session.createQuery("SELECT s.place FROM StudentJob s WHERE s.studentInfo.name = :name and s.studentInfo.surname = :surname and s.studentInfo.middleName = :middleName", String.class)
                    .setParameter("name", name)
                    .setParameter("surname", surname)
                    .setParameter("middleName", middleName).getResultList();


            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return socialPassport;
    }

    public static List<StudentJob> selectStudentJobInfoList(){
        List<StudentJob> studentJob = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            studentJob = session.createQuery(GET_STUDENT_JOB_INFO_ONE_LIST, StudentJob.class).setParameter("studentId", studentId).getResultList();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return studentJob;
    }

    public static StudentParents selectStudentParentsInfo(){
        StudentParents studentParents = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            studentParents = session.createQuery(GET_STUDENT_PARENTS_INFO, StudentParents.class).setParameter("studentId", studentId).getSingleResult();
            pipFatherParent = studentParents.getFatherFullName();
            pipMotherParent = studentParents.getMotherFullName();
            phoneFatherParent = studentParents.getPhoneFather();
            phoneMotherParent = studentParents.getPhoneMother();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return studentParents;
    }

    public static SocialActivity selectSocialActivityInfo(String activity){
        SocialActivity socialActivity = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialActivity= session.createQuery(GET_STUDENT_SOCIAL_INFO, SocialActivity.class).setParameter("studentId", studentId).setParameter("activity", activity).getSingleResult();
            semesterSocial = socialActivity.getSemestr();
            dateSocial = socialActivity.getDate();
            activitySocial = socialActivity.getActivity();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return socialActivity;
    }

    public static SocialActivity selectSocialActivityInfoOneResult(){
        SocialActivity socialActivity = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialActivity= session.createQuery(GET_STUDENT_SOCIAL_INFO_ONE_RESULT, SocialActivity.class).setParameter("studentId", studentId).getSingleResult();
            semesterSocial = socialActivity.getSemestr();
            dateSocial = socialActivity.getDate();
            activitySocial = socialActivity.getActivity();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return socialActivity;
    }

    public static List<SocialActivity> selectSocialActivityList(){
        List<SocialActivity> socialActivity = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialActivity= session.createQuery(GET_STUDENT_SOCIAL_INFO_LIST, SocialActivity.class).setParameter("studentId", studentId).getResultList();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return socialActivity;
    }

    public static List<String> selectSocialActivity(String name,String surname,String middleName){
        List<String> socialPassport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialPassport = session.createQuery("SELECT s.activity FROM SocialActivity s WHERE s.studentInfo.name = :name and s.studentInfo.surname = :surname and s.studentInfo.middleName = :middleName", String.class)
                    .setParameter("name", name)
                    .setParameter("surname", surname)
                    .setParameter("middleName", middleName).getResultList();


            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return socialPassport;
    }

    public static CircleActivity selectGroupActivityInfo(String groupName){
        CircleActivity circleActivity = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            circleActivity = session.createQuery(GET_STUDENT_GROUP_INFO, CircleActivity.class).setParameter("studentId", studentId).setParameter("groupName", groupName).getSingleResult();
            semesterGroup = circleActivity.getSemestr();
            groupNameGroup = circleActivity.getCircleName();
            noteGroup = circleActivity.getNote();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return circleActivity;
    }

    public static CircleActivity selectGroupActivityInfoOneResult(){
        CircleActivity circleActivity = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            circleActivity = session.createQuery(GET_STUDENT_GROUP_INFO_ONE_RESULT, CircleActivity.class).setParameter("studentId", studentId).getSingleResult();
            semesterGroup = circleActivity.getSemestr();
            groupNameGroup = circleActivity.getCircleName();
            noteGroup = circleActivity.getNote();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return circleActivity;
    }

    public static List<CircleActivity> selectGroupActivityList(){
        List<CircleActivity> circleActivity = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            circleActivity = session.createQuery(GET_STUDENT_GROUP_INFO_LIST, CircleActivity.class).setParameter("studentId", studentId).getResultList();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return circleActivity;
    }

    public static List<String> selectGroupName(String name,String surname,String middleName){
        List<String> socialPassport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialPassport = session.createQuery("SELECT c.circleName FROM CircleActivity c WHERE c.studentInfo.name = :name and c.studentInfo.surname = :surname and c.studentInfo.middleName = :middleName", String.class)
                    .setParameter("name", name)
                    .setParameter("surname", surname)
                    .setParameter("middleName", middleName).getResultList();


            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return socialPassport;
    }

    public static IndividualSupport selectStudentIndividualSupportInfo(String content){
        IndividualSupport individualSupport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            individualSupport = session.createQuery(GET_STUDENT_INDIVIDUAL_SUPPORT_INFO, IndividualSupport.class).setParameter("studentId", studentId).setParameter("content", content).getSingleResult();
            semesterSupport = individualSupport.getSemestr();
            dateSupport = individualSupport.getDate();
            contentSupport = individualSupport.getContent();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return individualSupport;
    }

    public static IndividualSupport selectStudentIndividualSupportInfoOneResult(){
        IndividualSupport individualSupport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            individualSupport = session.createQuery(GET_STUDENT_INDIVIDUAL_SUPPORT_INFO_ONE_RESULT, IndividualSupport.class).setParameter("studentId", studentId).getSingleResult();
            semesterSupport = individualSupport.getSemestr();
            dateSupport = individualSupport.getDate();
            contentSupport = individualSupport.getContent();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return individualSupport;
    }

    public static List<IndividualSupport> selectStudentIndividualSupportInfoList(){
        List<IndividualSupport> individualSupport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            individualSupport = session.createQuery(GET_STUDENT_INDIVIDUAL_SUPPORT_INFO_LIST, IndividualSupport.class).setParameter("studentId", studentId).getResultList();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return individualSupport;
    }

    public static List<String> selectIndividualSupportContentName(String name,String surname,String middleName){
        List<String> socialPassport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialPassport = session.createQuery("SELECT i.content FROM IndividualSupport i WHERE i.studentInfo.name = :name and i.studentInfo.surname = :surname and i.studentInfo.middleName = :middleName", String.class)
                    .setParameter("name", name)
                    .setParameter("surname", surname)
                    .setParameter("middleName", middleName).getResultList();


            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return socialPassport;
    }

    public static Promotion selectStudentPromotionInfo(String content){
        Promotion promotion = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            promotion = session.createQuery(GET_STUDENT_PROMOTION_INFO, Promotion.class).setParameter("studentId", studentId).setParameter("content", content).getSingleResult();
            semesterPromotion = promotion.getSemestr();
            datePromotion = promotion.getStartDate();
            contentPromotion = promotion.getContent();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return promotion;
    }

    public static Promotion selectStudentPromotionInfoOneResult(){
        Promotion promotion = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            promotion = session.createQuery(GET_STUDENT_PROMOTION_INFO_ONE_RESULT, Promotion.class).setParameter("studentId", studentId).getSingleResult();
            semesterPromotion = promotion.getSemestr();
            datePromotion = promotion.getStartDate();
            contentPromotion = promotion.getContent();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return promotion;
    }

    public static List<Promotion> selectStudentPromotionInfoList(){
        List<Promotion> promotion = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            promotion = session.createQuery(GET_STUDENT_PROMOTION_INFO_LIST, Promotion.class).setParameter("studentId", studentId).getResultList();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return promotion;
    }

    public static List<String> selectPromotionContentName(String name,String surname,String middleName){
        List<String> socialPassport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialPassport = session.createQuery("SELECT p.content FROM Promotion p WHERE p.studentInfo.name = :name and p.studentInfo.surname = :surname and p.studentInfo.middleName = :middleName", String.class)
                    .setParameter("name", name)
                    .setParameter("surname", surname)
                    .setParameter("middleName", middleName).getResultList();


            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return socialPassport;
    }

    public static SocialPassport selectStudentSocialPassportInfo(String categoryName){
        SocialPassport socialPassport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialPassport = session.createQuery(GET_STUDENT_SOCIAL_PASSPORT_INFO, SocialPassport.class).setParameter("studentId", studentId).setParameter("categoryName", categoryName).getSingleResult();
            //int categoryId = socialPassport.getSpCategoryName().getId();
            //SpCategoryName spCategoryName = session.createQuery(GET_SOCIAL_PASSPORT_CATEGORY_NAME_IN_TABLE, SpCategoryName.class).setParameter("categoryId", categoryId).getSingleResult();

            startDateSocialPassport = socialPassport.getStartDate();
            endDateSocialPassport = socialPassport.getEndDate();
            categorySocialPassport = socialPassport.getSpCategoryName().getCategory();
            semesterSocialPassport = socialPassport.getSemester();
            noteSocialPassport = socialPassport.getNote();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return socialPassport;
    }

    public static SocialPassport selectStudentSocialPassportInfoIfOneResult(){
        SocialPassport socialPassport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialPassport = session.createQuery(GET_STUDENT_SOCIAL_PASSPORT_INFO_LIST, SocialPassport.class).setParameter("studentId", studentId).getSingleResult();

            startDateSocialPassport = socialPassport.getStartDate();
            endDateSocialPassport = socialPassport.getEndDate();
            categorySocialPassport = socialPassport.getSpCategoryName().getCategory();
            semesterSocialPassport = socialPassport.getSemester();
            noteSocialPassport = socialPassport.getNote();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return socialPassport;
    }

    public static List<SocialPassport> selectStudentSocialPassportInfoList(){
        List<SocialPassport> socialPassport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialPassport = session.createQuery(GET_STUDENT_SOCIAL_PASSPORT_INFO_LIST, SocialPassport.class).setParameter("studentId", studentId).getResultList();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return socialPassport;
    }

    public static List<String> selectStudentSocialPassportCategory(String name,String surname,String middleName){
        List<String> socialPassport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialPassport = session.createQuery("SELECT s.spCategoryName.category FROM SocialPassport s WHERE s.studentInfo.name = :name and s.studentInfo.surname = :surname and s.studentInfo.middleName = :middleName", String.class)
                                                .setParameter("name", name)
                                                .setParameter("surname", surname)
                                                .setParameter("middleName", middleName).getResultList();


            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return socialPassport;
    }

    public static SocialPassport selectStudentInvalidPassportInfo(){
        SocialPassport socialPassport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialPassport = session.createQuery(GET_INVALID_INFO, SocialPassport.class).setParameter("studentId", studentId).getSingleResult();
            System.out.print(socialPassport.getId());
            int passportId = socialPassport.getId();
            SpInvalidPeople spInvalidPeople = session.createQuery(GET_INVALID_CATEGORY_INFO, SpInvalidPeople.class).setParameter("passportId", passportId).getSingleResult();

            startDateInvalid = socialPassport.getStartDate();
            endDateInvalid = socialPassport.getEndDate();
            semesterInvalid = socialPassport.getSemester();
            noteInvalid = socialPassport.getNote();
            categoryInvalid = spInvalidPeople.getGroup();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return socialPassport;
    }

    public static SocialPassport selectStudentManyChildrenFamilyInfo(){
        SocialPassport socialPassport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialPassport = session.createQuery(GET_MANY_CHILDREN_FAMILY , SocialPassport.class).setParameter("studentId", studentId).getSingleResult();
            System.out.print(socialPassport.getId());
            int passportId = socialPassport.getId();
            SpManyChildrenFamily spManyChildrenFamily = session.createQuery(GET_MANY_CHILDREN_FAMILY_FROM_TABLE, SpManyChildrenFamily.class).setParameter("passportId", passportId).getSingleResult();

            startDateFamily = socialPassport.getStartDate();
            endDateFamily = socialPassport.getEndDate();
            semesterFamily = socialPassport.getSemester();
            noteFamily = socialPassport.getNote();
            countChildrenFamily = spManyChildrenFamily.getCountChildren();
            lessThan18Family = spManyChildrenFamily.getLessThan18();
            muchThan18Family = spManyChildrenFamily.getMoreThan18();


            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return socialPassport;
    }

    public static List<SocialPassport> selectStudentSocialPassportInfoForExport(){
        List<SocialPassport> socialPassport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialPassport = session.createQuery(GET_STUDENT_SOCIAL_PASSPORT_INFO_LIST_FOR_EXPORT, SocialPassport.class).getResultList();

            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return socialPassport;
    }

    public static ObservableList<StudentInfo> tableGeneralInfo() {
        ObservableList<StudentInfo> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try {
            assert session != null;
            List<StudentInfo> data = session.createQuery("FROM StudentInfo ", StudentInfo.class).list();
            list.addAll(data);
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<EducationInfo> tableEducationInfo() {
        ObservableList<EducationInfo> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try {
            assert session != null;
            List<EducationInfo> data = session.createQuery("FROM EducationInfo ", EducationInfo.class).list();
            list.addAll(data);
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<MilitaryService> tableMilitaryService() {
        ObservableList<MilitaryService> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try {
            assert session != null;
            List<MilitaryService> data = session.createQuery("FROM MilitaryService ", MilitaryService.class).list();
            list.addAll(data);
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<StudentParents> tableStudentParents() {
        ObservableList<StudentParents> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try {
            assert session != null;
            List<StudentParents> data = session.createQuery("FROM StudentParents ", StudentParents.class).list();
            list.addAll(data);
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<StudentJob> tableStudentJob() {
        ObservableList<StudentJob> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try {
            assert session != null;
            List<StudentJob> data = session.createQuery("FROM StudentJob ", StudentJob.class).list();
            list.addAll(data);
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<IndividualSupport> tableIndividualSupport() {
        ObservableList<IndividualSupport> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try {
            assert session != null;
            List<IndividualSupport> data = session.createQuery("FROM IndividualSupport ", IndividualSupport.class).list();
            list.addAll(data);
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<Promotion> tablePromotion() {
        ObservableList<Promotion> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try {
            assert session != null;
            List<Promotion> data = session.createQuery("FROM Promotion ", Promotion.class).list();
            list.addAll(data);
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<CircleActivity> tableCircleActivity() {
        ObservableList<CircleActivity> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try {
            assert session != null;
            List<CircleActivity> data = session.createQuery("FROM CircleActivity ", CircleActivity.class).list();
            list.addAll(data);
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<SocialActivity> tableSocialActivity() {
        ObservableList<SocialActivity> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try {
            assert session != null;
            List<SocialActivity> data = session.createQuery("FROM SocialActivity ", SocialActivity.class).list();
            list.addAll(data);
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<SocialPassport> tableSocialPassport() {
        ObservableList<SocialPassport> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try {
            assert session != null;
            List<SocialPassport> data = session.createQuery("FROM SocialPassport ", SocialPassport.class).list();
            list.addAll(data);
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setId(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
