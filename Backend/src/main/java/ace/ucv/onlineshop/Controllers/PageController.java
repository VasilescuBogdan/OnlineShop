package ace.ucv.onlineshop.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping(value = "/products")
    public String productsPage(){
        return "products";
    }

    @GetMapping(value = "/transactions")
    public String transactionsPage(){return "transactions";}

    @GetMapping(value = "/register")
    public String registerPage(){return "register";}

    @GetMapping(value = "/profile")
    public String profilePage(){return "profile";}

    @GetMapping(value = "/cart")
    public String cartPage(){return "cart";}

}
