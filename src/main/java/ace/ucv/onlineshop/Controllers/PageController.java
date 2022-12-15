package ace.ucv.onlineshop.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping(value = "/details/{name}")
    public String detailsPage(@PathVariable String name){return "details/" + name;} //TODO make object Details page

}
