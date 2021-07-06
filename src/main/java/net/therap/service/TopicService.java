package net.therap.service;

import net.therap.dao.TopicDao;
import net.therap.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author masud.rana
 * @since 1/7/21
 */
@Service
public class TopicService {

    @Autowired
    private TopicDao topicDao;

    public Topic find(int id) {
        return topicDao.find(id);
    }

    public List<Topic> findAll() {
        return topicDao.findAll();
    }

    public void saveOrUpdate(Topic topic) {
        topicDao.saveOrUpdate(topic);
    }
}
