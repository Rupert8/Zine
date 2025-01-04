package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "sp_category_name")
@Getter
@Setter
@NoArgsConstructor
public class SpCategoryName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "spCategoryName")
    private List<SocialPassport> socialPassportList;

    @Column(name = "category")
    private String category;

}
