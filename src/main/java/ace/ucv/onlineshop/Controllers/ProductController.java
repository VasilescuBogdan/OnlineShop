package ace.ucv.onlineshop.Controllers;

import ace.ucv.onlineshop.Model.Product;
import ace.ucv.onlineshop.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody Product newProduct){
        return productService.createProduct(newProduct);
    }

    @PutMapping(value = "/{id}")
    public Product updateProduct(@RequestBody Product newProduct, @PathVariable("id") Long productId){
        return  productService.updateProduct(productId, newProduct);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
    }
}