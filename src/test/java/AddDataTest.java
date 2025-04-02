import data.AddData;
import enums.UserStatus;
import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.Curators;
import hibernate.entity.User;
import javafx.fxml.FXML;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class AddDataTest {
    private static SessionFactory sessionFactory = null;
    private Session session = null;

    @BeforeAll
    static void setup(){
        try {
            StandardServiceRegistry standardRegistry  = new StandardServiceRegistryBuilder()
                    .configure("hibernate-test.cfg.xml").build();

            Metadata metadata = new MetadataSources(standardRegistry)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Curators.class)
                    .getMetadataBuilder()
                    .build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    @BeforeEach
    void setupThis(){
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    void tearThis(){
        session.getTransaction().commit();
    }

    @AfterAll
    static void tear(){
        sessionFactory.close();
    }

//    @Test
//    public void addAdminInUserTable() {
//        try(Session session = HibernateUtil.getSession()) {
//            if (session == null) {
//                throw new IllegalStateException("Session is null");
//            }
//            session.beginTransaction();
//
//            User user = new User();
//            user.setEmail("Admin");
//            user.setPassword("1111");
//            user.setStatus(UserStatus.ADMIN);
//
//            session.persist(user);
//            session.getTransaction().commit();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void addDataTest(){

    }

}
