package hibernate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.ConstructorParameters;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "student_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "studentInfo")
    private List<CircleActivity> circleActivityList;

    @OneToOne(mappedBy = "studentInfo", cascade = CascadeType.ALL)
    private EducationInfo educationInfo;

    @OneToOne(mappedBy = "studentInfo",cascade = CascadeType.ALL)
    private IndividualSupport individualSupport;

    @OneToMany(mappedBy = "studentInfo")
    private List<MilitaryService> militaryServiceList;

    @OneToMany(mappedBy = "studentInfo")
    private List<StudentJob> studentJobList;

    @OneToOne(mappedBy = "studentInfo", cascade = CascadeType.ALL)
    private Promotion promotion;

    @OneToMany(mappedBy = "studentInfo")
    private List<SocialActivity> socialActivityList;

    @OneToOne(mappedBy = "studentInfo", cascade = CascadeType.ALL)
    private StudentParents studentParents;

    @OneToOne(mappedBy = "studentInfo", cascade = CascadeType.ALL)
    private DroppedOutStudents droppedOutStudents;

    @OneToMany(mappedBy = "studentInfo")
    private List<SocialPassport> socialPassportList;

    @OneToMany(mappedBy = "studentInfo")
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

    private StudentInfo(String name, String surname, String middleName) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
    }

    public String getFullName(){
        return surname + " " + name + " " + middleName;
    }

    public static String getFullNameOneStudent(String surname,String name,String middleName){
        return surname + " " + name + " " + middleName;
    }


}
