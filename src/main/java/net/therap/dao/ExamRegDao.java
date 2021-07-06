package net.therap.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author masud.rana
 * @since 6/7/21
 */
@Repository
public class ExamRegDao {

    @PersistenceContext
    private EntityManager em;

}
