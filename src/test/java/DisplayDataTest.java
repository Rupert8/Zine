import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.Curators;
import hibernate.entity.StudentInfo;
import hibernate.entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.*;

import java.util.List;

public class DisplayDataTest {
    private Session session = null;

    @Test
    public void getStudentInfoTest() {
        ObservableList<StudentInfo> studentInfo = FXCollections.observableArrayList();
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<StudentInfo> resultList = session.createQuery("SELECT s.id, s.name,s.surname,s.middleName,s.date_of_birth,s.phoneNumber,s.address,s.groupName FROM StudentInfo s WHERE status = true", StudentInfo.class).getResultList();
            studentInfo.addAll(resultList);

            for (int i = 0; i < resultList.size(); i++) {
                resultList.get(i).setId(i + 1);
            }
            System.out.println(studentInfo);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.rollback(session);
        }
    }
    }

