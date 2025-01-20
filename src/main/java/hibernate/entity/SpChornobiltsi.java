package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sp_chornobiltsi_nefk")
@Getter @Setter
@NoArgsConstructor
public class SpChornobiltsi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "social_passport_id")
    private SocialPassport socialPassport;

    @Column(name = "group_name")
    private String group_name;

}
