package ace.ucv.onlineshop.repositories;

import ace.ucv.onlineshop.model.Discount;
import ace.ucv.onlineshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByDiscount(Discount discount);
}