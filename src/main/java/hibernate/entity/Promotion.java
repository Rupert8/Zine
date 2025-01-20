package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "promotion_nefk")
//@Getter @Setter
@NoArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentInfo studentInfo;

    @Column(name = "semestr")
    private int semestr;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "content")
    private String content;

    public int getId() {
        return id;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public int getSemestr() {
        return semestr;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getContent() {
        return content;
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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
