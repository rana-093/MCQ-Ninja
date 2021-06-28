package net.therap.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author masud.rana
 * @since 28/6/21
 */
@Entity
@Table(name = "answer")
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;

    private String choosenOption;

    private boolean result;

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

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
