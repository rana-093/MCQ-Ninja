package net.therap.controller;

import net.therap.command.MCQCommand;
import net.therap.command.QuestionCommand;
import net.therap.model.Answer;
import net.therap.model.Exam;
import net.therap.model.Question;
import net.therap.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author masud.rana
 * @since 10/7/21
 */
@Controller
public class MCQController {

    @Autowired
    private ExamService examService;

    @GetMapping(value = "/mcq")
    public String showMcq(@RequestParam(value = "id", required = false, defaultValue = "0") int id, Model model) {
        Exam exam = (id == 0) ? new Exam() : examService.find(id);
        MCQCommand mcqCommand = new MCQCommand();
        mcqCommand.setExam(exam);
     //   System.out.println(exam.getName() + " , , , " + exam.getTopic());
        mcqCommand.setQuestionCommandList(exam.getQuestions());
        model.addAttribute("mcqCommand", mcqCommand);
        return "mcq/mcq";
    }

    @PostMapping(value = "/mcq")
    public void processMcq(@ModelAttribute("mcqCommand") MCQCommand mcqCommand) {
        for (QuestionCommand questionCommand : mcqCommand.getQuestionCommandList()) {
            System.out.println("Content: " + questionCommand.getQuestion().getContent() + " , Choosen Op: " + questionCommand.getChoosenOp());
        }
    }
}
