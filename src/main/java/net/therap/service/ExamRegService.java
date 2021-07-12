package net.therap.service;

import net.therap.dao.ExamRegDao;
import net.therap.model.UserExamRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author masud.rana
 * @since 6/7/21
 */
@Service
public class ExamRegService {

    @Autowired
    private ExamRegDao examRegDao;

    @Transactional
    public void saveOrUpdate(UserExamRegistration userExamRegistration) {
        examRegDao.saveOrUpdate(userExamRegistration);
    }

}
