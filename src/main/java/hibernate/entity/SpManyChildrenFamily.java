package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sp_many_children_family")
@Getter
@Setter
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
}
