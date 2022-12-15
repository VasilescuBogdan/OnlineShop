package ace.ucv.onlineshop.Services;

import ace.ucv.onlineshop.Exceptions.ResourceNotFoundException;
import ace.ucv.onlineshop.Model.CartItem;
import ace.ucv.onlineshop.Model.Product;
import ace.ucv.onlineshop.Model.User;
import ace.ucv.onlineshop.Repositories.CartItemRepository;
import ace.ucv.onlineshop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ProductRepository productRepository;

    public CartItem add(User user, Integer quantity , Long productId){

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));

        CartItem cartItem = new CartItem();

        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        return cartItem;
    }

    public void remove(Long productId, User user){

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));

        cartItemRepository.deleteByUserAndProduct(user, product);
    }

    public void updateQuantity(Long cartItemId, Integer quantity){ //ToDo continue implementation

    }
}
