package ace.ucv.onlineshop.Exceptions;

public class ProductReducedException extends RuntimeException{

    private final String productName;

    public ProductReducedException(String productName) {
        super(String.format("%s is already reduced.", productName));
        this.productName = productName;
    }
}
