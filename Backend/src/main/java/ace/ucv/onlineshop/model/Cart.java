package ace.ucv.onlineshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @JoinColumn(nullable = false)
    @OneToMany
    private List<CartItem> cartItems;

    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne
    private Profile userProfile;

}
