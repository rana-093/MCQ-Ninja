package net.therap.dao;

import net.therap.model.Subject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 1/7/21
 */
@Repository
public class SubjectDao {

    @PersistenceContext
    private EntityManager em;

    public Subject find(int id) {
        return em.find(Subject.class, id);
    }

    public List findAll() {
        Query allSubjects = em.createNamedQuery("findAllSubjects");
        return allSubjects.getResultList();
    }

    @Transactional
    public void saveOrUpdate(Subject subject) {
        if (subject.isNew()) {
            em.persist(subject);
        } else {
            em.merge(subject);
        }
    }

    @Transactional
    public void remove(int subjectId) {
        Subject subject = em.getReference(Subject.class, subjectId);
        if (Objects.nonNull(subject)) {
            em.remove(subject);
        }
    }
}
