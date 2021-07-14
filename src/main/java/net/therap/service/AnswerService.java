package net.therap.service;

import net.therap.dao.AnswerDao;
import net.therap.dao.ExamRegDao;
import net.therap.model.*;
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

    public Answer findByQuestionId(UserExamRegistration userExamRegistration, Question question) {
        return answerDao.find(userExamRegistration.getId(), question.getId());
    }

    public void saveOrUpdate(int examId, int userId, Answer answer) {
        UserExamRegistration userExamRegistration = examRegDao.findById(examId, userId);
        answer.setUserExamRegistration(userExamRegistration);
        answerDao.saveOrUpdate(answer);
    }
}
