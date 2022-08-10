package net.therap.controller;

import net.therap.model.Student;
import net.therap.model.User;
import net.therap.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static net.therap.util.Helper.*;

/**
 * @author masud.rana
 * @since 29/6/21
 */
@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/userList")
    public String showAll(Model model) {
        List studentList = userService.findAllStudents();
        model.addAttribute("studentList", studentList);

        return STUDENT_LIST;
    }

    @GetMapping(value = "/user")
    public String show(@RequestParam(defaultValue = "0") int id, Model model) {
        Student student = (id == 0) ? new Student() : userService.findStudent(id);

        if (Objects.isNull(student)) {
            return FOUR_O_FOUR;
        }

        model.addAttribute("student", student);

        return USER_STUDENT;
    }

    @PostMapping(value = "/user")
    public String process(@Valid @ModelAttribute("student") Student student,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", student);
            return USER_STUDENT;
        }
        logger.info("IN PROFILE EDIT: " + student.getImage().toString());
        userService.saveOrUpdate(student);

        return "redirect:/userList";
    }
}
