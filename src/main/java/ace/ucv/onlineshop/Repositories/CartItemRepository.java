package ace.ucv.onlineshop.Repositories;

import ace.ucv.onlineshop.Model.CartItem;
import ace.ucv.onlineshop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findCartItemsByUser(User user);

    @Transactional
    void deleteAllByUser(User user);

    @Transactional
    @Query("UPDATE CartItem c SET c.quantity = ?1 WHERE c.product.id = ?2 AND c.user.id = ?3")
    @Modifying
    void updateQuantity(Integer quantity, Float productId, Float userId );
}