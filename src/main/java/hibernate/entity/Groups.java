package hibernate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "study_groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public Groups(Integer id,String groupName,String curator,String profession){
        this.id = id;
        this.groupName = groupName;
        this.curator = curator;
        this.profession = profession;
    }
}
