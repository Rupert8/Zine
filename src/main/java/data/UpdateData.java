package data;

import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.*;
import org.hibernate.Session;

import java.sql.Date;
import java.util.List;

import static controller.login.LoginController.curatorGroupName;
import static controller.curator.WorkPlanController.planId;
import static controller.admin.AdminCuratorController.curatorId;
import static controller.admin.AdminMainController.*;
import static controller.admin.AdminWorkPlanController.AdminPlanId;

public class UpdateData {
    public static void updatePlanDataById (String eventName, Date executionDate, String execution, int semester) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            WorkPlan plan = session.get(WorkPlan.class, planId);
            plan.setEventName(eventName);
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

    public static void updateGroupCurator(String curatorName,String groupName,boolean status) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            Groups groups = session.createQuery("From Groups Where groupName = :group" , Groups.class).setParameter("group", groupName).getSingleResult();
            System.out.println(groups.getGroupName());
            groups.setCurator(curatorName);
            groups.setStatus(status);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCuratorAfterDeleteGroup(String groupName) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            Curators curators = session.createQuery("From Curators Where group = :groupName", Curators.class).setParameter("groupName",groupName).getSingleResult();
            curators.setGroup(null);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCuratorGroup(String name, String surname, String middleName, String groupName) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            Curators curators = session.createQuery("From Curators Where name = :name and surname = :surname and middleName = :middleName", Curators.class)
                    .setParameter("name",name)
                    .setParameter("surname", surname)
                    .setParameter("middleName", middleName).getSingleResult();
            curators.setGroup(groupName);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCuratorDataById (String name, String surname, String middleName, String Email) {
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

            int id = SearchStudentData.getIdStudent(studentName,studentSurname,studentMiddleName,studentGroupName);

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

    public static void updateStudentMilitaryInfo(Date startDate,Date endDate,String unit){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            int id = SearchStudentData.getIdStudent(studentName,studentSurname,studentMiddleName,studentGroupName);

            int militaryInfoId = session.createQuery("SELECT m.id FROM MilitaryService m WHERE m.studentInfo.id = :id", Integer.class)
                                                        .setParameter("id", id).getSingleResult();

            MilitaryService militaryService = session.get(MilitaryService.class, militaryInfoId);
            militaryService.setStartDate(startDate);
            militaryService.setEndDate(endDate);
            militaryService.setUnit(unit);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateStudentJobInfo(Date startDate,Date endDate,String place,String position){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            int id = SearchStudentData.getIdStudent(studentName,studentSurname,studentMiddleName,studentGroupName);

            int jobInfoId = session.createQuery("SELECT j.id FROM StudentJob j WHERE j.studentInfo.id = :id", Integer.class)
                    .setParameter("id", id).getSingleResult();

            StudentJob studentJob = session.get(StudentJob.class, jobInfoId);
            studentJob.setStartDate(startDate);
            studentJob.setEndDate(endDate);
            studentJob.setPlace(place);
            studentJob.setPosition(position);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateStudentParentsInfo(String pipFather,String pipMother,String phoneFather,String phoneMother){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            int id = SearchStudentData.getIdStudent(studentName,studentSurname,studentMiddleName,studentGroupName);

            int parentsInfoId = session.createQuery("SELECT p.id FROM StudentParents p WHERE p.studentInfo.id = :id", Integer.class)
                    .setParameter("id", id).getSingleResult();

            StudentParents studentParents = session.get(StudentParents.class, parentsInfoId);
            studentParents.setFatherFullName(pipFather);
            studentParents.setMotherFullName(pipMother);
            studentParents.setPhoneFather(phoneFather);
            studentParents.setPhoneMother(phoneMother);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateStudentSocialActivityInfo(int semester,String activity,Date date){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            int id = SearchStudentData.getIdStudent(studentName,studentSurname,studentMiddleName,studentGroupName);

            int socialActivityInfoId = session.createQuery("SELECT s.id FROM SocialActivity s WHERE s.studentInfo.id = :id", Integer.class)
                    .setParameter("id", id).getSingleResult();

            SocialActivity socialActivity = session.get(SocialActivity.class, socialActivityInfoId);
            socialActivity.setActivity(activity);
            socialActivity.setDate(date);
            socialActivity.setSemestr(semester);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateStudentGroupActivityInfo(int semester,String groupName,String note){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            int id = SearchStudentData.getIdStudent(studentName,studentSurname,studentMiddleName,studentGroupName);

            int groupActivityInfoId = session.createQuery("SELECT c.id FROM CircleActivity c WHERE c.studentInfo.id = :id", Integer.class)
                    .setParameter("id", id).getSingleResult();

            CircleActivity circleActivity = session.get(CircleActivity.class, groupActivityInfoId);
            circleActivity.setSemestr(semester);
            circleActivity.setCircleName(groupName);
            circleActivity.setNote(note);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateStudentIndividualSupportInfo(int semester,Date date,String content,String tmpContent){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            int id = SearchStudentData.getIdStudent(studentName,studentSurname,studentMiddleName,studentGroupName);

            int individualSupportInfoId = session.createQuery("SELECT i.id FROM IndividualSupport i WHERE i.studentInfo.id = :id and i.content = :content", Integer.class)
                                                                 .setParameter("id", id).setParameter("content", tmpContent).getSingleResult();

            IndividualSupport individualSupport = session.get(IndividualSupport.class, individualSupportInfoId);
            individualSupport.setDate(date);
            individualSupport.setContent(content);
            individualSupport.setSemestr(semester);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateStudentPromotionInfo(int semester,Date date,String content,String tmpContent){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            int id = SearchStudentData.getIdStudent(studentName,studentSurname,studentMiddleName,studentGroupName);

            int promotionSupportInfoId = session.createQuery("SELECT p.id FROM Promotion p WHERE p.studentInfo.id = :id and p.content = :content", Integer.class)
                    .setParameter("id", id).setParameter("content", tmpContent).getSingleResult();

            Promotion promotion = session.get(Promotion.class, promotionSupportInfoId);
            promotion.setStartDate(date);
            promotion.setSemestr(semester);
            promotion.setContent(content);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateStudentSocialPassportInfo(Date startDate,Date endDate,int semester,String category,String note,String tmpCategory){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            int id = SearchStudentData.getIdStudent(studentName,studentSurname,studentMiddleName,studentGroupName);

            int socialPassportInfoId = session.createQuery("SELECT s.id FROM SocialPassport s WHERE s.studentInfo.id = :id and s.spCategoryName.category = :category", Integer.class)
                    .setParameter("id", id).setParameter("category", tmpCategory).getSingleResult();

            SocialPassport socialPassport = session.get(SocialPassport.class, socialPassportInfoId);
            socialPassport.setStartDate(startDate);
            socialPassport.setEndDate(endDate);
            socialPassport.setSemester(semester);
            socialPassport.setNote(note);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateStudentInvalidPassportInfo(Date startDate,Date endDate,int semester,String category,String note,String tmpCategory){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            int id = SearchStudentData.getIdStudent(studentName,studentSurname,studentMiddleName,studentGroupName);

            int socialPassportInfoId = session.createQuery("SELECT s.id FROM SocialPassport s WHERE s.studentInfo.id = :id and s.spCategoryName.category = :category", Integer.class)
                    .setParameter("id", id).setParameter("category", tmpCategory).getSingleResult();

            SocialPassport socialPassport = session.get(SocialPassport.class, socialPassportInfoId);
            socialPassport.setStartDate(startDate);
            socialPassport.setEndDate(endDate);
            socialPassport.setSemester(semester);
            socialPassport.setNote(note);

            SpInvalidPeople spInvalidPeople = session.createQuery("FROM SpInvalidPeople s WHERE s.socialPassport.id = :id", SpInvalidPeople.class).setParameter("id", socialPassportInfoId).getSingleResult();
            spInvalidPeople.setGroup(category);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateStudentManyChildrenFamilyPassportInfo(Date startDate,Date endDate,int semester,int countChildren,int lessThan18,int muchThan18,String note,String tmpCategory){
        try{
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            int id = SearchStudentData.getIdStudent(studentName,studentSurname,studentMiddleName,studentGroupName);

            int socialPassportInfoId = session.createQuery("SELECT s.id FROM SocialPassport s WHERE s.studentInfo.id = :id and s.spCategoryName.category = :category", Integer.class)
                    .setParameter("id", id).setParameter("category", tmpCategory).getSingleResult();

            SocialPassport socialPassport = session.get(SocialPassport.class, socialPassportInfoId);
            socialPassport.setStartDate(startDate);
            socialPassport.setEndDate(endDate);
            socialPassport.setSemester(semester);
            socialPassport.setNote(note);

            SpManyChildrenFamily spManyChildrenFamily = session.createQuery("FROM SpManyChildrenFamily s WHERE s.socialPassport.id = :id", SpManyChildrenFamily.class).setParameter("id", socialPassportInfoId).getSingleResult();
            spManyChildrenFamily.setCountChildren(countChildren);
            spManyChildrenFamily.setLessThan18(lessThan18);
            spManyChildrenFamily.setMoreThan18(muchThan18);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateStudent(String name,String surname,String middleName,String address,String phoneNumber,Date dateOfBirth,int studentId){
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            StudentInfo studentInfo = session.get(StudentInfo.class, studentId);
            studentInfo.setName(name);
            studentInfo.setSurname(surname);
            studentInfo.setMiddleName(middleName);
            studentInfo.setAddress(address);
            studentInfo.setPhoneNumber(phoneNumber);
            studentInfo.setDate_of_birth(dateOfBirth);
            studentInfo.setGroupName(curatorGroupName);

            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void updateStudentActiveStatus(String groupName) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            List<StudentInfo> studentInfoList = session.createQuery("FROM StudentInfo s WHERE s.groupName = :groupName", StudentInfo.class)
                    .setParameter("groupName", groupName)
                    .getResultList();

            for (StudentInfo student : studentInfoList) {
                student.setStatus(false);
                session.update(student);
            }

            session.getTransaction().commit();
            System.out.println("Оновлено статус для студентів у групі: " + groupName);
        } catch (Exception e) {
            if (session != null) {
                HibernateUtil.rollback(session);
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
