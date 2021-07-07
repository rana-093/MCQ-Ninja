package net.therap.controller;

import net.therap.model.Exam;
import net.therap.model.Question;
import net.therap.model.Topic;
import net.therap.propertyEditor.QuestionEditor;
import net.therap.propertyEditor.TopicEditor;
import net.therap.service.ExamService;
import net.therap.service.QuestionService;
import net.therap.service.TopicService;
import net.therap.validation.ExamValidator;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 4/7/21
 */
@Controller
@SessionAttributes({"topicList", "exam", "questionList"})
public class ExamController {

    @Autowired
    private TopicEditor topicEditor;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ExamService examService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamValidator examValidator;

    @Autowired
    private QuestionEditor questionEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Topic.class, topicEditor);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        simpleDateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
        binder.registerCustomEditor(Question.class, questionEditor);
        binder.addValidators(examValidator);
        binder.setDisallowedFields("id");
    }

    @GetMapping(value = "/examList")
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
        setUpReferenceData(model);
        model.addAttribute("exam", exam);
        return "exam/exam";
    }

    @PostMapping(value = "/exam")
    public String process(@Valid @ModelAttribute("exam") Exam exam,
                          BindingResult result,
                          @SessionAttribute("topicList") List<Topic> topicList,
                          Model model) {
        if (result.hasErrors()) {
            return "exam/exam";
        }
        return "redirect:/examTopic";
    }

    @GetMapping(value = "/examTopic")
    public String setExam(@ModelAttribute("exam") Exam exam, Model model) {
        List<Question> questionList = questionService.findByTopicId(exam.getTopic().getId());
        questionList.addAll(exam.getQuestions());
        model.addAttribute("questionList", questionList);
        return "exam/chooseQuestions";
    }

    @PostMapping(value = "/examTopic")
    public String setQuestions(@Valid @ModelAttribute("exam") Exam exam, SessionStatus status) {
        if (exam.getQuestions().size() == 0) {
            return "exam/chooseQuestions";
        }
        examService.saveOrUpdate(exam);
        status.setComplete();
        return "redirect:/examList";
    }

    private void setUpReferenceData(Model model) {
        List<Topic> topicList = topicService.findAll();
        model.addAttribute("topicList", topicList);
    }
}
