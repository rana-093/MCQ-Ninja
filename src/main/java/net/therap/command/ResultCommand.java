package net.therap.command;

import net.therap.model.Answer;
import net.therap.model.Question;
import net.therap.model.Result;

/**
 * @author masud.rana
 * @since 14/7/21
 */
public class ResultCommand {

    private Answer answer;

    private Question question;

    public ResultCommand(Answer answer, Question question) {
        this.answer = answer;
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
