package ace.ucv.onlineshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "role")
    private String role;

}