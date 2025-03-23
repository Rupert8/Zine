package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_parents_nefk")
//@Getter @Setter
@NoArgsConstructor
public class StudentParents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentInfo studentInfo;

    @Column(name = "pip_father")
    private String fatherFullName;

    @Column(name = "father_phone_number")
    private String phoneFather;

    @Column(name = "pip_mother")
    private String motherFullName;

    @Column(name = "mother_phone_number")
    private String phoneMother;

    @Column(name = "note")
    private String note;

    public int getId() {
        return id;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public String getFatherFullName() {
        return fatherFullName;
    }

    public String getPhoneFather() {
        return phoneFather;
    }

    public String getMotherFullName() {
        return motherFullName;
    }

    public String getPhoneMother() {
        return phoneMother;
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

    public void setFatherFullName(String fatherFullName) {
        this.fatherFullName = fatherFullName;
    }

    public void setPhoneFather(String phoneFather) {
        this.phoneFather = phoneFather;
    }

    public void setMotherFullName(String motherFullName) {
        this.motherFullName = motherFullName;
    }

    public void setPhoneMother(String phoneMother) {
        this.phoneMother = phoneMother;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
