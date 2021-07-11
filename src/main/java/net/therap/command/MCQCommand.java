package net.therap.command;

import net.therap.model.Answer;
import net.therap.model.Exam;
import net.therap.model.Question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author masud.rana
 * @since 10/7/21
 */
public class MCQCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    private Exam exam;

    private List<QuestionCommand> questionCommandList;

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<QuestionCommand> getQuestionCommandList() {
        return questionCommandList;
    }

    public void setQuestionCommandList(List<Question> questions) {
        this.questionCommandList = new ArrayList<>();
        for (Question question : questions) {
            QuestionCommand questionCommand = new QuestionCommand();
            questionCommand.setQuestion(question);
            questionCommandList.add(questionCommand);
        }
    }
}
