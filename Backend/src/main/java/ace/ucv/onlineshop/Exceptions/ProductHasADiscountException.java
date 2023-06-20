package ace.ucv.onlineshop.Exceptions;

public class ProductHasADiscountException extends RuntimeException{
    public ProductHasADiscountException(String productName) {
        super(String.format("Product with name %s has a discount already!", productName));
    }
}
