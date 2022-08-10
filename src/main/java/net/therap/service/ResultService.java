package net.therap.service;

import net.therap.dao.ResultDao;
import net.therap.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author masud.rana
 * @since 12/7/21
 */
@Service
public class ResultService {

    @Autowired
    private ResultDao resultDao;

    public Result findByUserExamId(int examId, int studentId) {
        return resultDao.findByUserExamId(examId, studentId);
    }

    public List<Result> findByUserId(int userId) {
        return resultDao.findByUserId(userId);
    }

    public void saveOrUpdate(Result result) {
        resultDao.saveOrUpdate(result);
    }
}
