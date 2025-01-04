package tableView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SocialPassportCategoryPrototype {
    private String nameCategory;

    @Override
    public String toString(){
        return nameCategory;
    }
}
