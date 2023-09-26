package ace.ucv.onlineshop.repositories;

import ace.ucv.onlineshop.model.Transaction;
import ace.ucv.onlineshop.model.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {
    List<TransactionItem> findAllByTransaction(Transaction transaction);
}
