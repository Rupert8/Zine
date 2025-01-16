package hibernate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.NumericBooleanConverter;

import java.sql.Date;


@Entity
@Table(name = "work_plan")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Semester")
    private int semester;

    @Column(name = "EventName")
    private String eventName;

    @Column(name = "ExecutionDate")
    private Date executionDate;

    @Column(name = "Performer")
    private String performer;

    @Column(name = "CompletionNote")
    private String completionNote;

    @Column(name = "ConfirmationNote")
    private String confirmationNote;
}
