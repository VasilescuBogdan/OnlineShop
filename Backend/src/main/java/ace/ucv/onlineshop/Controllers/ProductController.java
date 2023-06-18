package ace.ucv.onlineshop.Controllers;

import ace.ucv.onlineshop.Dtos.DiscountDto;
import ace.ucv.onlineshop.Model.Discount;
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
    public Product createProduct(@RequestBody Product newProduct) {
        return productService.createProduct(newProduct);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @PutMapping()
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long Id) {
        productService.deleteProduct(Id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/discount")
    public Discount addDiscount(@RequestBody DiscountDto newDiscount) {
        return productService.addDiscount(newDiscount);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/discount/{id}")
    public void deleteDiscount(@PathVariable("id") Long productId) {
        productService.deleteDiscount(productId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/discount/{id}")
    public DiscountDto getDiscount(@PathVariable("id") Long productId) {
        return productService.getDiscount(productId);
    }
}
