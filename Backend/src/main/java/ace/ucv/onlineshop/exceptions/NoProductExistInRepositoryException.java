package ace.ucv.onlineshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class NoProductExistInRepositoryException extends RuntimeException {
    public NoProductExistInRepositoryException() {
        super("No product exists in database");
    }
}
