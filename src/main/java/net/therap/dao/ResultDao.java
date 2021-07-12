package net.therap.dao;

import net.therap.model.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author masud.rana
 * @since 12/7/21
 */
@Repository
public class ResultDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveOrUpdate(Result result) {
        if (result.isNew()) {
            em.persist(result);
        } else {
            em.merge(result);
        }
    }
}
