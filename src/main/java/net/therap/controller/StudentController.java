package net.therap.controller;

import net.therap.exception.NotFoundException;
import net.therap.exception.WebSecurityException;
import net.therap.model.Student;
import net.therap.service.UserService;
import net.therap.util.Access;
import net.therap.validation.RegistrationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

import static net.therap.util.Helper.EDIT_PROFILE;
import static net.therap.util.Helper.SHOW_PROFILE;

/**
 * @author masud.rana
 * @since 11/7/21
 */
@Controller
@SessionAttributes("student")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
        binder.setDisallowedFields("active");
    }

    @GetMapping(value = "/showProfile")
    public String showProfile(@RequestParam(defaultValue = "0") int id,
                              Model model, HttpServletRequest request) throws Exception {

        //Access.checkAccessWithId(id, request);

        Student student = userService.findStudent(id);

        if (Objects.isNull(student)) {
            throw new NotFoundException(id);
        }

        logger.info("Image File: " + student.getImage().toString());

        byte[] encodeBase64 = Base64.getEncoder().encode(student.getImage());
        String base64Encoded = new String(encodeBase64, "UTF-8");

        student.setBase64image(base64Encoded);

        model.addAttribute("student", student);

        return SHOW_PROFILE;
    }

    @GetMapping(value = "/editProfile")
    public String show(@ModelAttribute("student") Student student,
                       HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        Object sessionId = session.getAttribute("userId");
        int userId = Integer.parseInt(sessionId.toString());

        Access.checkAccessWithId(userId, request);

        return EDIT_PROFILE;
    }

    @PostMapping(value = "/editProfile")
    public String process(@Valid @ModelAttribute("student") Student student,
                          BindingResult result, HttpServletRequest request,
                          SessionStatus status) throws WebSecurityException {

        Access.checkAccessWithId(student.getId(), request);

        if (result.hasErrors()) {
            return EDIT_PROFILE;
        }

        userService.saveOrUpdate(student);
        status.setComplete();

        return "redirect:/home";
    }
}
