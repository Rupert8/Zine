package hibernate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;


@Data
@Entity
@Table(name = "student_info_nefk")
@AllArgsConstructor
@Builder
public class StudentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "studentInfo",fetch = LAZY)
    private List<CircleActivity> circleActivityList;

    @OneToOne(mappedBy = "studentInfo", cascade = CascadeType.ALL)
    private EducationInfo educationInfo;

    @OneToMany(mappedBy = "studentInfo",cascade = CascadeType.ALL,fetch = LAZY)
    private List<IndividualSupport> individualSupport;

    @OneToMany(mappedBy = "studentInfo",fetch = LAZY)
    private List<MilitaryService> militaryServiceList;

    @OneToMany(mappedBy = "studentInfo",fetch = LAZY)
    private List<StudentJob> studentJobList;

    @OneToMany(mappedBy = "studentInfo", cascade = CascadeType.ALL,fetch = LAZY)
    private List<Promotion> promotion;

    @OneToMany(mappedBy = "studentInfo",fetch = LAZY)
    private List<SocialActivity> socialActivityList;

    @OneToOne(mappedBy = "studentInfo", cascade = CascadeType.ALL)
    private StudentParents studentParents;

    @OneToOne(mappedBy = "studentInfo", cascade = CascadeType.ALL)
    private DroppedOutStudents droppedOutStudents;

    @OneToMany(mappedBy = "studentInfo",fetch = LAZY)
    private List<SocialPassport> socialPassportList;

    @OneToMany(mappedBy = "studentInfo",fetch = LAZY)
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

    public StudentInfo() {

    }

    public StudentInfo(Integer id,String name,String surname,String middleName,Date date_of_birth,String phoneNumber,String address){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.date_of_birth = date_of_birth;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getFullName(){
        return surname + " " + name + " " + middleName;
    }

    public static String getFullNameOneStudent(String surname,String name,String middleName){
        return surname + " " + name + " " + middleName;
    }


}
