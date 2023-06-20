package ace.ucv.onlineshop.Repositories;

import ace.ucv.onlineshop.Model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {}