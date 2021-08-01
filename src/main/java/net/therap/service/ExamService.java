package net.therap.service;

import net.therap.dao.AnswerDao;
import net.therap.dao.ExamDao;
import net.therap.dao.QuestionDao;
import net.therap.dao.ResultDao;
import net.therap.model.Exam;
import net.therap.model.Question;
import net.therap.model.Result;
import net.therap.util.AnswerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Query;
import java.io.IOException;
import java.nio.file.Paths;
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
    private AnswerService answerService;

    @Autowired
    private ResultService resultService;

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

    @Transactional
    public void saveOrUpdateWithInstructionsFile(Exam exam, String destPath, MultipartFile file) throws IOException {
        file.transferTo(Paths.get(destPath));
        exam.setInstructionsFilePath(file.getOriginalFilename());
        examDao.saveOrUpdate(exam);
    }

    public void saveOrUpdate(Exam exam) {
        examDao.saveOrUpdate(exam);
    }

    @Transactional
    public void saveExamDetails(List<AnswerUtil> answerUtilList, Result result) {
        for (AnswerUtil answerUtil : answerUtilList) {
            answerService.saveOrUpdate(answerUtil.getExamId(), answerUtil.getUserId(), answerUtil.getAnswer());
        }
        resultService.saveOrUpdate(result);
    }

    public void remove(int id) {
        Exam exam = examDao.find(id);
        exam.getQuestions().clear();
        examDao.remove(id);
    }
}

