package net.therap.dao;

import net.therap.model.Admin;
import net.therap.model.Student;
import net.therap.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ObjectError;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 29/6/21
 */
@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager em;

    public boolean isAdmin(int id) {
        return Objects.nonNull(em.find(Admin.class, id));
    }

    public boolean isStudent(int id) {
        return Objects.nonNull(em.find(Student.class, id));
    }

    public Student findStudent(int userId) {
        return em.find(Student.class, userId);
    }

    public List findAllStudents() {
        Query allStudents = em.createNamedQuery("findAllStudents");
        return allStudents.getResultList();
    }

    public User findAdmin(int id) {
        return em.find(Admin.class, id);
    }

    public User findByEmail(String email, boolean isAdmin) {
        String queryString = "";
        if (isAdmin) {
            queryString = "FROM Admin a WHERE a.email =:email";
        } else {
            queryString = "FROM Student s WHERE s.email =:email";
        }
        Query query = em.createQuery(queryString);
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        return users.size() == 0 ? null : users.get(0);
    }

    @Transactional
    public void saveOrUpdate(User user) {
        if (user.isNew()) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }
}
