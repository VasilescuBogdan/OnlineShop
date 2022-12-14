package ace.ucv.onlineshop.Controllers;

import ace.ucv.onlineshop.Model.Product;
import ace.ucv.onlineshop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long productId){
        return productService.getProductById(productId);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product newProduct){
        return productService.createProduct(newProduct);
    }

    @PutMapping()
    public Product updateProduct(@RequestBody Product newProduct){
        return  productService.updateProduct(newProduct);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
    }
}
