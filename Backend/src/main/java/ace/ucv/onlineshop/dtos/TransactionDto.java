package ace.ucv.onlineshop.dtos;

import ace.ucv.onlineshop.model.TransactionItem;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TransactionDto {

    private LocalDate date;

    private Double totalCost;

    private List<TransactionItem> items;
}
