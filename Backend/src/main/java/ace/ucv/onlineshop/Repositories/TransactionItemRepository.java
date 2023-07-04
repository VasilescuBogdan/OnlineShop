package ace.ucv.onlineshop.Repositories;

import ace.ucv.onlineshop.Model.Transaction;
import ace.ucv.onlineshop.Model.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {
    List<TransactionItem> findAllByTransaction(Transaction transaction);
}
