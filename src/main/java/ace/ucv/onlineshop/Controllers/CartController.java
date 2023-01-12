package ace.ucv.onlineshop.Controllers;

import ace.ucv.onlineshop.Dtos.CartItemDto;
import ace.ucv.onlineshop.Model.CartItem;
import ace.ucv.onlineshop.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping(value = "/{id}")
    public CartItem addProduct(Principal principal, @RequestBody CartItemDto cartItemDto){
        return cartService.add(principal, cartItemDto);
    }

    @DeleteMapping()
    public void removeProduct(Long productId, Principal principal){
        cartService.remove(productId, principal);
    }

    @GetMapping()
    public List<CartItem> getCart(Principal principal) {return cartService.getCart(principal);}
}
