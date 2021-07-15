package net.therap.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author masud.rana
 * @since 15/7/21
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(Model model, Exception exception) {
        model.addAttribute("exception", exception);
        return "warnings/error";
    }

    @ExceptionHandler(WebSecurityException.class)
    public String handleWebSecurityException(Model model, Exception exception) {
        model.addAttribute("exception", exception);
        return "warnings/error";
    }
}
