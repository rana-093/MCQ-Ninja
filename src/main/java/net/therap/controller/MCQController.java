package net.therap.controller;

import net.therap.command.MCQCommand;
import net.therap.command.QuestionCommand;
import net.therap.dao.OptionDao;
import net.therap.model.*;
import net.therap.service.AnswerService;
import net.therap.service.ExamService;
import net.therap.service.ResultService;
import net.therap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 10/7/21
 */
@Controller
@SessionAttributes("mcqCommand")
public class MCQController {

    @Autowired
    private ExamService examService;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private ResultService resultService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerService answerService;

    @GetMapping(value = "/mcq")
    public String showMcq(@RequestParam(value = "id", required = false, defaultValue = "0") int id, Model model) {
        Exam exam = (id == 0) ? new Exam() : examService.find(id);
        MCQCommand mcqCommand = new MCQCommand();
        mcqCommand.setExam(exam);
        mcqCommand.setQuestionCommandList(exam.getQuestions());
        String date = formateDate(exam.getEndTime());
        model.addAttribute("date", date);
        model.addAttribute("mcqCommand", mcqCommand);
        return "mcq/mcq";
    }

    @PostMapping(value = "/mcq")
    public void processMcq(@ModelAttribute("mcqCommand") MCQCommand mcqCommand,
                           HttpServletRequest request, SessionStatus status) {
        int score = 0;
        HttpSession session = request.getSession(false);
        int userId = Integer.parseInt(session.getAttribute("userId").toString());
        Student student = userService.findStudent(userId);
        for (QuestionCommand questionCommand : mcqCommand.getQuestionCommandList()) {
            List<Option> optionList = optionDao.findByQuestion(questionCommand.getQuestion().getId());
            Answer answer = new Answer();
            answer.setChoosenOption(questionCommand.getChoosenOp());
            answer.setQuestion(questionCommand.getQuestion());
            answer.setCorrect(false);
            for (Option option : optionList) {
                if (option.isCorrect() && option.getContent().equals(questionCommand.getChoosenOp())) {
                    score += 1;
                    answer.setCorrect(true);
                }
            }
            answerService.saveOrUpdate(mcqCommand.getExam().getId(), userId, answer);
        }
        Result result = new Result();
        result.setExam(mcqCommand.getExam());
        result.setStudent(student);
        result.setScore(score);
        resultService.saveOrUpdate(result);
        status.setComplete();
    }

    private String formateDate(Date date) { //Needed to convert Java Date to JavaScript Date format!!!
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
        String dueTime = simpleDateFormat.format(date);
        boolean isPM = dueTime.substring(dueTime.length() - 2).equals("PM");
        int hours = Integer.parseInt(dueTime.substring(11, 13));
        if (isPM) {
            hours = (hours < 12) ? (hours + 12) : hours;
        }
        String hour = String.valueOf(hours);
        String month = String.valueOf(Integer.parseInt(dueTime.substring(5, 7)) - 1); //JS Month format is MOD 12 format. Hence Need to subtract 1 from Java Month.
        String tempTime = dueTime.substring(0, 5) + month + dueTime.substring(7, 11);
        String temp = dueTime.substring(13, dueTime.length() - 2);
        dueTime = tempTime + hour + temp;
        dueTime = dueTime.replace('-', ',');
        dueTime = dueTime.replace(':', ',');
        dueTime = dueTime.replace(' ', ',');
        return dueTime;
    }
}
