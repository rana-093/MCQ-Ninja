package net.therap.controller;

import net.therap.service.ExamRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * @author masud.rana
 * @since 6/7/21
 */
@Controller
public class ExamRegController {

    @Autowired
    private ExamRegService examRegService;


    public void show(Model model) {

    }
}
