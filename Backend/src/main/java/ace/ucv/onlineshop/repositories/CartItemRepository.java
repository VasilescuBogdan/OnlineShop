package ace.ucv.onlineshop.repositories;

import ace.ucv.onlineshop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem getCartItemById(Long id);
}
