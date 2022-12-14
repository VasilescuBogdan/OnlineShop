package ace.ucv.onlineshop.Repositories;

import ace.ucv.onlineshop.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}