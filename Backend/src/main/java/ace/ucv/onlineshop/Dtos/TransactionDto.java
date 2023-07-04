package ace.ucv.onlineshop.Dtos;

import ace.ucv.onlineshop.Model.TransactionItem;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TransactionDto {

    private LocalDate date;

    private Double TotalCost;

    private List<TransactionItem> items;
}
