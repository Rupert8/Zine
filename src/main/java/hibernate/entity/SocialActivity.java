package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "social_activity_nefk")
//@Getter @Setter
@ToString(exclude = "studentInfo")
@NoArgsConstructor
public class SocialActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentInfo studentInfo;

    @Column(name = "semestr")
    private int semestr;

    @Column(name = "date")
    private Date date;

    @Column(name = "activity")
    private String activity;

    public int getId() {
        return id;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public int getSemestr() {
        return semestr;
    }

    public Date getDate() {
        return date;
    }

    public String getActivity() {
        return activity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public void setSemestr(int semestr) {
        this.semestr = semestr;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }


}
