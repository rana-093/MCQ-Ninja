package net.therap.service;

import net.therap.dao.ExamRegDao;
import net.therap.model.Exam;
import net.therap.model.Student;
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

    public UserExamRegistration findById(int examId, int studentId) {
        return examRegDao.findById(examId, studentId);
    }

    @Transactional
    public void saveOrUpdate(Exam exam, Student student) {
        UserExamRegistration userExamRegistration = new UserExamRegistration();
        userExamRegistration.setExam(exam);
        userExamRegistration.setStudent(student);
        examRegDao.saveOrUpdate(userExamRegistration);
    }

}
