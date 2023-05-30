package ace.ucv.onlineshop.Controllers;

import ace.ucv.onlineshop.Dtos.CartItemDto;
import ace.ucv.onlineshop.Model.Cart;
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
    public Cart addCartItem(@RequestBody CartItemDto cartItemDto, Principal principal) {
        return cartService.addNewCartItem(cartItemDto, principal);
    }
}
