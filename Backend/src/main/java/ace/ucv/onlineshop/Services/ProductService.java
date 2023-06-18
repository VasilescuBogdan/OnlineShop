package ace.ucv.onlineshop.Services;

import ace.ucv.onlineshop.Dtos.DiscountDto;
import ace.ucv.onlineshop.Exceptions.NoProductExistInRepositoryException;
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

    public Product updateProduct(Product product) {

        Product newProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", product.getId()));

        newProduct.setName(product.getName());
        newProduct.setSpecifications(product.getSpecifications());
        newProduct.setPrice(product.getPrice());
        newProduct.setStock(product.getStock());
        newProduct.setProvider(product.getProvider());
        newProduct.setCategory(product.getCategory());

        productRepository.save(newProduct);

        return newProduct;
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

        Discount newDiscount = new Discount();
        newDiscount.setValue(discountDto.getValue());
        newDiscount.setPoints(discountDto.getPoints());
        newDiscount.setProduct(product);

        discountRepository.save(newDiscount);

        return newDiscount;
    }

    public void deleteDiscount(Long productId) {

        Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
        discountRepository.deleteDiscountByProduct(product);
    }

    public DiscountDto getDiscount(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
        Discount discount = discountRepository.findDiscountByProduct(product);

        DiscountDto discountDto = new DiscountDto();
        discountDto.setPoints(discount.getPoints());
        discountDto.setValue(discount.getValue());
        discountDto.setProductId(productId);

        return discountDto;
    }
    
}
