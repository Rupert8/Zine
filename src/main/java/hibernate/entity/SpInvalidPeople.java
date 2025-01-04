package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sp_invalid_people")
@Getter
@Setter
@NoArgsConstructor
public class SpInvalidPeople {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "social_passport_id")
    private SocialPassport socialPassport;

    @Column(name = "invalid_group")
    private String group;
}
