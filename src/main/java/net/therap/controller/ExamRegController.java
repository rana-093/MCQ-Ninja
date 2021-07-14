package net.therap.controller;

import net.therap.model.Exam;
import net.therap.model.Student;
import net.therap.service.ExamRegService;
import net.therap.service.ExamService;
import net.therap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 6/7/21
 */
@Controller
public class ExamRegController {

    @Autowired
    private ExamRegService examRegService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExamService examService;

    @GetMapping(value = "/regExam")
    public String regExam(@RequestParam(value = "id",
            required = false,
            defaultValue = "0") int examId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        int userId = Integer.parseInt(session.getAttribute("userId").toString());

        if (Objects.nonNull(examRegService.findById(examId, userId))) {
            return "warnings/registered";
        }

        Student student = userService.findStudent(userId);
        Exam exam = examService.find(examId);
        examRegService.saveOrUpdate(exam, student);

        return "warnings/registered";
    }
}
