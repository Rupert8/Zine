package data;

import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.*;
import org.hibernate.Session;
import start.zine.StartApplication;

import java.sql.Date;
import java.time.LocalDate;


public class DeleteData extends StartApplication {


    public static void deletePlanDataById (int planId) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            WorkPlan plan = session.get(WorkPlan.class, planId);
            session.remove(plan);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {

        }
    }
    public static void deleteCurator (int curatorId,String group) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        try{
            Curators curators = session.get(Curators.class, curatorId);
            session.remove(curators);

            UpdateData.updateGroupCurator(null,group,false);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void deleteGroup(int groupId,String groupName) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        try{
            Groups groups = session.get(Groups.class, groupId);
            groups.setActive(false);

            UpdateData.updateStudentActiveStatus(groupName);
            UpdateData.updateGroupCurator(null,groupName,false);

            UpdateData.updateCuratorAfterDeleteGroup(groupName);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void deleteCategorySocialPassport(int idCategory) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        try{
            SpCategoryName spCategoryName = session.get(SpCategoryName.class, idCategory);
            session.remove(spCategoryName);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception ignored){

        }

    }

    public static void deleteStudent (int studentId) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        try{
            StudentInfo studentInfo = session.get(StudentInfo.class, studentId);
            studentInfo.setStatus(false);
            studentInfo.setRemovedDate(Date.valueOf(LocalDate.now()));

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
