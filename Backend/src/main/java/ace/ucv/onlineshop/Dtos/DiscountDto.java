package ace.ucv.onlineshop.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountDto {

    Long productId;

    Float value;

    Integer points;
}
