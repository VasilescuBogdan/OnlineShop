package ace.ucv.onlineshop.Model;

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
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @JoinColumn(name = "cart_id")
    @OneToMany
    private List<CartItem> cartItems;

    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne
    private Profile userProfile;

}
