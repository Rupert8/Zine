package tableView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
//@Getter @Setter
public class SocialPassportPrototype {
    private Integer id;
    private String name;
    private String surname;
    private String middleName;
    private String category;
    private String groupName;
    private Integer semester;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getCategory() {
        return category;
    }

    public String getGroupName() {
        return groupName;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setCategory(String nameCategory) {
        this.category = nameCategory;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
