package net.therap.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author masud.rana
 * @since 28/6/21
 */
@Entity
@Table(name = "userExamRegistration")
@NamedQuery(name = "findByRegAndUserId", query = "SELECT ue FROM UserExamRegistration  ue WHERE ue.exam.id =:examId AND ue.student.id =:studentId")
public class UserExamRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Student student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isNew() {
        return this.id == 0;
    }
}

