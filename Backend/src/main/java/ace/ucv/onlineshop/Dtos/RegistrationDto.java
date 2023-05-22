package ace.ucv.onlineshop.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {

    private String password;

    private String username;

    private String lastName;

    private String firstName;

    private String number;

    private String email;
}
