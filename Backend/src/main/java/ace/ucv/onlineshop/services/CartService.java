package ace.ucv.onlineshop.services;

import ace.ucv.onlineshop.model.Cart;
import ace.ucv.onlineshop.model.CartItem;
import ace.ucv.onlineshop.model.User;
import ace.ucv.onlineshop.repositories.*;
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

    public Cart addNewCartItem(CartItem cartItem, Principal principal) {

        CartItem newCartItem = new CartItem();
        newCartItem.setQuantity(cartItem.getQuantity());
        newCartItem.setProduct((cartItem.getProduct()));
        newCartItem.setIsReduced(false);
        cartItemRepository.save(newCartItem);

        Cart cart = getCart(principal);
        cart.getCartItems().add(newCartItem);
        return cartRepository.save(cart);
    }

    public Cart getCart(Principal principal) {
        User currentUser = userRepository.findUserByUsername(principal.getName());
        return cartRepository.getCartByUserProfile(profileRepository.findProfileByUser(currentUser));
    }

    public CartItem setIsReduced(boolean isReduced, Long cartItemId) {
        CartItem cartItem = cartItemRepository.getCartItemById(cartItemId);
        cartItem.setIsReduced(isReduced);
        return cartItemRepository.save(cartItem);
    }

    public CartItem setQuantity(Integer quantity, Long cartItemId) {
        CartItem cartItem = cartItemRepository.getCartItemById(cartItemId);
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Long cartItemId) {cartItemRepository.deleteById(cartItemId);
    }
}
