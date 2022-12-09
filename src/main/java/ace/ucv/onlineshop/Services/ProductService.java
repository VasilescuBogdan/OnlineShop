package ace.ucv.onlineshop.Services;

import ace.ucv.onlineshop.Exceptions.ResourceNotFoundException;
import ace.ucv.onlineshop.Model.Product;
import ace.ucv.onlineshop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product newProduct){
        return productRepository.save(newProduct);
    }

    public Product updateProduct(Long productId, Product newProduct){

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));

        product.setName(newProduct.getName());
        product.setSpecifications(newProduct.getSpecifications());
        product.setPrice(newProduct.getPrice());
        product.setStock(newProduct.getStock());
        product.setProvider(newProduct.getProvider());

        productRepository.save(product);

        return product;
    }

    public void deleteProduct(Long productId){
        productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
        productRepository.deleteById(productId);
    }
}
