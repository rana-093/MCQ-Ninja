package net.therap.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author masud.rana
 * @since 28/6/21
 */
@Entity
@Table(name = "result")
@NamedQuery(name = "findByUserExamId", query = "SELECT r FROM Result r WHERE r.exam.id =:examId AND r.student.id =:studentId")
@NamedQuery(name = "findByUserId", query = "SELECT r FROM Result r WHERE r.student.id =:studentId")
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    private Student student;

    @OneToOne(fetch = FetchType.EAGER)
    private Exam exam;

    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isNew() {
        return this.id == 0;
    }
}
