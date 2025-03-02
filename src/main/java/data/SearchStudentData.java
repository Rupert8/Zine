package data;

import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.StudentInfo;
import hibernate.entity.User;
import org.hibernate.Session;

import static controller.WorkPlanController.performerNameForAdd;

public class SearchStudentData {
    private static final String SELECT_STUDENT_FOR_ADD_ID = "SELECT id FROM StudentInfo WHERE name = :name and surname = :surname and middleName = :middleName and groupName = :groupName";
    private static Session session;
    private static final String SELECT_STUDENT_ID = "SELECT id FROM StudentInfo WHERE phoneNumber = :phoneNumber AND address = :address";
    private static final String SELECT_SOCIAL_INVALID_PASSPORT_ID = "SELECT с.id FROM SocialPassport с WHERE с.studentInfo.id = :studentId AND invalidStatus = true";
    private static final String SELECT_SOCIAL_MANY_CHILDREN_PASSPORT_ID = "SELECT с.id FROM SocialPassport с WHERE с.studentInfo.id = :studentId AND с.manyChildrenStatus = true";

    private static final String SELECT_WORK_PLAN_ID = "SELECT id FROM WorkPlan WHERE eventName = :eventName AND performer = :performer";
    private static final String SELECT_CURATOR_ID = "SELECT id FROM Curators WHERE user.Email = :email";
    private static final String SELECT_GROUP_ID = "SELECT id FROM Groups WHERE groupName = :groupName";

    private static final String SELECT_USER_ID = "SELECT u.id FROM User u WHERE u.Email = :userEmail";

    public static int getIdStudent(String name,String surname,String middleName,String groupName){
        int id = 0;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            id = session.createQuery(SELECT_STUDENT_FOR_ADD_ID, Integer.class)
                    .setParameter("name", name)
                    .setParameter("middleName", middleName)
                    .setParameter("surname", surname)
                    .setParameter("groupName", groupName).getSingleResult();

            session.getTransaction().commit();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally {
            HibernateUtil.rollback(session);
        }

        return id;
    }

    public static int getIdCategorySocialPassport(String category){
        int id = 0;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            id = session.createQuery("SELECT s.id FROM SpCategoryName s Where s.category = :category", Integer.class)
                    .setParameter("category", category).getSingleResult();

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

    public static int getIdGroup(String groupName){
        int id = 0;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            id = session.createQuery(SELECT_GROUP_ID, Integer.class)
                    .setParameter("groupName" , groupName).getSingleResult();

            session.getTransaction().commit();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally {
            HibernateUtil.rollback(session);
        }

        return id;
    }

    public static int getIdCurator(String email){
        int id = 0;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            id = session.createQuery(SELECT_CURATOR_ID, Integer.class)
                    .setParameter("email" , email).getSingleResult();
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

    public static boolean validateUserEmail(String userEmail){
        boolean result = false;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String email = session.createQuery("SELECT u.Email FROM User u WHERE u.Email = :email", String.class).setParameter("email", userEmail).getSingleResult();

            if(email != null){
                result = true;
            }else{
                result = false;
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return result;
    }

    public static boolean validateGroupName(String groupName){
        boolean result = false;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String group = session.createQuery("SELECT g.groupName FROM Groups g WHERE g.groupName = :groupName", String.class).setParameter("groupName", groupName).getSingleResult();

            if(group != null){
                result = true;
            }else{
                result = false;
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return result;
    }

    public static boolean validateSocialPassportCategoryName(String categoryName){
        boolean result = false;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String category = session.createQuery("SELECT s.category FROM SpCategoryName s WHERE s.category = :categoryName", String.class).setParameter("categoryName", categoryName).getSingleResult();

            if(category != null){
                result = true;
            }else{
                result = false;
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return result;
    }

    public static boolean validateCuratorEventName(String eventName){
        boolean result = false;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String name = session.createQuery("SELECT w.eventName FROM WorkPlan  w WHERE w.eventName = :eventName and w.performer = :performer", String.class).setParameter("eventName", eventName).setParameter("performer", performerNameForAdd).getSingleResult();

            if(name != null){
                result = true;
            }else{
                result = false;
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return result;
    }

    public static boolean validateStudentExist(String phoneNumber){
        boolean result = false;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String phone = session.createQuery("SELECT s.phoneNumber FROM StudentInfo s WHERE s.phoneNumber = :phoneNumber", String.class).setParameter("phoneNumber", phoneNumber).getSingleResult();

            if(phone != null){
                result = true;
            }else{
                result = false;
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return result;
    }

    public static boolean validateStudentEducationData(String name,String surName,String middleName){
        boolean result = false;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String education = session.createQuery("SELECT e.schoolName FROM EducationInfo e WHERE e.studentInfo.name = :name and e.studentInfo.surname = :surName and e.studentInfo.middleName = :middleName", String.class)
                                                        .setParameter("name", name)
                                                        .setParameter("surName", surName)
                                                        .setParameter("middleName", middleName).getSingleResult();

            if(education  != null){
                result = true;
            }else{
                result = false;
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return result;
    }

    public static boolean validateStudentMilitary(String name,String surName,String middleName){
        boolean result = false;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String military = session.createQuery("SELECT m.unit FROM MilitaryService m WHERE m.studentInfo.name = :name and m.studentInfo.surname = :surName and m.studentInfo.middleName = :middleName", String.class)
                    .setParameter("name", name)
                    .setParameter("surName", surName)
                    .setParameter("middleName", middleName).getSingleResult();

            if(military  != null){
                result = true;
            }else{
                result = false;
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return result;
    }

    public static boolean validateStudentParents(String name,String surName,String middleName){
        boolean result = false;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String studentParents = session.createQuery("SELECT s.fatherFullName FROM StudentParents s WHERE s.studentInfo.name = :name and s.studentInfo.surname = :surName and s.studentInfo.middleName = :middleName", String.class)
                    .setParameter("name", name)
                    .setParameter("surName", surName)
                    .setParameter("middleName", middleName).getSingleResult();

            if(studentParents  != null){
                result = true;
            }else{
                result = false;
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return result;
    }

    public static boolean validateInvalid(String name,String surName,String middleName,String groupName){
        boolean result = false;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String invalid = session.createQuery("SELECT s.spCategoryName.category FROM SocialPassport s WHERE s.studentInfo.name = :name and s.studentInfo.surname = :surName and s.studentInfo.middleName = :middleName and s.studentInfo.groupName = :groupName and s.spCategoryName.category = 'Інвалід'", String.class)
                    .setParameter("name", name)
                    .setParameter("surName", surName)
                    .setParameter("middleName", middleName)
                    .setParameter("groupName", groupName).getSingleResult();

            if(invalid  != null){
                result = true;
            }else{
                result = false;
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return result;
    }

    public static boolean validateManyChildrenFamily(String name,String surName,String middleName,String groupName){
        boolean result = false;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            String family = session.createQuery("SELECT s.spCategoryName.category FROM SocialPassport s WHERE s.studentInfo.name = :name and s.studentInfo.surname = :surName and s.studentInfo.middleName = :middleName and s.studentInfo.groupName = :groupName and s.spCategoryName.category = 'Багатодітна родина'", String.class)
                    .setParameter("name", name)
                    .setParameter("surName", surName)
                    .setParameter("middleName", middleName)
                    .setParameter("groupName", groupName).getSingleResult();

            if(family  != null){
                result = true;
            }else{
                result = false;
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return result;
    }

}
