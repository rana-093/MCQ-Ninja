package net.therap.controller;

import lombok.extern.slf4j.Slf4j;
import net.therap.command.MCQCommand;
import net.therap.command.QuestionCommand;
import net.therap.dao.OptionDao;
import net.therap.model.*;
import net.therap.service.*;
import net.therap.util.AnswerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static net.therap.util.Helper.fileURL;

/**
 * @author masud.rana
 * @since 10/7/21
 */
@Controller
@SessionAttributes("mcqCommand")
public class MCQController {

    private static final Logger logger = LoggerFactory.getLogger(MCQController.class);

    @Autowired
    private ExamService examService;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ExamRegService examRegService;

    @Autowired
    private ResultService resultService;

    @GetMapping(value = "/openPDF/{fileName}")
    public ResponseEntity<InputStreamResource> openPDF(@PathVariable("fileName") String fileName) throws FileNotFoundException {

        logger.info("Okay From the OpenPDF");

        String filePath = fileURL + "/";
        File file = new File(filePath + fileName);
        HttpHeaders headers = new HttpHeaders();

        headers.add("content-disposition", "inline;filename=" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }


    @GetMapping(value = "/mcq")
    public String showMcq(@RequestParam(value = "id", required = false, defaultValue = "0") int examId,
                          Model model,
                          HttpServletRequest request) {
        Exam exam = (examId == 0) ? new Exam() : examService.find(examId);

//        HttpSession session = request.getSession(false);
//        int userId = Integer.parseInt(session.getAttribute("userId").toString());
//
//        if (Objects.isNull(examRegService.findById(examId, userId))) {
//            return "warnings/notRegistered";
//        }

//        if (Objects.nonNull(resultService.findByUserExamId(examId, userId))) {
//            return "warnings/alreadyAppeared";
//        }

        MCQCommand mcqCommand = new MCQCommand();
        mcqCommand.setExam(exam);
        mcqCommand.setQuestionCommandList(exam.getQuestions());

        String date = formateDate(exam.getEndTime());

        model.addAttribute("date", date);
        model.addAttribute("mcqCommand", mcqCommand);
        return "mcq/mcq";
    }

    @PostMapping(value = "/mcq")
    public String processMcq(@ModelAttribute("mcqCommand") MCQCommand mcqCommand,
                             HttpServletRequest request, SessionStatus status) {
        int score = 0;

        HttpSession session = request.getSession(false);
        int userId = Integer.parseInt(session.getAttribute("userId").toString());
        Student student = userService.findStudent(userId);

        List<AnswerUtil> answerUtilList = new ArrayList<>();

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

            answerUtilList.add(new AnswerUtil(mcqCommand.getExam().getId(), userId, answer));
        }

        Result result = new Result();
        result.setExam(mcqCommand.getExam());
        result.setStudent(student);
        result.setScore(score);

        examService.saveExamDetails(answerUtilList, result);
        status.setComplete();

        return "redirect:/userExamList";
    }

    private String formateDate(Date date) { //Needed to convert Java Date to JavaScript Date format!!!
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
        String dueTime = simpleDateFormat.format(date);

        boolean isPM = dueTime.endsWith("PM");
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
