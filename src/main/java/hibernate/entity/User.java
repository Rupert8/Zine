package hibernate.entity;


import enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.NumericBooleanConverter;

@Data
@Entity
@Table(name = "user_nefk")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "email")
    private String Email;

    @Column(name = "password")
    private String Password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "curator_id")
    private Curators curators;

    @Column
    @Enumerated(EnumType.STRING)
    private UserStatus Status;

    public long getId() {
        return id;
    }

    public String getEmail() {
        return Email;
    }

    public Curators getCurators() {
        return curators;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setCurators(Curators curators) {
        this.curators = curators;
    }

}
