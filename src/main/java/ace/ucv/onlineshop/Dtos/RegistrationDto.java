package ace.ucv.onlineshop.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {

    private String lastName;

    private String password;

    private String firstName;

    private String number;

    private String email;
}
