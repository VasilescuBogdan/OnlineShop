package ace.ucv.onlineshop.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {

    private Double totalPrice;

    private Integer totalPoints;
}
