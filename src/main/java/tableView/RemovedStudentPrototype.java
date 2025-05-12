package tableView;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class RemovedStudentPrototype {
    private int studentId;
    private String studentName;
    private String studentSurname;
    private String studentMiddleName;
    private Date studentBirthDate;
    private String studentPhoneNumber;
    private String studentAddress;
    private Date studentRemovedDate;

}
