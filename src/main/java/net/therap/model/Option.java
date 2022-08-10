package net.therap.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 29/6/21
 */
@Entity
@Table(name = "questionOption")
@NamedQuery(name = "findByQuestion",
        query = "SELECT o FROM Option o WHERE o.question.id =:questionId")
public class Option implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 100, message = "Invalid Option Size")
    private String content;

    private boolean correct;

    @ManyToOne(fetch = FetchType.EAGER)
    private Question question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return id == option.id && correct == option.correct && Objects.equals(content, option.content) && Objects.equals(question, option.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, correct, question);
    }
}
