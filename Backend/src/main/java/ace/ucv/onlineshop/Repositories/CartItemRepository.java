package ace.ucv.onlineshop.Repositories;

import ace.ucv.onlineshop.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem getCartItemById(Long id);
}
