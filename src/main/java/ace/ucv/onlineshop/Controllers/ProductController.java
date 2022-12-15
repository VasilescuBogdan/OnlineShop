package ace.ucv.onlineshop.Controllers;

import ace.ucv.onlineshop.Model.Product;
import ace.ucv.onlineshop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping()
    public Product createProduct(@RequestBody Product newProduct){
        return productService.createProduct(newProduct);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product fetchData(@PathVariable("id") Long productId){
        return productService.getProductById(productId);
    }

    @PutMapping()
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @GetMapping("name/{name}")
    public Product getProduct(@PathVariable("name") String name) {
        return productService.getProductByName(name);
    }

    @DeleteMapping("{id}")
    public void updateProduct(@PathVariable("id") Long Id){
        productService.deleteProduct(Id);
    }
}
