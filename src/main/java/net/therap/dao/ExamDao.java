package net.therap.dao;

import net.therap.model.Exam;
import net.therap.model.Question;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
        TypedQuery<Exam> pastExamsList = (TypedQuery<Exam>) em.createNamedQuery("findAllPastExams");
        return pastExamsList.getResultList();
    }

    @Transactional
    public void saveOrUpdate(Exam exam) {
        if (exam.isNew()) {
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
