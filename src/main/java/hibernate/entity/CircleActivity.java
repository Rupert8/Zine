package hibernate.entity;

import jakarta.persistence.*;
import javafx.scene.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "circle_activity")
@Getter
@Setter
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

}
