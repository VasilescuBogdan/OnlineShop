package ace.ucv.onlineshop.Repositories;

import ace.ucv.onlineshop.Model.Cart;
import ace.ucv.onlineshop.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart getCartByUserProfile(Profile profile);
}
