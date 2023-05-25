package ace.ucv.onlineshop.Controllers;

import ace.ucv.onlineshop.Model.Product;
import ace.ucv.onlineshop.Services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public Product createProduct(@RequestBody Product newProduct){
        return productService.createProduct(newProduct);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getProducts();
    }

    @PutMapping()
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long Id){
        productService.deleteProduct(Id);
    }
}
