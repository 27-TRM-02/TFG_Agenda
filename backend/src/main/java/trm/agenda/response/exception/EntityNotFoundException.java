package trm.agenda.response.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends IllegalArgumentException {

    public EntityNotFoundException(UUID id, Class<?> entityClass) {
        super(String.format("Entity '%s' with id '%s' does not exist", entityClass.getName().toLowerCase(), id));
    }

}
