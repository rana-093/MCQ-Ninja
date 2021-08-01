package net.therap.controller;

import net.therap.exception.NotFoundException;
import net.therap.model.Exam;
import net.therap.model.Option;
import net.therap.model.Question;
import net.therap.model.Topic;
import net.therap.propertyEditor.TopicEditor;
import net.therap.service.ExamService;
import net.therap.service.QuestionService;
import net.therap.service.TopicService;
import net.therap.util.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 3/7/21
 */
@Controller
@SessionAttributes({"question", "topicList"})
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private TopicEditor topicEditor;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ExamService examService;

    @Autowired
    private CSVReader csvReader;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
        binder.registerCustomEditor(Topic.class, topicEditor);
    }

    @GetMapping(value = "/questionList")
    public String showAll(Model model) {
        List<Question> questionList = questionService.findAll();
        model.addAttribute("questionList", questionList);
        return "question/questionList";
    }

    @GetMapping(value = "/question")
    public String show(@RequestParam(value = "id", required = false, defaultValue = "0")
                               int id, Model model) {
        Question question = (id == 0) ? new Question() : questionService.find(id);
        if (Objects.isNull(question)) {
            throw new RuntimeException();
        }
        setUpReferenceData(question, model);
        model.addAttribute("question", question);
        return "question/question";
    }

    @PostMapping(value = "/question")
    public String process(@Valid @ModelAttribute("question") Question question,
                          BindingResult result,
                          @SessionAttribute("question") Question q1,
                          @SessionAttribute("topicList") List<Topic> topicList,
                          Model model,
                          SessionStatus status) {

        if (result.hasErrors()) {
            return "question/question";
        }

        questionService.saveOrUpdate(question);
        status.setComplete();
        return "redirect:/questionList";
    }

    @GetMapping(value = "/questionRemove")
    public String questionRemove(@RequestParam("id") int questionId) {
        questionService.remove(questionId);
        return "redirect:/questionList";
    }

    @PostMapping(value = "/uploadCSV")
    public void uploadCSV(@RequestParam("file") MultipartFile file) throws IOException {
        List<Question> questionList = csvReader.fetchQuestionsFromCSV(file.getOriginalFilename());
        questionService.saveAll(questionList);
    }

    @GetMapping(value = "/removeFromExam/{examId}")
    public void removeFromExam(@RequestParam("id") int id, @PathVariable("examId") int examId) {
        Exam exam = examService.find(examId);
        List<Question> questions = exam.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getId() == id) {
                questions.remove(i);
            }
        }
        examService.saveOrUpdate(exam);
    }

    private void setUpReferenceData(Question question, Model model) {
        List<Topic> topicList = topicService.findAll();
        List<Option> options = question.getOptionList();
        if (options.size() == 0) {
            for (int i = 0; i < 4; i++) {
                Option option = new Option();
                question.addOption(option);
            }
        }
        model.addAttribute("topicList", topicList);
    }
}
