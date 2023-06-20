package ace.ucv.onlineshop.Services;

import ace.ucv.onlineshop.Dtos.DiscountDto;
import ace.ucv.onlineshop.Exceptions.NoProductExistInRepositoryException;
import ace.ucv.onlineshop.Exceptions.ProductHasADiscountException;
import ace.ucv.onlineshop.Exceptions.ResourceNotFoundException;
import ace.ucv.onlineshop.Model.Discount;
import ace.ucv.onlineshop.Model.Product;
import ace.ucv.onlineshop.Repositories.DiscountRepository;
import ace.ucv.onlineshop.Repositories.ProductRepository;
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
        productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
        productRepository.deleteById(productId);
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

    public void deleteDiscount(Long id) {

        Discount discount =  discountRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Discount", "Id", id));

        Product product = productRepository.findByDiscount(discount);

        product.setDiscount(null);

        discountRepository.deleteById(id);
    }
    
}
