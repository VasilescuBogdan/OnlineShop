package ace.ucv.onlineshop.services;

import ace.ucv.onlineshop.dtos.PaymentDto;
import ace.ucv.onlineshop.dtos.TransactionDto;
import ace.ucv.onlineshop.model.*;
import ace.ucv.onlineshop.repositories.ProfileRepository;
import ace.ucv.onlineshop.repositories.TransactionItemRepository;
import ace.ucv.onlineshop.repositories.TransactionRepository;
import ace.ucv.onlineshop.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {

    private final CartService cartService;

    private final ProfileRepository profileRepository;

    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    private final TransactionItemRepository transactionItemRepository;

    public Transaction addTransaction(PaymentDto paymentDto, Principal principal) {
        
        User user = userRepository.findUserByUsername(principal.getName());

        Profile profile = profileRepository.findProfileByUser(user);
        int actualPoints = profile.getPoints() + paymentDto.getTotalPoints();
        if (actualPoints < 0) {
            throw new RuntimeException("Invalid transaction: Not enough points");
        }
        profile.setPoints(actualPoints);
        profileRepository.save(profile);

        Transaction transaction = new Transaction();
        transaction.setTotalCost(paymentDto.getTotalPrice());
        transaction.setDate(LocalDate.now());
        transaction.setProfile(profile);

        transactionRepository.save(transaction);

        Cart cart = cartService.getCart(principal);

        for (CartItem item: cart.getCartItems()) {
            TransactionItem transactionItem = getTransactionItem(item, transaction);

            transactionItemRepository.save(transactionItem);
            cartService.deleteCartItem(item.getId());
        }

        return transactionRepository.save(transaction);
    }

    private TransactionItem getTransactionItem(CartItem item, Transaction transaction) {
        TransactionItem transactionItem = new TransactionItem();
        transactionItem.setName(item.getProduct().getName());
        transactionItem.setQuantity(item.getQuantity());
        transactionItem.setTransaction(transaction);

        Double actualPrice;
        if (item.getIsReduced())
            actualPrice = item.getProduct().getPrice() - item.getProduct().getPrice() * item.getProduct().getDiscount().getValue() / 100;
        else
            actualPrice = item.getProduct().getPrice();
        transactionItem.setPrice(actualPrice);
        return transactionItem;
    }

    public List<TransactionDto> getTransactions(Principal principal) {
        List<Transaction> transactions = transactionRepository.findTransactionsByProfile(profileRepository.findProfileByUser(userRepository.findUserByUsername(principal.getName())));
        List<TransactionDto> transactionDtos = new ArrayList<>();
        for (Transaction transaction: transactions) {
            TransactionDto transactionDto = new TransactionDto();

            transactionDto.setDate(transaction.getDate());
            transactionDto.setTotalCost(transaction.getTotalCost());
            transactionDto.setItems(transactionItemRepository.findAllByTransaction(transaction));

            transactionDtos.add(transactionDto);
        }

        return transactionDtos;
    }
}
