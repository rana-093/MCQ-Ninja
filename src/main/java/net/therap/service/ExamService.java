package net.therap.service;

import net.therap.dao.ExamDao;
import net.therap.dao.QuestionDao;
import net.therap.model.Exam;
import net.therap.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

/**
 * @author masud.rana
 * @since 4/7/21
 */
@Service
public class ExamService {

    @Autowired
    private ExamDao examDao;

    @Autowired
    private QuestionDao questionDao;

    public Exam find(int id) {
        return examDao.find(id);
    }

    public List<Exam> findAllUpcomingExams() {
        return examDao.findAllUpcomingExams();
    }

    public List<Exam> findAllRunningExams() {
        return examDao.findAllRunningExams();
    }

    public List<Exam> findAllPastExams() {
        return examDao.findAllPastExams();
    }

    public void saveOrUpdate(Exam exam) {
        List<Question> questionList = questionDao.findByTopicId(exam.getTopic().getId());
        exam.setQuestions(questionList);
        examDao.saveOrUpdate(exam);
    }

    public void remove(int id) {
        examDao.remove(id);
    }
}

