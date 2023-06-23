package ace.ucv.onlineshop.Controllers;

import ace.ucv.onlineshop.Model.Cart;
import ace.ucv.onlineshop.Model.CartItem;
import ace.ucv.onlineshop.Services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping()
    public Cart addCartItem(@RequestBody CartItem cartItem, Principal principal) {
        return cartService.addNewCartItem(cartItem, principal);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PatchMapping(("/quantity/{id}"))
    public CartItem SetQuantity(@RequestParam("quantity") Integer quantity, @PathVariable("id") Long Id) {
        return cartService.setQuantity(quantity, Id);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PatchMapping(("/isReduced/{id}"))
    public CartItem addCartItem(@RequestParam("is_reduced") Boolean isReduced, @PathVariable("id") Long Id) {
        return cartService.setIsReduced(isReduced, Id);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @DeleteMapping("/{id}")
    public void DeleteCartItem(@PathVariable("id") Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
    }
}
