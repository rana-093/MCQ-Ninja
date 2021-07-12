package net.therap.service;

import net.therap.dao.AnswerDao;
import net.therap.dao.ExamRegDao;
import net.therap.model.Answer;
import net.therap.model.Exam;
import net.therap.model.Student;
import net.therap.model.UserExamRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author masud.rana
 * @since 12/7/21
 */
@Service
public class AnswerService {

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private ExamRegDao examRegDao;

    public void saveOrUpdate(int examId, int userId, Answer answer) {
        UserExamRegistration userExamRegistration = examRegDao.findById(examId, userId);
        answer.setUserExamRegistration(userExamRegistration);
        answerDao.saveOrUpdate(answer);
    }
}
