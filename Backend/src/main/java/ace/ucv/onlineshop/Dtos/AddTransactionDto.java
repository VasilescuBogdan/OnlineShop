package ace.ucv.onlineshop.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTransactionDto {

    private Double totalValue;

    private Integer totalPoints;
}
