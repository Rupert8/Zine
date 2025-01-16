package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "social_passport")
@Getter
@Setter
@NoArgsConstructor
public class SocialPassport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
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

    public SocialPassport(Date startDate, Date endDate, Integer semester, String note) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.semester = semester;
        this.note = note;
    }

    @Override
    public String toString() {
        return spCategoryName.getCategory(); // Або інший логічний атрибут
    }

}
