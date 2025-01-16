package data;

import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.*;
import org.hibernate.Session;

import javax.swing.*;
import java.sql.Date;

import static controller.WorkPlanController.planId;
import static controller.admin.AdminCuratorController.curatorId;
import static controller.admin.AdminMainController.*;
import static controller.admin.AdminWorkPlanController.AdminPlanId;

public class UpdateData {
    public static void updatePlanDataById (String eventName, Date executionDate, String Performer, String execution, int semester) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            WorkPlan plan = session.get(WorkPlan.class, planId);
            plan.setEventName(eventName);
            plan.setPerformer(Performer);
            plan.setExecutionDate(executionDate);
            plan.setSemester(semester);
            plan.setCompletionNote(execution);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateAdminPlanDataById (String eventName, Date executionDate,String confirmationNote, String execution, int semester) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            WorkPlan plan = session.get(WorkPlan.class, AdminPlanId);
            plan.setEventName(eventName);
            plan.setExecutionDate(executionDate);
            plan.setSemester(semester);
            plan.setConfirmationNote(confirmationNote);
            plan.setCompletionNote(execution);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateGroupCurator(String curatorName,String groupName) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            Groups groups = session.createQuery("From Groups Where groupName = :group" , Groups.class).setParameter("group", groupName).getSingleResult();
            System.out.println(groups.getGroupName());
            groups.setCurator(curatorName);
            groups.setStatus(true);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCuratorDataById (String name, String surname, String middleName, String groupName, String Email) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            Curators curators = session.get(Curators.class, curatorId);
            User user = curators.getUser();
            curators.setName(name);
            curators.setSurname(surname);
            curators.setMiddleName(middleName);
            curators.setEmail(Email);
            user.setEmail(Email);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateGroupDataById(int groupId, String groupName,String profession,String educationAndProfession,String levelOfEducation,int Course,String yearOfStudy,String FormOfEducation){
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            Groups groups = session.get(Groups.class, groupId);
            groups.setGroupName(groupName);
            groups.setProfession(profession);
            groups.setEducationProgram(educationAndProfession);
            groups.setLevelOfEducation(levelOfEducation);
            groups.setYearOfStudy(yearOfStudy);
            groups.setFormOfEducation(FormOfEducation);


            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUserPassword(String userEmail,String newPassword){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            long id = SearchStudentData.getIdUser(userEmail);
            System.out.println(id);
            User user = session.get(User.class, id);
            user.setPassword(newPassword);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateStudentEducationInfo(Date enddate,String schoolName,float gradeAverage){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            int id = SearchStudentData.getIdStudent(studentName,studentSurname,studentMiddleName);

            int educationInfoId = session.createQuery("SELECT e.id FROM EducationInfo e WHERE e.studentInfo.id = :id", Integer.class)
                                                             .setParameter("id", id).getSingleResult();

            EducationInfo educationInfo = session.get(EducationInfo.class, educationInfoId);
            educationInfo.setEndDate(enddate);
            educationInfo.setSchoolName(schoolName);
            educationInfo.setGradeAvarage(gradeAverage);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
