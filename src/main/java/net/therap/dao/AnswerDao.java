package net.therap.dao;

import net.therap.model.Answer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author masud.rana
 * @since 12/7/21
 */
@Repository
public class AnswerDao {

    @PersistenceContext
    private EntityManager em;

    public Answer find(int examRegId, int questionId) {
        TypedQuery<Answer> answerTypedQuery = em.createNamedQuery("findByQuestionId", Answer.class);
        answerTypedQuery.setParameter("examRegId", examRegId);
        answerTypedQuery.setParameter("questionId", questionId);
        return answerTypedQuery.getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Answer answer) {
        if (answer.isNew()) {
            em.persist(answer);
        } else {
            em.merge(answer);
        }
    }
}
