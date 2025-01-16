package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "promotion")
@Getter
@Setter
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
}
