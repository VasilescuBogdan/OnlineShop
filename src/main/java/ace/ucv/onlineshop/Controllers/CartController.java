package ace.ucv.onlineshop.Controllers;

import ace.ucv.onlineshop.Model.CartItem;
import ace.ucv.onlineshop.Model.User;
import ace.ucv.onlineshop.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping()
    public CartItem addProduct(User user, Integer quantity, Long productId){
        return cartService.add(user, quantity, productId);
    }

    @DeleteMapping()
    public void removeProduct(Long productId, User user){
        cartService.remove(productId, user);
    }
}
