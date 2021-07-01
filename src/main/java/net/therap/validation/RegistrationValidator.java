package net.therap.validation;

import net.therap.dao.UserDao;
import net.therap.model.Admin;
import net.therap.model.Student;
import net.therap.model.User;
import net.therap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * @author masud.rana
 * @since 29/6/21
 */
@Component
public class RegistrationValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Admin.class.equals(clazz) || Student.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        User currentUser = userService.findByEmail(user.getEmail());
        if (Objects.nonNull(currentUser)) {
            errors.rejectValue("email", "ALREADY EXISTS", "User email already exists!");
        }
    }
}
