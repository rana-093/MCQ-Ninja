package net.therap.controller;

import net.therap.model.Subject;
import net.therap.model.Topic;
import net.therap.propertyEditor.SubjectEditor;
import net.therap.service.SubjectService;
import net.therap.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectEditor subjectEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Subject.class, subjectEditor);
    }

    @GetMapping("/topic")
    public String show(@RequestParam(value = "id", required = false, defaultValue = "0") int id,
                       ModelMap model) {
        Topic topic = (id == 0) ? new Topic() : topicService.find(id);

        if (Objects.isNull(topic)) {
            return FOUR_O_FOUR;
        }

        setupReferenceData(model);
        model.addAttribute("topic", topic);

        return TOPIC;
    }

    @PostMapping("/topic")
    public String process(@Valid @ModelAttribute("topic") Topic topic,
                          BindingResult result, ModelMap model, RedirectAttributes rttr) {
        if (result.hasErrors()) {
            setupReferenceData(model);
            model.addAttribute("topic", topic);
            return TOPIC;
        }

        topicService.saveOrUpdate(topic);
        rttr.addFlashAttribute("alert", 1);

        return REDIRECT_TOPIC_LIST;
    }

    @GetMapping(value = "/topicList")
    public String showAll(Model model) {
        List topicList = topicService.findAll();
        model.addAttribute("topicList", topicList);

        return TOPIC_LIST;
    }

    private void setupReferenceData(ModelMap model) {
        List subjectList = subjectService.findAll();
        model.addAttribute("subjectList", subjectList);
    }
}
