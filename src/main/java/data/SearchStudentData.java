package data;

import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.StudentInfo;
import org.hibernate.Session;

public class SearchStudentData {
    private static Session session;
    private static final String SELECT_STUDENT_ID = "SELECT id FROM StudentInfo WHERE name = :name AND surname = :surname AND middleName = :middleName";
    private static final String SELECT_SOCIAL_INVALID_PASSPORT_ID = "SELECT с.id FROM SocialPassport с WHERE с.studentInfo.id = :studentId AND invalidStatus = true";
    private static final String SELECT_SOCIAL_MANY_CHILDREN_PASSPORT_ID = "SELECT с.id FROM SocialPassport с WHERE с.studentInfo.id = :studentId AND с.manyChildrenStatus = true";

    public static int getIdStudent(String name,String surname,String middleName){
        int id = 0;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            id = session.createQuery(SELECT_STUDENT_ID, Integer.class)
                    .setParameter("name", name)
                    .setParameter("middleName", middleName)
                    .setParameter("surname", surname).getSingleResult();

            session.getTransaction().commit();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally {
            HibernateUtil.rollback(session);
        }

        return id;
    }

    public static int getIdInvalidSocialPassport(int studentId){
        int id = 0;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            id = session.createQuery(SELECT_SOCIAL_INVALID_PASSPORT_ID, Integer.class)
                    .setParameter("studentId", studentId).getSingleResult();

            session.getTransaction().commit();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally {
            HibernateUtil.rollback(session);
        }

        return id;
    }

    public static int getIdManyChildrenSocialPassport(int studentId){
        int id = 0;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            id = session.createQuery(SELECT_SOCIAL_MANY_CHILDREN_PASSPORT_ID, Integer.class)
                    .setParameter("studentId", studentId).getSingleResult();

            session.getTransaction().commit();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally {
            HibernateUtil.rollback(session);
        }

        return id;
    }
}
