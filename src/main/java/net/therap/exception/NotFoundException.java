package net.therap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author masud.rana
 * @since 15/7/21
 */
public class NotFoundException extends Exception {

    public NotFoundException(int id) {
        super("Object Not Found with id: " + id);
    }
}
