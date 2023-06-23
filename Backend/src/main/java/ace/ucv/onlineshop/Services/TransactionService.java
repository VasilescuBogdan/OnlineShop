package ace.ucv.onlineshop.Services;

import ace.ucv.onlineshop.Dtos.AddTransactionDto;
import ace.ucv.onlineshop.Model.Transaction;
import ace.ucv.onlineshop.Repositories.ProfileRepository;
import ace.ucv.onlineshop.Repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class TransactionService {

    private final CartService cartService;

    private final ProfileRepository profileRepository;

    private final TransactionRepository transactionRepository;

    public Transaction addTransaction(AddTransactionDto transactionDto, Principal principal) {
        
    }
}
