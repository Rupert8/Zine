package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_parents")
@Getter
@Setter
@NoArgsConstructor
public class StudentParents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
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
}
