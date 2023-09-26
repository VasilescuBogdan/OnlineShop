package ace.ucv.onlineshop.controllers;

import ace.ucv.onlineshop.model.Cart;
import ace.ucv.onlineshop.model.CartItem;
import ace.ucv.onlineshop.services.CartService;
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
    public CartItem setQuantity(@RequestParam("quantity") Integer quantity, @PathVariable("id") Long id) {
        return cartService.setQuantity(quantity, id);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PatchMapping(("/isReduced/{id}"))
    public CartItem addCartItem(@RequestParam("is_reduced") Boolean isReduced, @PathVariable("id") Long id) {
        return cartService.setIsReduced(isReduced, id);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable("id") Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
    }
}
