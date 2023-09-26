package ace.ucv.onlineshop.services;

import ace.ucv.onlineshop.dtos.DiscountDto;
import ace.ucv.onlineshop.exceptions.NoProductExistInRepositoryException;
import ace.ucv.onlineshop.exceptions.ProductHasADiscountException;
import ace.ucv.onlineshop.exceptions.ResourceNotFoundException;
import ace.ucv.onlineshop.model.Discount;
import ace.ucv.onlineshop.model.Product;
import ace.ucv.onlineshop.repositories.DiscountRepository;
import ace.ucv.onlineshop.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final DiscountRepository discountRepository;

    public Product createProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            throw new NoProductExistInRepositoryException();
        } else {
            return products;
        }
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
        productRepository.delete(product);
    }


    public Discount addDiscount(DiscountDto discountDto) {

        Long productId = discountDto.getProductId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));

        if (product.getDiscount() != null) {
            throw new ProductHasADiscountException(product.getName());
        }

        Discount newDiscount = new Discount();
        newDiscount.setValue(discountDto.getValue());
        newDiscount.setPoints(discountDto.getPoints());

        discountRepository.save(newDiscount);

        product.setDiscount(newDiscount);

        productRepository.save(product);

        return newDiscount;
    }

    public void deleteDiscount(Long discountId) {

        Discount discount =  discountRepository.findById(discountId)
                        .orElseThrow(() -> new ResourceNotFoundException("Discount", "Id", discountId));

        Product product = productRepository.findByDiscount(discount);

        product.setDiscount(null);

        discountRepository.deleteById(discountId);
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }
}
