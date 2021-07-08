package net.therap.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author masud.rana
 * @since 28/6/21
 */
@Entity
@Table(name = "exam")
@NamedQuery(name = "findAllUpcomingExams", query = "SELECT e FROM Exam e WHERE e.startTime >= current_timestamp ORDER BY e.startTime ASC")
@NamedQuery(name = "findAllRunningExams", query = "SELECT e FROM Exam e WHERE current_timestamp >= e.startTime AND current_timestamp <= e.endTime ORDER BY e.startTime ASC")
@NamedQuery(name = "findAllPastExams", query = "SELECT e FROM Exam e WHERE current_timestamp > e.endTime ORDER BY e.startTime ASC")
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 3, max = 100, message = "Exam Name Should be within 3 & 100")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime; //int Value

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @OneToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_id")
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Exam() {
        this.questions = new ArrayList<>();
        this.topic = new Topic();
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", topic=" + topic +
                ", questions=" + questions +
                '}';
    }

    public boolean isNew() {
        return this.id == 0;
    }


}
