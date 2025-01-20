package tableView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
//@Getter @Setter
public class RemovedStudentPrototype {
    private int studentId;
    private String studentName;
    private String studentSurname;
    private String studentMiddleName;
    private Date studentBirthDate;
    private String studentPhoneNumber;
    private String studentAddress;
    private Date studentRemovedDate;

    public Date getStudentRemovedDate() {
        return studentRemovedDate;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public Date getStudentBirthDate() {
        return studentBirthDate;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public void setStudentBirthDate(Date studentBirthDate) {
        this.studentBirthDate = studentBirthDate;
    }

    public void setStudentPhoneNumber(String studentPhoneNumber) {
        this.studentPhoneNumber = studentPhoneNumber;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public void setStudentRemovedDate(Date studentRemovedDate) {
        this.studentRemovedDate = studentRemovedDate;
    }
}
