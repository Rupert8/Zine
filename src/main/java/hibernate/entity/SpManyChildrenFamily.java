package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sp_many_children_family_nefk")
//@Getter @Setter
@NoArgsConstructor
public class SpManyChildrenFamily {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "social_passport_id")
    private SocialPassport socialPassport;

    @Column(name = "count_children")
    private int countChildren;

    @Column(name = "less_than_18")
    private int lessThan18;

    @Column(name = "more_than_18")
    private int moreThan18;

    public int getId() {
        return id;
    }

    public SocialPassport getSocialPassport() {
        return socialPassport;
    }

    public int getCountChildren() {
        return countChildren;
    }

    public int getLessThan18() {
        return lessThan18;
    }

    public int getMoreThan18() {
        return moreThan18;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSocialPassport(SocialPassport socialPassport) {
        this.socialPassport = socialPassport;
    }

    public void setCountChildren(int countChildren) {
        this.countChildren = countChildren;
    }

    public void setLessThan18(int lessThan18) {
        this.lessThan18 = lessThan18;
    }

    public void setMoreThan18(int moreThan18) {
        this.moreThan18 = moreThan18;
    }
}
