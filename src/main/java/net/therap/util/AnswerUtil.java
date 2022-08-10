package net.therap.util;

import net.therap.model.Answer;

/**
 * @author masud.rana
 * @since 13/7/21
 */
public class AnswerUtil {

    private int examId;

    private int userId;

    private Answer answer;

    public AnswerUtil(int examId, int userId, Answer answer) {
        this.examId = examId;
        this.userId = userId;
        this.answer = answer;
    }


    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
