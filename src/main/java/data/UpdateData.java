package data;

import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.Curators;
import hibernate.entity.Groups;
import hibernate.entity.User;
import hibernate.entity.WorkPlan;
import org.hibernate.Session;

import javax.swing.*;
import java.sql.Date;

import static controller.WorkPlanController.planId;
import static controller.admin.AdminCuratorController.curatorId;

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

    public static void updateGroupDataById(int groupId, String groupName,String curator,String profession,String educationAndProfession,String levelOfEducation,int Course,String yearOfStudy,String FormOfEducation){
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            Groups groups = session.get(Groups.class, groupId);
            groups.setGroupName(groupName);
            groups.setCurator(curator);
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
}
