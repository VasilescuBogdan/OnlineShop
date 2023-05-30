package ace.ucv.onlineshop.Services;

import ace.ucv.onlineshop.Dtos.CartItemDto;
import ace.ucv.onlineshop.Model.Cart;
import ace.ucv.onlineshop.Model.CartItem;
import ace.ucv.onlineshop.Model.User;
import ace.ucv.onlineshop.Repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;

    private final CartRepository cartRepository;

    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    private final ProductRepository productRepository;

    public Cart addNewCartItem(CartItemDto cartItemDto, Principal principal) {

        CartItem newCartItem = new CartItem();
        newCartItem.setQuantity(cartItemDto.getQuantity());
        newCartItem.setProduct(productRepository.findById(cartItemDto.getProductId()).get());
        cartItemRepository.save(newCartItem);

        Cart cart = getCart(principal);
        cart.getCartItems().add(newCartItem);
        return cartRepository.save(cart);
    }

    public Cart getCart(Principal principal) {
        User currentUser = userRepository.findUserByUsername(principal.getName());
        return cartRepository.getCartByUserProfile(profileRepository.findProfileByUser(currentUser));
    }
}
