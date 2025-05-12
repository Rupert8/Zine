package tableView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
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
