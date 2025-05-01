package hibernate.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.NumericBooleanConverter;

import java.sql.Date;


@Data
@Entity
@Table(name = "work_plan_nefk")
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

    @Column(name = "CompletionNote")
    private String completionNote;

    @Column(name = "ConfirmationNote")
    private String confirmationNote;

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public String getPerformer() {
        return performer;
    }

    public String getCompletionNote() {
        return completionNote;
    }

    public String getConfirmationNote() {
        return confirmationNote;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public void setCompletionNote(String completionNote) {
        this.completionNote = completionNote;
    }

    public void setConfirmationNote(String confirmationNote) {
        this.confirmationNote = confirmationNote;
    }
}
