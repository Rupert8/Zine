package hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "sp_category_name_nefk")
//@Getter @Setter
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

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public List<SocialPassport> getSocialPassportList() {
        return socialPassportList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSocialPassportList(List<SocialPassport> socialPassportList) {
        this.socialPassportList = socialPassportList;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
