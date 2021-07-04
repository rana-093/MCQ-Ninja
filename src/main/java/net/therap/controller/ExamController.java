package net.therap.controller;

import net.therap.model.Exam;
import net.therap.model.Topic;
import net.therap.propertyEditor.TopicEditor;
import net.therap.service.ExamService;
import net.therap.service.TopicService;
import net.therap.validation.ExamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author masud.rana
 * @since 4/7/21
 */
@Controller
public class ExamController {

    @Autowired
    private TopicEditor topicEditor;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamValidator examValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Topic.class, topicEditor);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        simpleDateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
        binder.addValidators(examValidator);
    }

    @GetMapping(value = "examList")
    public String showAll(Model model) {
        List<Exam> runningExams = examService.findAllRunningExams();
        List<Exam> upcomingExams = examService.findAllUpcomingExams();
        List<Exam> pastExams = examService.findAllPastExams();
        model.addAttribute("runningExamList", runningExams);
        model.addAttribute("upcomingExamList", upcomingExams);
        model.addAttribute("pastExamList", pastExams);
        return "exam/examList";
    }

    @GetMapping(value = "/exam")
    public String show(@RequestParam(value = "id", required = false, defaultValue = "0") int id, Model model) {
        Exam exam = (id == 0) ? new Exam() : examService.find(id);
        setUpReferenceData(model, exam);
        return "exam/exam";
    }

    @PostMapping(value = "/exam")
    public String process(@Valid @ModelAttribute("exam") Exam exam,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            setUpReferenceData(model, exam);
            return "exam/exam";
        }
        examService.saveOrUpdate(exam);
        return "redirect:/examList";
    }

    private void setUpReferenceData(Model model, Exam exam) {
        List<Topic> topicList = topicService.findAll();
        model.addAttribute("exam", exam);
        model.addAttribute("topicList", topicList);
    }
}
