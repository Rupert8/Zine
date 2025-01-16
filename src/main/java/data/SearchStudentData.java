package data;

import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.StudentInfo;
import hibernate.entity.User;
import org.hibernate.Session;

public class SearchStudentData {
    private static final String SELECT_STUDENT_FOR_ADD_ID = "SELECT id FROM StudentInfo WHERE name = :name and surname = :surname and middleName = :middleName";
    private static Session session;
    private static final String SELECT_STUDENT_ID = "SELECT id FROM StudentInfo WHERE phoneNumber = :phoneNumber AND address = :address";
    private static final String SELECT_SOCIAL_INVALID_PASSPORT_ID = "SELECT с.id FROM SocialPassport с WHERE с.studentInfo.id = :studentId AND invalidStatus = true";
    private static final String SELECT_SOCIAL_MANY_CHILDREN_PASSPORT_ID = "SELECT с.id FROM SocialPassport с WHERE с.studentInfo.id = :studentId AND с.manyChildrenStatus = true";

    private static final String SELECT_WORK_PLAN_ID = "SELECT id FROM WorkPlan WHERE eventName = :eventName AND performer = :performer";
    private static final String SELECT_CURATOR_ID = "SELECT id FROM Curators WHERE group = :groupName";
    private static final String SELECT_GROUP_ID = "SELECT id FROM Groups WHERE curator = :curatorName";

    private static final String SELECT_USER_ID = "SELECT u.id FROM User u WHERE u.Email = :userEmail";

    public static int getIdStudent(String name,String surname,String middleName){
        int id = 0;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            id = session.createQuery(SELECT_STUDENT_FOR_ADD_ID, Integer.class)
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

    public static int getIdStudentForAdmin(String address,String phoneNumber){
        int id = 0;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            id = session.createQuery(SELECT_STUDENT_ID, Integer.class)
                    .setParameter("phoneNumber", phoneNumber)
                    .setParameter("address", address).getSingleResult();

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


    public static int getIdWorkPlan(String eventName,String performer){
        int id = 0;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            id = session.createQuery(SELECT_WORK_PLAN_ID, Integer.class)
                    .setParameter("eventName", eventName)
                    .setParameter("performer" , performer).getSingleResult();
            session.getTransaction().commit();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally {
            HibernateUtil.rollback(session);
        }

        return id;
    }

    public static int getIdGroup(String curator){
        int id = 0;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            id = session.createQuery(SELECT_GROUP_ID, Integer.class)
                    .setParameter("curatorName" , curator).getSingleResult();

            session.getTransaction().commit();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally {
            HibernateUtil.rollback(session);
        }

        return id;
    }

    public static int getIdCurator(String groupName){
        int id = 0;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            id = session.createQuery(SELECT_CURATOR_ID, Integer.class)
                    .setParameter("groupName" , groupName).getSingleResult();
            session.getTransaction().commit();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally {
            HibernateUtil.rollback(session);
        }

        return id;
    }

    public static long getIdUser(String userEmail){
        long id = 0;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            id = session.createQuery(SELECT_USER_ID, Long.class)
                    .setParameter("userEmail" , userEmail).getSingleResult();

            session.getTransaction().commit();
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return id;
    }
}
