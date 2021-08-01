package net.therap.controller;

import net.therap.model.Admin;
import net.therap.model.Student;
import net.therap.service.UserService;
import net.therap.validation.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

import static net.therap.util.Helper.*;

/**
 * @author masud.rana
 * @since 29/6/21
 */
@Controller
public class RegController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationValidator registrationValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(registrationValidator);
    }

    @GetMapping(value = "/home")
    public String showHome() {
        return HOME;
    }

    @GetMapping(value = "/registerStudent")
    public String showStudentRegister(Model model) {
        model.addAttribute("student", new Student());
        return STUDENT_REGISTER;
    }

    @PostMapping(value = "/registerStudent")
    public String processStudentRegister(@Valid @ModelAttribute Student student,
                                         BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return STUDENT_REGISTER;
        }

        student.setData(student.getImageFile());
        userService.saveOrUpdate(student);

        return "redirect:/home";
    }

    @GetMapping(value = "/registerAdmin")
    public String showAdminRegister(Model model) {
        model.addAttribute("admin", new Admin());

        return ADMIN_REGISTER;
    }

    @PostMapping(value = "/registerAdmin")
    public String processAdminRegister(@Valid @ModelAttribute Admin admin,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return ADMIN_REGISTER;
        }

        admin.setActive(true);
        userService.saveOrUpdate(admin);

        return "redirect:/home";
    }
}
