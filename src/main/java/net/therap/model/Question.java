package net.therap.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 28/6/21
 */
@Entity
@Table(name = "question")
@NamedQuery(name = "findByTopicId",
        query = "SELECT q FROM Question q " +
                "WHERE q.topic.id =:topicId")
@NamedQuery(name = "findAllQuestions", query = "SELECT q FROM Question q")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @NotNull
    @Size(min = 3, max = 300, message = "Content should be valid in Size")
    private String content;

    @ManyToMany(mappedBy = "questions")
    public List<Exam> exams = new ArrayList<>();

    @Valid
    @OneToMany(mappedBy = "question",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Option> optionList = new ArrayList<>();

    @NotNull
    @Size(min = 8, max = 500, message = "not more than length 500")
    private String explanation;

    @NotNull
    private int correctOption;

    private boolean used;

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }

    public void addOption(Option option) {
        option.setQuestion(this);
        optionList.add(option);
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }

    public boolean isNew() {
        return this.id == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id && correctOption == question.correctOption && used == question.used && Objects.equals(topic, question.topic) && Objects.equals(content, question.content) && Objects.equals(optionList, question.optionList) && Objects.equals(explanation, question.explanation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, content, optionList, explanation, correctOption, used);
    }
}
