package hibernate.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.NumericBooleanConverter;

import java.sql.Date;


@Data
@Entity
@Table(name = "work_plan_nefk")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Semester")
    private int semester;

    @Column(name = "AcademicYear")
    private int academicYear;

    @Column(name = "EventName")
    private String eventName;

    @Column(name = "ExecutionDate")
    private Date executionDate;

    @Column(name = "Performer")
    private String performer;

    @Column(name = "GroupName")
    private String groupName;

    @Column(name = "CompletionNote")
    private String completionNote;

    @Column(name = "ConfirmationNote")
    private String confirmationNote;

    @Column(name = "Active")
    private boolean active;
}
