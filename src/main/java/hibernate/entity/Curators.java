package hibernate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "curators_nefk")
//@Getter @Setter
@AllArgsConstructor
//@NoArgsConstructor
public class Curators {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(mappedBy = "curators", cascade = CascadeType.ALL)
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "group_name")
    private String group;

    @Transient
    private String email;

    public Curators(Integer  id,String name, String surname, String middleName, String group, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.group = group;
        this.email = email;
    }

    public Curators(String name,String surname,String middleName){
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
    }

    public Curators() {

    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
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

    public String getGroup() {
        return group;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setGroup(String group) {
        this.group = group;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
