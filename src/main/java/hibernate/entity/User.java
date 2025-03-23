package hibernate.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.NumericBooleanConverter;

@Entity
@Table(name = "user_nefk")
//@Getter @Setter
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
    @Convert(converter = NumericBooleanConverter.class)
    private boolean Status;

    public long getId() {
        return id;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public Curators getCurators() {
        return curators;
    }

    public boolean isStatus() {
        return Status;
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

    public void setStatus(boolean status) {
        Status = status;
    }
}
