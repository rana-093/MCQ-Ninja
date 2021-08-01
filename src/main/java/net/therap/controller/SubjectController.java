package net.therap.controller;

import net.therap.model.Subject;
import net.therap.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static net.therap.util.Helper.*;

/**
 * @author masud.rana
 * @since 1/7/21
 */
@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, "name", new StringTrimmerEditor(true));
    }

    @GetMapping("/subject")
    public String show(@RequestParam(value = "id", required = false, defaultValue = "0") int id, ModelMap model) {
        Subject subject = (id == 0) ? new Subject() : subjectService.find(id);

        if (Objects.isNull(subject)) {
            return FOUR_O_FOUR;
        }

        model.addAttribute("subject", subject);

        return SUBJECT;
    }

    @PostMapping("/subject")
    public String process(@Valid @ModelAttribute("subject") Subject subject,
                          BindingResult result, ModelMap model, RedirectAttributes rttr) {
        if (result.hasErrors()) {
            model.addAttribute("subject", subject);
            return SUBJECT;
        }
        subjectService.saveOrUpdate(subject);
        rttr.addFlashAttribute("alert", 1);

        return "redirect:/subjectList";
    }

    @GetMapping(value = "/subjectList")
    public String showAll(Model model) {
        List<Subject> subjectList = subjectService.findAll();
        model.addAttribute("subjectList", subjectList);

        return SUBJECT_LIST;
    }
}
