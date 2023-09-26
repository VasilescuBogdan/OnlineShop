package ace.ucv.onlineshop.controllers;

import ace.ucv.onlineshop.dtos.PaymentDto;
import ace.ucv.onlineshop.dtos.TransactionDto;
import ace.ucv.onlineshop.model.Transaction;
import ace.ucv.onlineshop.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping()
    public Transaction addTransaction(@RequestBody PaymentDto transactionDto, Principal principal) {
        return transactionService.addTransaction(transactionDto, principal);
    }

    @GetMapping
    public List<TransactionDto> getTransactions(Principal principal) {
        return transactionService.getTransactions(principal);
    }
}
