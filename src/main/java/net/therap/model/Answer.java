package net.therap.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author masud.rana
 * @since 28/6/21
 */
@Entity
@Table(name = "answer")
@NamedQuery(name = "findByQuestionId", query = "SELECT a FROM Answer a WHERE a.question.id =:questionId AND a.userExamRegistration.id =:examRegId")
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userExamReg_id")
    private UserExamRegistration userExamRegistration;

    private String choosenOption;

    private boolean correct;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getChoosenOption() {
        return choosenOption;
    }

    public void setChoosenOption(String choosenOption) {
        this.choosenOption = choosenOption;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserExamRegistration getUserExamRegistration() {
        return userExamRegistration;
    }

    public void setUserExamRegistration(UserExamRegistration userExamRegistration) {
        this.userExamRegistration = userExamRegistration;
    }

    public boolean isNew() {
        return this.id == 0;
    }
}
