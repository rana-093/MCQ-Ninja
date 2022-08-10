package net.therap.dao;

import net.therap.model.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author masud.rana
 * @since 12/7/21
 */
@Repository
public class ResultDao {

    @PersistenceContext
    private EntityManager em;

    public Result findByUserExamId(int examId, int studentId) {
        TypedQuery<Result> resultTypedQuery = em.createNamedQuery("findByUserExamId", Result.class);

        resultTypedQuery.setParameter("examId", examId);
        resultTypedQuery.setParameter("studentId", studentId);
        List<Result> resultList = resultTypedQuery.getResultList();
        return resultList.size() == 0 ? null : resultList.get(0);
    }

    public List<Result> findByUserId(int studentId) {
        TypedQuery<Result> resultTypedQuery = em.createNamedQuery("findByUserId", Result.class);
        resultTypedQuery.setParameter("studentId", studentId);
        return resultTypedQuery.getResultList();
    }

    @Transactional
    public void saveOrUpdate(Result result) {
        if (result.isNew()) {
            em.persist(result);
        } else {
            em.merge(result);
        }
    }
}
