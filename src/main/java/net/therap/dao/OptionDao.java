package net.therap.dao;

import net.therap.model.Option;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author masud.rana
 * @since 12/7/21
 */
@Repository
public class OptionDao {

    @PersistenceContext
    private EntityManager em;

    public List<Option> findByQuestion(int questionId) {
        System.out.println("IN OptionDAO: " + questionId);
        TypedQuery<Option> optionList = em.createNamedQuery("findByQuestion", Option.class);
        optionList.setParameter("questionId", questionId);
        return optionList.getResultList();
    }
}
