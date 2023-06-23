package ace.ucv.onlineshop.Repositories;

import ace.ucv.onlineshop.Model.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {
}
