package net.therap.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
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

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime; //int Value

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @OneToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @OneToMany
    @JoinColumn(name = "exam_id")
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
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

    public boolean isNew() {
        return this.id == 0;
    }
}
