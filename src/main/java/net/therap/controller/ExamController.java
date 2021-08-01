package net.therap.controller;

import net.therap.command.ExamCommand;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static net.therap.util.Helper.fileURL;

/**
 * @author masud.rana
 * @since 4/7/21
 */
@Controller
@SessionAttributes({"examCommand"})
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
        ExamCommand examCommand = new ExamCommand();

        Exam exam = (id == 0) ? new Exam() : examService.find(id);
        examCommand.setExam(exam);
        if (exam.isNew()) {
            exam.setTopic(new Topic());
        }

        List<Topic> topicList = topicService.findAll();
        examCommand.setTopicList(topicList);

        List<Question> questions = exam.getQuestions();
        List<Question> questionList = questionService.findByTopicId(exam.getTopic().getId());

        for (Question question : questionList) {
            question.setUsed(questions.contains(question));
            if (!questions.contains(question)) {
                questions.add(question);
            }
        }
        examCommand.setQuestionList(questions);

        model.addAttribute("examCommand", examCommand);
        return "exam/exam";
    }

    @PostMapping(value = "/exam")
    public String process(@Valid @ModelAttribute("examCommand") ExamCommand examCommand,
                          BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "exam/exam";
        }
        if (examCommand.getQuestionList().size() == 0) { //new Exam create kortese eikhane.
            examCommand.setQuestionList(questionService.findByTopicId(
                    examCommand.getExam().
                            getTopic().
                            getId()));
        }

        return "redirect:/examTopic";
    }

    @GetMapping(value = "/examTopic")
    public String setExam(@ModelAttribute("examCommand") ExamCommand examCommand) {
        return "exam/chooseQuestions";
    }

    @PostMapping(value = "/examTopic")
    public String setQuestions(@Valid @ModelAttribute("examCommand") ExamCommand examCommand,
                               SessionStatus status) throws IOException {
        if (examCommand.getQuestionList().size() == 0) {
            return "exam/chooseQuestions";
        }

        String fileName = examCommand.getExam().getInstructionsFile().getOriginalFilename();

        if (Objects.nonNull(fileName)) {
            String destURL = fileURL + "/" + fileName;
            MultipartFile srcFile = examCommand.getExam().getInstructionsFile();

            examService.saveOrUpdateWithInstructionsFile(examCommand.getExam(), destURL, srcFile);
        } else {
            examService.saveOrUpdate(examCommand.getExam());
        }

        status.setComplete();

        return "redirect:/examList";
    }

    @GetMapping(value = "/deleteExam")
    public String questionRemove(@RequestParam("id") int examId) {
        examService.remove(examId);

        return "redirect:/questionList";
    }
}
