package net.therap.controller;

import net.therap.model.Student;
import net.therap.service.UserService;
import net.therap.util.Access;
import net.therap.validation.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author masud.rana
 * @since 11/7/21
 */
@Controller
@SessionAttributes("student")
public class StudentController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
        binder.setDisallowedFields("active");
    }

    @GetMapping(value = "/showProfile")
    public String showProfile(@RequestParam(defaultValue = "0") int id,
                              Model model, HttpServletRequest request) {
        if (!Access.checkAccessWithId(id, request)) {
            return "warnings/restricted";
        }
        Student student = userService.findStudent(id);
        model.addAttribute("student", student);

        return "profile/show";
    }

    @GetMapping(value = "/editProfile")
    public String show(@ModelAttribute("student") Student student,
                       HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object sessionId = session.getAttribute("userId");
        int userId = Integer.parseInt(sessionId.toString());
        if (!Access.checkAccessWithObject(userId, student, request)) {
            return "warnings/restricted";
        }

        return "profile/edit";
    }

    @PostMapping(value = "/editProfile")
    public String process(@Valid @ModelAttribute("student") Student student,
                          BindingResult result, HttpServletRequest request,
                          SessionStatus status) {
        if (!Access.checkAccessWithId(student.getId(), request)) {
            return "warnings/restricted";
        }
        if (result.hasErrors()) {
            return "profile/edit";
        }
        userService.saveOrUpdate(student);
        status.setComplete();

        return "redirect:/home";
    }
}
