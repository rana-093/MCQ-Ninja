package net.therap.dao;

import net.therap.model.Answer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author masud.rana
 * @since 12/7/21
 */
@Repository
public class AnswerDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveOrUpdate(Answer answer) {
        if (answer.isNew()) {
            em.persist(answer);
        } else {
            em.merge(answer);
        }
    }
}
