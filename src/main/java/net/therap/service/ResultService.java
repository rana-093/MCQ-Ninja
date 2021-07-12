package net.therap.service;

import net.therap.dao.ResultDao;
import net.therap.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author masud.rana
 * @since 12/7/21
 */
@Service
public class ResultService {

    @Autowired
    private ResultDao resultDao;

    public void saveOrUpdate(Result result) {
        resultDao.saveOrUpdate(result);
    }
}
