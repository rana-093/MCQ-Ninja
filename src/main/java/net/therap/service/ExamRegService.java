package net.therap.service;

import net.therap.dao.ExamRegDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author masud.rana
 * @since 6/7/21
 */
@Service
public class ExamRegService {

    @Autowired
    private ExamRegDao examRegDao;

}
