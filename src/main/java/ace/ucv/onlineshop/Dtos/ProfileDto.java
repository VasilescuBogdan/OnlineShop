package ace.ucv.onlineshop.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProfileDto {

    String email;

    String firstName;

    String lastName;

    LocalDate creationDate;

    String number;

    Integer points;
}
