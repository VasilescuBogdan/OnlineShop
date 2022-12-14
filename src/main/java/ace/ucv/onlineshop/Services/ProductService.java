package ace.ucv.onlineshop.Services;

import ace.ucv.onlineshop.Exceptions.ResourceNotFoundException;
import ace.ucv.onlineshop.Model.Product;
import ace.ucv.onlineshop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product newProduct){
        return productRepository.save(newProduct);
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
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

        productRepository.save(newProduct);

        return newProduct;
    }

    public void deleteProduct(Long productId){
        productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
        productRepository.deleteById(productId);
    }
}
