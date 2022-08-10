package net.therap.command;

import net.therap.model.Exam;
import net.therap.model.Question;
import net.therap.model.Topic;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author masud.rana
 * @since 8/7/21
 */
public class ExamCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Topic> topicList;

    private List<Question> questionList;

    @Valid
    private Exam exam;

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
