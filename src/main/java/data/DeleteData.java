package data;

import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.Curators;
import hibernate.entity.Groups;
import hibernate.entity.WorkPlan;
import org.hibernate.Session;

import javax.swing.*;

public class DeleteData {


    public static void deletePlanDataById (int planId) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            WorkPlan plan = session.get(WorkPlan.class, planId);
            session.remove(plan);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteCurator (int curatorId) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        try{
            Curators curators = session.get(Curators.class, curatorId);
            session.remove(curators);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void deleteGroup (int curatorId) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        try{
            Groups groups = session.get(Groups.class, curatorId);
            session.remove(groups);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
