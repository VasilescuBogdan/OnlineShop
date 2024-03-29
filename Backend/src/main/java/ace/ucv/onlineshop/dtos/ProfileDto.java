package ace.ucv.onlineshop.dtos;

import ace.ucv.onlineshop.model.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProfileDto {

    String username;

    String email;

    String firstName;

    String lastName;

    LocalDate creationDate;

    String number;

    Integer points;

    List<CartItem> cart;
}
