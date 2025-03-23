package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "social_passport_nefk")
//@Getter @Setter
//@NoArgsConstructor
public class SocialPassport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentInfo studentInfo;

    @ManyToOne
    @JoinColumn(name = "sp_category_name_id")
    private SpCategoryName spCategoryName;

    @OneToMany(mappedBy = "socialPassport")
    private List<SpInvalidPeople> spInvalidPeopleList;

    @OneToMany(mappedBy = "socialPassport")
    private List<SpChornobiltsi> socialChornobiltsiList;

    @OneToMany(mappedBy = "socialPassport")
    private List<SpManyChildrenFamily> spManyChildrenFamilyList;

    @Column(name = "semester")
    private int semester;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "note")
    private String note;

    @Column
    private boolean invalidStatus;

    @Column
    private boolean manyChildrenStatus;

    @Column(name = "status_adult")
    private boolean statusAdult;

    public SocialPassport(Date startDate, Date endDate, Integer semester, String note) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.semester = semester;
        this.note = note;
    }

    public SocialPassport() {

    }

    @Override
    public String toString() {
        return spCategoryName.getCategory(); // Або інший логічний атрибут
    }

    public boolean isStatusAdult() {
        return statusAdult;
    }

    public boolean isManyChildrenStatus() {
        return manyChildrenStatus;
    }

    public boolean isInvalidStatus() {
        return invalidStatus;
    }

    public String getNote() {
        return note;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getSemester() {
        return semester;
    }

    public List<SpManyChildrenFamily> getSpManyChildrenFamilyList() {
        return spManyChildrenFamilyList;
    }

    public List<SpChornobiltsi> getSocialChornobiltsiList() {
        return socialChornobiltsiList;
    }

    public List<SpInvalidPeople> getSpInvalidPeopleList() {
        return spInvalidPeopleList;
    }

    public SpCategoryName getSpCategoryName() {
        return spCategoryName;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public void setSpCategoryName(SpCategoryName spCategoryName) {
        this.spCategoryName = spCategoryName;
    }

    public void setSpInvalidPeopleList(List<SpInvalidPeople> spInvalidPeopleList) {
        this.spInvalidPeopleList = spInvalidPeopleList;
    }

    public void setSocialChornobiltsiList(List<SpChornobiltsi> socialChornobiltsiList) {
        this.socialChornobiltsiList = socialChornobiltsiList;
    }

    public void setSpManyChildrenFamilyList(List<SpManyChildrenFamily> spManyChildrenFamilyList) {
        this.spManyChildrenFamilyList = spManyChildrenFamilyList;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setInvalidStatus(boolean invalidStatus) {
        this.invalidStatus = invalidStatus;
    }

    public void setManyChildrenStatus(boolean manyChildrenStatus) {
        this.manyChildrenStatus = manyChildrenStatus;
    }

    public void setStatusAdult(boolean statusAdult) {
        this.statusAdult = statusAdult;
    }
}
