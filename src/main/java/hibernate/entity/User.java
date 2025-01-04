package hibernate.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.NumericBooleanConverter;

@Entity
@Table(name = "user")
@Getter
@Setter
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curator_id")
    private Curators curators;

    @Column
    @Convert(converter = NumericBooleanConverter.class)
    private boolean Status;
}
