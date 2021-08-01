package net.therap.controller;

import net.therap.model.User;
import net.therap.service.UserService;
import net.therap.util.Helper;
import net.therap.validation.LogInValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static net.therap.util.Helper.*;

/**
 * @author masud.rana
 * @since 29/6/21
 */
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogInValidator logInValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(logInValidator);
        binder.setDisallowedFields("name");
    }

    @GetMapping(value = "/login")
    public String show(Model model) {
        model.addAttribute("user", new User());
        return LOG_IN;
    }

    @PostMapping(value = "/login")
    public String process(@Valid @ModelAttribute User user,
                          BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return LOG_IN;
        }

        User currentUser = userService.findByEmail(user.getEmail());
        boolean isAdmin = userService.isAdmin(currentUser.getEmail());

        HttpSession session = request.getSession();

        session.setAttribute("role", (isAdmin) ? Helper.Role.ADMIN : Helper.Role.STUDENT);
        session.setAttribute("userId", currentUser.getId());
        return "redirect:/home";
    }

    @GetMapping(value = "/logout")
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping(value = "/restricted")
    public String restricted() {
        return RESTRICTED;
    }

    @GetMapping(value = "/fourOfour")
    public String fourOfour() {
        return FOUR_O_FOUR;
    }
}
