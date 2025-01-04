package hiberante.sessionFactory;

import hibernate.entity.*;
import lombok.Getter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;


public class HibernateUtil {
    @Getter
    private static SessionFactory factory;

    static {
        try{
            Configuration configuration = new Configuration().configure(new File("src\\main\\resources\\hibernate.xml\\hibernate.cfg.xml"));
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(StudentInfo.class);
            configuration.addAnnotatedClass(WorkPlan.class);
            configuration.addAnnotatedClass(EducationInfo.class);
            configuration.addAnnotatedClass(MilitaryService.class);
            configuration.addAnnotatedClass(StudentJob.class);
            configuration.addAnnotatedClass(StudentParents.class);
            configuration.addAnnotatedClass(SocialActivity.class);
            configuration.addAnnotatedClass(CircleActivity.class);
            configuration.addAnnotatedClass(Promotion.class);
            configuration.addAnnotatedClass(IndividualSupport.class);
            configuration.addAnnotatedClass(DroppedOutStudents.class);
            configuration.addAnnotatedClass(SocialPassport.class);
            configuration.addAnnotatedClass(SpCategoryName.class);
            configuration.addAnnotatedClass(SpChornobiltsi.class);
            configuration.addAnnotatedClass(SpInvalidPeople.class);
            configuration.addAnnotatedClass(SpManyChildrenFamily.class);
            configuration.addAnnotatedClass(Groups.class);
            configuration.addAnnotatedClass(Curators.class);
            configuration.addAnnotatedClass(StudentGroups.class);

            factory = configuration.buildSessionFactory();
        }catch (HibernateException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Session getSession() {
        if(factory != null){
            return factory.openSession();
        }else {
            return null;
        }
    }

    public static void closeSession(Session session) {
        if(session != null){
            session.close();
        }
    }

    public static void closeFactory(Session session) {
        if(factory != null){
            factory.close();
        }
    }

    public static void rollback(Session session) {
        if(session.getTransaction().isActive()){
            session.getTransaction().rollback();
        }
    }


}
