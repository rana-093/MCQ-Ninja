package net.therap.service;

import net.therap.dao.QuestionDao;
import net.therap.model.Exam;
import net.therap.model.Option;
import net.therap.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author masud.rana
 * @since 3/7/21
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public Question find(int id) {
        return questionDao.find(id);
    }

    public List findAll() {
        return questionDao.findAll();
    }

    public List<Question> findByTopicId(int id) {
        return questionDao.findByTopicId(id);
    }

    public void saveOrUpdate(Question q) {
        if (q.isNew()) {
            int cur = q.getCorrectOption();
            Option option = q.getOptionList().get(cur);
            option.setCorrect(true);
            for (Option op : q.getOptionList()) {
                op.setQuestion(q);
            }
        }
        questionDao.saveOrUpdate(q);
    }

    public void remove(int questionId) {
        Question question = questionDao.find(questionId);
        for (Exam exam : question.getExams()) {
            exam.getQuestions().remove(question);
        }
        questionDao.remove(questionId);
    }
}
