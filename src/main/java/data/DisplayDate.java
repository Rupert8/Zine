package data;
import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import tableView.SocialPassportCategoryPrototype;
import tableView.SocialPassportPrototype;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static controller.ExtendedInformationAboutStudent.*;
import static controller.admin.AdminGroupController.*;

public class DisplayDate  {
    public static final String GET_CURATOR_AND_EMAIL = "SELECT c.id,c.name,c.surname,c.middleName,c.group, u.Email\n" +
                                                        "FROM Curators c\n" +
                                                        "LEFT JOIN User u ON c.id = u.curators.id";

    public static final String GET_SOCIAL_PASSPORT_INFO = "SELECT s.id,s.studentInfo.name,s.studentInfo.surname,s.studentInfo.middleName,c.category,s.semester\n" +
                                                          "FROM SocialPassport s\n" +
                                                          "INNER JOIN SpCategoryName c on s.spCategoryName.id = c.id";

    public static final String GET_INVALID_INFO = "SELECT s\n" +
                                                  "FROM SocialPassport s\n" +
                                                  "WHERE s.studentInfo.id = :studentId  And s.invalidStatus = true";

    public static final String GET_MANY_CHILDREN_FAMILY = "SELECT s FROM SocialPassport s Where s.studentInfo.id = :studentId AND s.manyChildrenStatus = true";
    public static final String GET_MANY_CHILDREN_FAMILY_FROM_TABLE = "SELECT m FROM SpManyChildrenFamily m Where m.socialPassport.id = :passportId";
    public static final String GET_INVALID_CATEGORY_INFO = "SELECT i FROM SpInvalidPeople i Where i.socialPassport.id = :passportId ";

    public static final String GET_SOCIAL_PASSPORT_CATEGORY_NAME = "SELECT c.category FROM SpCategoryName c";
    public static final String GET_SOCIAL_PASSPORT_CATEGORY_NAME_IN_TABLE = "SELECT c FROM SpCategoryName c Where c.id = :categoryId ";

    public static final String GET_STUDENT_CATEGORY = "SELECT c.category FROM SpCategoryName c";
    public static final String GET_GROUP_INFO = "SELECT id,groupName,curator,profession FROM Groups";
    public static final String GET_CURATOR_NAME = "SELECT CONCAT(name, ' ', surname, ' ', middleName) FROM Curators";
    public static final String GET_STUDENT_EDUCATION_INFO = "FROM EducationInfo WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_MILITARY_INFO = "FROM MilitaryService WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_JOB_INFO = "FROM StudentJob WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_PARENTS_INFO = "FROM StudentParents WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_SOCIAL_INFO = "FROM SocialActivity WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_GROUP_INFO = "FROM CircleActivity WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_INDIVIDUAL_SUPPORT_INFO = "FROM IndividualSupport WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_PROMOTION_INFO = "FROM Promotion WHERE studentInfo.id = :studentId";
    public static final String GET_STUDENT_SOCIAL_PASSPORT_INFO = "FROM SocialPassport s WHERE s.studentInfo.id = :studentId and s.invalidStatus = false";

    public static Session session = null;


    public static ObservableList<StudentInfo> getDataStudentInfo(String hqlQuery,String groupName) {
        ObservableList<StudentInfo> studentInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<StudentInfo> resoultlist = session.createQuery(hqlQuery, StudentInfo.class).setParameter("groupName", groupName).getResultList();
            studentInfo.addAll(resoultlist);

            session.getTransaction().commit();
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

            List<StudentInfo> resultList = session.createQuery("FROM StudentInfo", StudentInfo.class).getResultList();
            studentInfo.addAll(resultList);
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
            List<SocialPassportCategoryPrototype> resultList = session.createQuery(GET_SOCIAL_PASSPORT_CATEGORY_NAME, SocialPassportCategoryPrototype.class).getResultList();
            for (SocialPassportCategoryPrototype category : resultList) {
                nameCategory.add(category.getNameCategory());
            }
        } finally {
            session.close();
        }
        return nameCategory;
    }


    public static ObservableList<WorkPlan> getDataWithParametrPlanInfo(Date startDate, Date endDate,int semester) {
        String hql = "FROM WorkPlan WHERE executionDate BETWEEN :startDate AND :endDate AND semester = :semester" ;
        ObservableList<WorkPlan> planInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<WorkPlan> resoultlist = session.createQuery(hql, WorkPlan.class).setParameter("startDate", startDate).setParameter("endDate", endDate).setParameter("semester", semester).getResultList();
            planInfo.addAll(resoultlist);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
        return planInfo;
    }

    public static ObservableList<Integer> getSemesterPlanInfo() {
        String hql = "SELECT semester FROM WorkPlan";
        ObservableList<Integer> planInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<Integer> resoultlist = session.createQuery(hql, Integer.class).getResultList();
            planInfo.addAll(resoultlist);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
        return planInfo;
    }

    public static ObservableList<WorkPlan> getDataBySemesterPlanInfo(int semester) {
        String hql = "FROM WorkPlan WHERE semester = :semester";
        ObservableList<WorkPlan> planInfo = FXCollections.observableArrayList();
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<WorkPlan> resoultlist = session.createQuery(hql, WorkPlan.class).setParameter("semester",semester).getResultList();
            planInfo.addAll(resoultlist);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
        return planInfo;
    }

    public static List<String> getStudentFullNames() {
        List<String> studentNames = new ArrayList<>();
        session = HibernateUtil.getSession();
        try {
            List<StudentInfo> students = session.createQuery("FROM StudentInfo", StudentInfo.class).list();
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
            List<String> students = session.createQuery("SELECT groupName FROM Groups", String.class).list();
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return groupNames;
    }

    public static ObservableList<WorkPlan> getFullPlanInfo() {
        ObservableList<WorkPlan> planNames = FXCollections.observableArrayList();
        session = HibernateUtil.getSession();
        try{
            List<WorkPlan> workPlan = session.createQuery("FROM WorkPlan", WorkPlan.class).getResultList();
            planNames.addAll(workPlan);
        }catch (Exception e){
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

    public static StudentJob selectStudentJobInfo(){
        StudentJob studentJob = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            studentJob = session.createQuery(GET_STUDENT_JOB_INFO, StudentJob.class).setParameter("studentId", studentId).getSingleResult();
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

    public static SocialActivity selectStudentSocialInfo(){
        SocialActivity socialActivity = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialActivity= session.createQuery(GET_STUDENT_SOCIAL_INFO, SocialActivity.class).setParameter("studentId", studentId).getSingleResult();
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

    public static CircleActivity selectStudentGroupActivityInfo(){
        CircleActivity circleActivity = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            circleActivity = session.createQuery(GET_STUDENT_GROUP_INFO, CircleActivity.class).setParameter("studentId", studentId).getSingleResult();
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

    public static IndividualSupport selectStudentIndividualSupportInfo(){
        IndividualSupport individualSupport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            individualSupport = session.createQuery(GET_STUDENT_INDIVIDUAL_SUPPORT_INFO, IndividualSupport.class).setParameter("studentId", studentId).getSingleResult();
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

    public static Promotion selectStudentPromotionInfo(){
        Promotion promotion = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            promotion = session.createQuery(GET_STUDENT_PROMOTION_INFO, Promotion.class).setParameter("studentId", studentId).getSingleResult();
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

    public static SocialPassport selectStudentSocialPassportInfo(){
        SocialPassport socialPassport = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            socialPassport = session.createQuery(GET_STUDENT_SOCIAL_PASSPORT_INFO, SocialPassport.class).setParameter("studentId", studentId).getSingleResult();
            int categoryId = socialPassport.getSpCategoryName().getId();
            SpCategoryName spCategoryName = session.createQuery(GET_SOCIAL_PASSPORT_CATEGORY_NAME_IN_TABLE, SpCategoryName.class).setParameter("categoryId", categoryId).getSingleResult();

            startDateSocialPassport = socialPassport.getStartDate();
            endDateSocialPassport = socialPassport.getEndDate();
            categorySocialPassport = spCategoryName.getCategory();
            semesterSocialPassport = socialPassport.getSemester();
            noteSocialPassport = socialPassport.getNote();

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
}
