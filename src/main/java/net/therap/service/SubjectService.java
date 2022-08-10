package net.therap.service;

import net.therap.dao.SubjectDao;
import net.therap.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author masud.rana
 * @since 1/7/21
 */
@Service
public class SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    public Subject find(int id) {
        return subjectDao.find(id);
    }

    public List findAll() {
        return subjectDao.findAll();
    }

    public void saveOrUpdate(Subject subject) {
        subjectDao.saveOrUpdate(subject);
    }

    public void delete(int subjectId) {
        subjectDao.remove(subjectId);
    }
}
