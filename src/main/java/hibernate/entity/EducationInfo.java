package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.sql.Date;

@Entity
@Table(name = "student_education_info_nefk")
//@Getter @Setter
@NoArgsConstructor
public class EducationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentInfo studentInfo;

    @Column(name = "end_Date")
    private Date endDate;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "grade_avarage")
    private float gradeAvarage;

    public int getId() {
        return id;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public float getGradeAvarage() {
        return gradeAvarage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setGradeAvarage(float gradeAvarage) {
        this.gradeAvarage = gradeAvarage;
    }
}
