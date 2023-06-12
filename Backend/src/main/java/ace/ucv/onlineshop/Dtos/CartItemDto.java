package ace.ucv.onlineshop.Dtos;

import ace.ucv.onlineshop.Model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {

    Product product;

    Integer quantity;
}
