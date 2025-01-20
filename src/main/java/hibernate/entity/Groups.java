package hibernate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "study_groups_nefk")
//@Getter @Setter
@AllArgsConstructor
//@NoArgsConstructor
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "groups")
    private List<StudentGroups> studentGroupsList;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "curator")
    private String curator;

    @Column(name = "proffesion")
    private String profession;

    @Column(name = "education_and_professional_program")
    private String educationProgram;

    @Column(name = "level_of_education")
    private String levelOfEducation;

    @Column(name = "course")
    private int course;

    @Column(name = "year_of_study")
    private String yearOfStudy;

    @Column(name = "form_of_education")
    private String formOfEducation;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "is_Active")
    private Boolean Active;

    public Groups(Integer id,String groupName,String curator,String profession){
        this.id = id;
        this.groupName = groupName;
        this.curator = curator;
        this.profession = profession;
    }

    public Groups() {

    }


    public int getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getCurator() {
        return curator;
    }

    public String getProfession() {
        return profession;
    }

    public String getEducationProgram() {
        return educationProgram;
    }

    public String getLevelOfEducation() {
        return levelOfEducation;
    }

    public int getCourse() {
        return course;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public String getFormOfEducation() {
        return formOfEducation;
    }

    public Boolean getStatus() {
        return status;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setEducationProgram(String educationProgram) {
        this.educationProgram = educationProgram;
    }

    public void setLevelOfEducation(String levelOfEducation) {
        this.levelOfEducation = levelOfEducation;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public void setFormOfEducation(String formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setActive(Boolean active) {
        Active = active;
    }
}
