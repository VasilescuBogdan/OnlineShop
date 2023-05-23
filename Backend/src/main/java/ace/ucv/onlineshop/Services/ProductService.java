package ace.ucv.onlineshop.Services;

import ace.ucv.onlineshop.Dtos.DiscountDto;
import ace.ucv.onlineshop.Exceptions.ResourceNotFoundException;
import ace.ucv.onlineshop.Model.Discount;
import ace.ucv.onlineshop.Model.Product;
import ace.ucv.onlineshop.Repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product newProduct){
        return productRepository.save(newProduct);
    }

    public List<Product> getProducts(int pageNumber){
        return (List<Product>) productRepository.findAll(PageRequest.of(pageNumber, 12)).getContent();
    }

    public Product getProductById(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
    }

    public Product updateProduct(Product product){

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

    public void deleteProduct(Long productId){
        productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
        productRepository.deleteById(productId);
    }


    public Discount AddDiscount(Long productId, DiscountDto discountDto){ //TODO figure how to set is reduced to product

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));


        Discount newDiscount = new Discount();
        newDiscount.setValue(discountDto.getValue());
        newDiscount.setPoints(discountDto.getPoints());
        newDiscount.setProduct(product);

        productRepository.save(product);

        return newDiscount;
    }
}
