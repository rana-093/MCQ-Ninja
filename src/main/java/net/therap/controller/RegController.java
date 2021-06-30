package net.therap.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import net.therap.model.Admin;
import net.therap.model.Student;
import net.therap.service.UserService;
import net.therap.validation.ValidateRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author masud.rana
 * @since 29/6/21
 */
@Controller
public class RegController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidateRegister validateRegister;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validateRegister);
    }

    @GetMapping(value = "/home")
    public String showHome() {
        return "home";
    }

    @GetMapping(value = "/registerStudent")
    public String showStudentRegister(Model model) {
        model.addAttribute("student", new Student());
        return "auth/studentRegister";
    }

    @PostMapping(value = "/registerStudent")
    public String processStudentRegister(@Valid @ModelAttribute("student") Student student,
                                         BindingResult result) {
        if (result.hasErrors()) {
            return "auth/studentRegister";
        }
        userService.saveOrUpdate(student);
        return "redirect:/home";
    }

    @GetMapping(value = "/registerAdmin")
    public String showAdminRegister(Model model) {
        model.addAttribute("admin", new Admin());
        return "auth/adminRegister";
    }

    @PostMapping(value = "/registerAdmin")
    public String processAdminRegister(@Valid @ModelAttribute("admin") Admin admin, BindingResult result) {
        if (result.hasErrors()) {
            return "auth/adminRegister";
        }
        admin.setActive(true);
        userService.saveOrUpdate(admin);
        return "redirect:/home";
    }
}
