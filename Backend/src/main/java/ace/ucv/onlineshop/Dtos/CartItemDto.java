package ace.ucv.onlineshop.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {

    Long productId;

    Integer quantity;
}
