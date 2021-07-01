package net.therap.controller;

import net.therap.model.Student;
import net.therap.model.User;
import net.therap.service.UserService;
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

/**
 * @author masud.rana
 * @since 29/6/21
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/userList")
    public String showAll(Model model) {
        List studentList = userService.findAllStudents();
        System.out.println(studentList+" , , , , , ");
        model.addAttribute("studentList", studentList);
        return "user/studentList";
    }

    @GetMapping(value = "/user")
    public String show(@RequestParam(defaultValue = "0") int id, Model model) {
        Student student = (id == 0) ? new Student() : userService.findStudent(id);
        if (Objects.isNull(student)) {
            return "warnings/404";
        }
        System.out.println("student: "+ student.getEmail()+" , id: "+ id);
        model.addAttribute("student", student);
        return "user/student";
    }

    @PostMapping(value = "/user")
    public String process(@Valid @ModelAttribute("student") Student student,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", student);
            return "user/student";
        }
        userService.saveOrUpdate(student);
        return "redirect:/userList";
    }
}
