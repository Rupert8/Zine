package hibernate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "student_info_nefk")
//@Getter @Setter
//@NoArgsConstructor
@AllArgsConstructor
public class StudentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "studentInfo",fetch = FetchType.LAZY)
    private List<CircleActivity> circleActivityList;

    @OneToOne(mappedBy = "studentInfo", cascade = CascadeType.ALL)
    private EducationInfo educationInfo;

    @OneToMany(mappedBy = "studentInfo",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<IndividualSupport> individualSupport;

    @OneToMany(mappedBy = "studentInfo",fetch = FetchType.LAZY)
    private List<MilitaryService> militaryServiceList;

    @OneToMany(mappedBy = "studentInfo",fetch = FetchType.LAZY)
    private List<StudentJob> studentJobList;

    @OneToMany(mappedBy = "studentInfo", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Promotion> promotion;

    @OneToMany(mappedBy = "studentInfo",fetch = FetchType.LAZY)
    private List<SocialActivity> socialActivityList;

    @OneToOne(mappedBy = "studentInfo", cascade = CascadeType.ALL)
    private StudentParents studentParents;

    @OneToOne(mappedBy = "studentInfo", cascade = CascadeType.ALL)
    private DroppedOutStudents droppedOutStudents;

    @OneToMany(mappedBy = "studentInfo",fetch = FetchType.LAZY)
    private List<SocialPassport> socialPassportList;

    @OneToMany(mappedBy = "studentInfo",fetch = FetchType.LAZY)
    private List<StudentGroups> socialGroupList;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "MiddleName")
    private String middleName;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "Date_of_birth")
    private Date date_of_birth;

    @Column(name = "Address")
    private String address;

    @Column(name = "groupName")
    private String groupName;

    @Column(name = "is_active")
    private boolean status;

    @Column(name = "removed_Date")
    private Date removedDate;

    private StudentInfo(String name, String surname, String middleName) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
    }

    public StudentInfo() {

    }

    public String getFullName(){
        return surname + " " + name + " " + middleName;
    }

    public static String getFullNameOneStudent(String surname,String name,String middleName){
        return surname + " " + name + " " + middleName;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public String getGroupName() {
        return groupName;
    }

    public boolean isStatus() {
        return status;
    }

    public Date getRemovedDate() {
        return removedDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setRemovedDate(Date removedDate) {
        this.removedDate = removedDate;
    }
}
