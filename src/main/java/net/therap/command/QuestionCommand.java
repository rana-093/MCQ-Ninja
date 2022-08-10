package net.therap.command;

import net.therap.model.Answer;
import net.therap.model.Question;

import java.io.Serializable;

/**
 * @author masud.rana
 * @since 11/7/21
 */
public class QuestionCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    private Question question;

    private String choosenOp;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getChoosenOp() {
        return choosenOp;
    }

    public void setChoosenOp(String choosenOp) {
        this.choosenOp = choosenOp;
    }
}
