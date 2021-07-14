package net.therap.dao;

import net.therap.model.User;
import net.therap.model.UserExamRegistration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 6/7/21
 */
@Repository
public class ExamRegDao {

    @PersistenceContext
    private EntityManager em;

    public UserExamRegistration findById(int examId, int studentId) {
        TypedQuery<UserExamRegistration> userExamRegistrationTypedQuery = em.createNamedQuery("findByRegAndUserId", UserExamRegistration.class);
        userExamRegistrationTypedQuery.setParameter("examId", examId);
        userExamRegistrationTypedQuery.setParameter("studentId", studentId);
        List<UserExamRegistration> userExamRegistrations = userExamRegistrationTypedQuery.getResultList();
        return userExamRegistrations.size() == 0 ? null : userExamRegistrations.get(0);
    }

    @Transactional
    public void saveOrUpdate(UserExamRegistration userExamRegistration) {
        if (userExamRegistration.isNew()) {
            em.persist(userExamRegistration);
        } else {
            em.merge(userExamRegistration);
        }
    }
}
