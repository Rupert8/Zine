package tableView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//@AllArgsConstructor
//@Getter @Setter
public class SocialPassportCategoryPrototype {
    private String category;

    public SocialPassportCategoryPrototype(String category) {
        this.category = category;
    }

    public SocialPassportCategoryPrototype(){}

    @Override
    public String toString(){
        return category;
    }

    public String getNameCategory() {
        return category;
    }

}
