package ace.ucv.onlineshop.repositories;

import ace.ucv.onlineshop.model.Cart;
import ace.ucv.onlineshop.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart getCartByUserProfile(Profile profile);
}
