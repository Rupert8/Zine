package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "student_education_info")
@Getter
@Setter
@NoArgsConstructor
public class EducationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentInfo studentInfo;

    @Column(name = "end_Date")
    private Date endDate;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "grade_avarage")
    private float gradeAvarage;

}
