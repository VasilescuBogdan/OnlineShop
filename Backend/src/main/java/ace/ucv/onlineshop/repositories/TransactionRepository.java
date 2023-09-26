package ace.ucv.onlineshop.repositories;

import ace.ucv.onlineshop.model.Profile;
import ace.ucv.onlineshop.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionsByProfile(Profile profileByUser);
}