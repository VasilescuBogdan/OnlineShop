package ace.ucv.onlineshop.controllers;

import ace.ucv.onlineshop.dtos.DiscountDto;
import ace.ucv.onlineshop.model.Discount;
import ace.ucv.onlineshop.model.Product;
import ace.ucv.onlineshop.services.ProductService;
import lombok.AllArgsConstructor;
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

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/discount")
    public Discount addDiscount(@RequestBody DiscountDto newDiscount) {
        return productService.addDiscount(newDiscount);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
        return productService.getProduct(productId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/discount/{id}")
    public void deleteDiscount(@PathVariable("id") Long productId) {
        productService.deleteDiscount(productId);
    }

}
