package net.therap.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import net.therap.command.ResultCommand;
import net.therap.model.*;
import net.therap.service.AnswerService;
import net.therap.service.ExamRegService;
import net.therap.service.ExamService;
import net.therap.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 14/7/21
 */
@Controller
public class ResultController {

    @Autowired
    private ResultService resultService;

    @Autowired
    private ExamRegService examRegService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ExamService examService;

    @GetMapping(value = "/userExamList")
    public String userExamList(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        int userId = Integer.parseInt(session.getAttribute("userId").toString());

        List<Result> resultList = resultService.findByUserId(userId);

        model.addAttribute("resultList", resultList);
        return "result/userExamList";
    }

    @GetMapping(value = "/showDetails")
    public String showDetails(@RequestParam(value = "id", required = false, defaultValue = "0") int examId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        int userId = Integer.parseInt(session.getAttribute("userId").toString());

        System.out.println("Exam Id: " + examId + " , userId: " + userId);

        UserExamRegistration userExamRegistration = examRegService.findById(examId, userId);
        if (Objects.isNull(userExamRegistration)) {
            return "warnings/notRegistered";
        }

        Exam exam = examService.find(examId);
        List<Question> questions = exam.getQuestions();
        List<ResultCommand> resultCommandList = new ArrayList<>();

        for (Question question : questions) {
            Answer answer = answerService.findByQuestionId(userExamRegistration, question);
            resultCommandList.add(new ResultCommand(answer, question));
        }

        model.addAttribute("resultCommandList", resultCommandList);

        return "result/showDetails";
    }
}
