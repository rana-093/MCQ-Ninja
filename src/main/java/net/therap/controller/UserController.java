package net.therap.controller;

import net.therap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author masud.rana
 * @since 29/6/21
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
}
