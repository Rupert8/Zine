package hibernate.entity;

import jakarta.persistence.*;
import javafx.scene.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "circle_activity_nefk")
//@Getter @Setter
@NoArgsConstructor
public class CircleActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentInfo studentInfo;

    @Column(name = "semestr")
    private int semestr;

    @Column(name = "circle_name")
    private String circleName;

    @Column(name = "note")
    private String note;

    public int getId() {
        return id;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public int getSemestr() {
        return semestr;
    }

    public String getCircleName() {
        return circleName;
    }

    public String getNote() {
        return note;
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

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
