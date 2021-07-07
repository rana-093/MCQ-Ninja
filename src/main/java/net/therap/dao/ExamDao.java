package net.therap.dao;

import net.therap.model.Exam;
import net.therap.model.Question;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 4/7/21
 */
@Repository
public class ExamDao {

    @PersistenceContext
    private EntityManager em;

    public Exam find(int id) {
        return em.find(Exam.class, id);
    }

    public List<Exam> findAllUpcomingExams() {
        Query upcomingExamsList = em.createNamedQuery("findAllUpcomingExams");
        return upcomingExamsList.getResultList();
    }

    public List<Exam> findAllRunningExams() {
        Query runningExamsList = em.createNamedQuery("findAllRunningExams");
        return runningExamsList.getResultList();
    }

    public List<Exam> findAllPastExams() {
        Query pastExamsList = em.createNamedQuery("findAllPastExams");
        return pastExamsList.getResultList();
    }

    @Transactional
    public void saveOrUpdate(Exam exam) {
        if (exam.isNew()) {
            System.out.println("Okayayyayayyayyya");
            em.persist(exam);
        } else {
            em.merge(exam);
        }
    }

    public void remove(int id) {
        Exam exam = em.getReference(Exam.class, id);
        if (Objects.nonNull(exam)) {
            em.remove(exam);
        }
    }
}
