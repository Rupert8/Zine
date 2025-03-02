package data;

import controller.socialActivity.GroupActivity;
import hiberante.sessionFactory.HibernateUtil;
import hibernate.entity.*;

import org.hibernate.Session;

import java.sql.Date;

import static controller.LoginController.curatorGroupName;

public class AddData {
    public static final String GET_CATEGORYNAME_ID = "SELECT s FROM SpCategoryName s WHERE s.category = :categoryName";


    public static void addPlanForCuratorData(String eventName, Date executionDate, String Performer, String execution, int semester) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            WorkPlan plan = new WorkPlan();
            plan.setEventName(eventName);
            plan.setPerformer(Performer);
            plan.setExecutionDate(executionDate);
            plan.setSemester(semester);
            plan.setCompletionNote(execution);
            plan.setConfirmationNote("Не затверджено");

            session.persist(plan);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addPlanForAdminData(String eventName, Date executionDate, String execution, int semester) {
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            WorkPlan plan = new WorkPlan();
            plan.setEventName(eventName);
            plan.setPerformer("Адміністратор");
            plan.setExecutionDate(executionDate);
            plan.setSemester(semester);
            plan.setCompletionNote(execution);
            plan.setConfirmationNote("Затверджено");

            session.persist(plan);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addEducationInfo(int id, Date endDate, String schoolName, float averageGrade) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            StudentInfo student = session.get(StudentInfo.class, id);
            EducationInfo info = new EducationInfo();
            info.setStudentInfo(student);
            info.setEndDate(endDate);
            info.setSchoolName(schoolName);
            info.setGradeAvarage(averageGrade);

            session.persist(info);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void addCuratorData(String name, String surname, String middleName, String email, String password) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            if (session != null) {
                session.beginTransaction();

                Curators curator = new Curators();
                curator.setName(name);
                curator.setSurname(surname);
                curator.setMiddleName(middleName);
                curator.setGroup(null);

                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setCurators(curator);
                user.setStatus(true);

                session.persist(user);
                session.getTransaction().commit();
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public static void addGroupInfo(String groupName, String groupProfession, String groupFormOfEducation, String groupGroupYearOfStudy, String groupLevelOfEducation, String groupEducationAndProfessionalProgram, int groupCourse) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            Groups groups = new Groups();
            groups.setGroupName(groupName);
            groups.setProfession(groupProfession);
            groups.setFormOfEducation(groupFormOfEducation);
            groups.setYearOfStudy(groupGroupYearOfStudy);
            groups.setLevelOfEducation(groupLevelOfEducation);
            groups.setEducationProgram(groupEducationAndProfessionalProgram);
            groups.setCourse(groupCourse);
            groups.setStatus(false);
            groups.setActive(true);

            session.persist(groups);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void addMilitaryInfo(int studentId, Date startDate, Date endDate, String unit) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            StudentInfo student = session.get(StudentInfo.class, studentId);
            MilitaryService militaryService = new MilitaryService();
            militaryService.setStudentInfo(student);
            militaryService.setStartDate(startDate);
            militaryService.setEndDate(endDate);
            militaryService.setUnit(unit);

            session.persist(militaryService);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void addJobInfo(int studentId, Date startDate, Date endDate, String place, String position) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            StudentInfo student = session.get(StudentInfo.class, studentId);
            StudentJob studentJob = new StudentJob();
            studentJob.setStudentInfo(student);
            studentJob.setStartDate(startDate);
            studentJob.setEndDate(endDate);
            studentJob.setPlace(place);
            studentJob.setPosition(position);

            session.persist(studentJob);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void addParentsInfo(int studentId, String pipFather, String pipMother, String phoneFather, String phoneMother) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            StudentInfo student = session.get(StudentInfo.class, studentId);
            StudentParents studentParents = new StudentParents();
            studentParents.setStudentInfo(student);
            studentParents.setFatherFullName(pipFather);
            studentParents.setMotherFullName(pipMother);
            studentParents.setPhoneFather(phoneFather);
            studentParents.setPhoneMother(phoneMother);

            session.persist(studentParents);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void addSocialActivityInfo(int studentId, int semester, Date date, String activity) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            StudentInfo student = session.get(StudentInfo.class, studentId);
            SocialActivity socialActivity = new SocialActivity();
            socialActivity.setStudentInfo(student);
            socialActivity.setActivity(activity);
            socialActivity.setDate(date);
            socialActivity.setSemestr(semester);

            session.persist(socialActivity);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void addGroupActivityInfo(int studentId, int semester, String GroupName, String note) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            StudentInfo student = session.get(StudentInfo.class, studentId);
            CircleActivity circleActivity = new CircleActivity();
            circleActivity.setStudentInfo(student);
            circleActivity.setNote(note);
            circleActivity.setSemestr(semester);
            circleActivity.setCircleName(GroupName);

            session.persist(circleActivity);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void addIndividualSupportInfo(int studentId, int semester, Date date, String content) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            StudentInfo student = session.get(StudentInfo.class, studentId);
            IndividualSupport individualSupport = new IndividualSupport();
            individualSupport.setStudentInfo(student);
            individualSupport.setSemestr(semester);
            individualSupport.setDate(date);
            individualSupport.setContent(content);

            session.persist(individualSupport);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void addPromotionInfo(int studentId, int semester, Date date, String content) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            StudentInfo student = session.get(StudentInfo.class, studentId);
            Promotion promotion = new Promotion();
            promotion.setStudentInfo(student);
            promotion.setSemestr(semester);
            promotion.setStartDate(date);
            promotion.setContent(content);

            session.persist(promotion);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void addGeneralSocialPassportInfo(int studentId, String nameCategory,int semester, Date startDate, Date endDate, String note,boolean statusAdult) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            StudentInfo student = session.get(StudentInfo.class, studentId);
            SpCategoryName spCategoryName = session.createQuery(GET_CATEGORYNAME_ID, SpCategoryName.class).setParameter("categoryName",nameCategory).getSingleResultOrNull();

            SocialPassport socialPassport = new SocialPassport();
            socialPassport.setStudentInfo(student);
            socialPassport.setSpCategoryName(spCategoryName);
            socialPassport.setSemester(semester);
            socialPassport.setStartDate(startDate);
            socialPassport.setEndDate(endDate);
            socialPassport.setNote(note);
            socialPassport.setInvalidStatus(false);
            socialPassport.setManyChildrenStatus(false);
            socialPassport.setStatusAdult(statusAdult);

            session.persist(socialPassport);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void addSocialPassportCategoryInfo(String nameCategory) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            SpCategoryName categoryName = new SpCategoryName();
            categoryName.setCategory(nameCategory);

            session.persist(categoryName);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public static void addInvalidPassportCategoryInfo(int studentId,String nameCategory,int semester,Date startDate,Date endDate,String note,boolean adultStatus) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            StudentInfo student = session.get(StudentInfo.class, studentId);

            SpCategoryName spCategoryName = session.createQuery("FROM SpCategoryName WHERE category = :name", SpCategoryName.class)
                               .setParameter("name", "Інвалід")
                               .uniqueResult();

            SocialPassport socialPassport = new SocialPassport();

            socialPassport.setSpCategoryName(spCategoryName);
            socialPassport.setStudentInfo(student);
            socialPassport.setStartDate(startDate);
            socialPassport.setEndDate(endDate);
            socialPassport.setNote(note);
            socialPassport.setSemester(semester);
            socialPassport.setInvalidStatus(true);
            socialPassport.setStatusAdult(adultStatus);

            session.persist(socialPassport);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
            addInvalidSocialPassport(studentId,nameCategory);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    private static void addInvalidSocialPassport(int studentId,String nameCategory) {
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            SpInvalidPeople spInvalidPeople = new SpInvalidPeople();
            int socialPassportId = SearchStudentData.getIdInvalidSocialPassport(studentId);
            SocialPassport socialPassportObject = session.get(SocialPassport.class, socialPassportId);
            spInvalidPeople.setGroup(nameCategory);
            spInvalidPeople.setSocialPassport(socialPassportObject);

            session.persist(spInvalidPeople);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void addManyChildrenPassportInfo(int studentId,int semester,Date startDate,Date endDate,String note,int countChildren,int lessThan18,int muchThan18,boolean adultStatus) {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();

            StudentInfo student = session.get(StudentInfo.class, studentId);

            SpCategoryName spCategoryName = session.createQuery("FROM SpCategoryName WHERE category = :name", SpCategoryName.class)
                    .setParameter("name", "Багатодітна родина")
                    .uniqueResult();

            SocialPassport socialPassport = new SocialPassport();

            socialPassport.setSpCategoryName(spCategoryName);
            socialPassport.setStudentInfo(student);
            socialPassport.setStartDate(startDate);
            socialPassport.setEndDate(endDate);
            socialPassport.setNote(note);
            socialPassport.setSemester(semester);
            socialPassport.setInvalidStatus(false);
            socialPassport.setManyChildrenStatus(true);
            socialPassport.setStatusAdult(adultStatus);

            session.persist(socialPassport);
            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
            addManyChildrenSocialPassport(studentId,countChildren,lessThan18,muchThan18);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    private static void addManyChildrenSocialPassport(int studentId,int countChildren,int lessThan18,int muchThan18) {
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            SpManyChildrenFamily spManyChildrenFamily = new SpManyChildrenFamily();
            int socialPassportId = SearchStudentData.getIdManyChildrenSocialPassport(studentId);
            SocialPassport socialPassportObject = session.get(SocialPassport.class, socialPassportId);
            spManyChildrenFamily.setCountChildren(countChildren);
            spManyChildrenFamily.setLessThan18(lessThan18);
            spManyChildrenFamily.setMoreThan18(muchThan18);
            spManyChildrenFamily.setSocialPassport(socialPassportObject);

            session.persist(spManyChildrenFamily);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void addStudent(String name,String surname,String middleName,String address,String phoneNumber,Date dateOfBirth){
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();

            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setName(name);
            studentInfo.setSurname(surname);
            studentInfo.setMiddleName(middleName);
            studentInfo.setAddress(address);
            studentInfo.setPhoneNumber(phoneNumber);
            studentInfo.setDate_of_birth(dateOfBirth);
            studentInfo.setGroupName(curatorGroupName);
            studentInfo.setStatus(true);


            session.persist(studentInfo);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}