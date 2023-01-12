package ace.ucv.onlineshop.Services;

import ace.ucv.onlineshop.Dtos.CartItemDto;
import ace.ucv.onlineshop.Exceptions.ResourceNotFoundException;
import ace.ucv.onlineshop.Model.CartItem;
import ace.ucv.onlineshop.Model.Product;
import ace.ucv.onlineshop.Model.User;
import ace.ucv.onlineshop.Repositories.CartItemRepository;
import ace.ucv.onlineshop.Repositories.ProductRepository;
import ace.ucv.onlineshop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    public CartItem add(Principal principal, CartItemDto cartItemDto){

        Long productId = cartItemDto.getProductId();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));


        String currentEmail = principal.getName();
        User currentUser = userRepository.findUserByEmail(currentEmail);

        CartItem cartItem = new CartItem();

        cartItem.setUser(currentUser);
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemDto.getQuantity());

        return cartItem;
    }

    public void remove(Long productId, Principal principal){

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));

        String currentEmail = principal.getName();
        User currentUser = userRepository.findUserByEmail(currentEmail);

        cartItemRepository.deleteByUserAndProduct(currentUser, product);
    }

    public List<CartItem> getCart(Principal principal){

        String currentEmail = principal.getName();
        User currentUser = userRepository.findUserByEmail(currentEmail);

        return cartItemRepository.findCartItemsByUser(currentUser);
    }

    public void updateQuantity(Long cartItemId, Integer quantity){ //ToDo continue implementation

    }
}
