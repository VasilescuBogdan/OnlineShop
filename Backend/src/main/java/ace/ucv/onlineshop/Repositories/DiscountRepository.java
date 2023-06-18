package ace.ucv.onlineshop.Repositories;

import ace.ucv.onlineshop.Model.Discount;
import ace.ucv.onlineshop.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    @Transactional
    void deleteDiscountByProduct(Product product);

    @Transactional
    Discount findDiscountByProduct(Product product);
}