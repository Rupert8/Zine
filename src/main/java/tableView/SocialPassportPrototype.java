package tableView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SocialPassportPrototype {
    private Integer id;
    private String name;
    private String surname;
    private String middleName;
    private String nameCategory;
    private Integer semester;
}
