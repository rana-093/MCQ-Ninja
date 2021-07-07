package net.therap.dao;

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
 * @since 3/7/21
 */
@Repository
public class QuestionDao {

    @PersistenceContext
    private EntityManager em;

    public Question find(int id) {
        return em.find(Question.class, id);
    }

    public List<Question> findAll() {
        Query questionList = em.createNamedQuery("findAllQuestions");
        return questionList.getResultList();
    }

    public List<Question> findByTopicId(int id) {
        System.out.println("In DAO: " + id);
        TypedQuery<Question> questionList = em.createNamedQuery("findByTopicId", Question.class);
        questionList.setParameter("topicId", id);
        return questionList.getResultList();
    }

    @Transactional
    public void saveOrUpdate(Question q) {
        if (q.isNew()) {
            em.persist(q);
        } else {
            em.merge(q);
        }
    }

    @Transactional
    public void remove(int id) {
        Question question = em.getReference(Question.class, id);
        if (Objects.nonNull(question)) {
            em.remove(question);
        }
    }
}
