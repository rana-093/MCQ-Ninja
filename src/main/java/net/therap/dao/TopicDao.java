package net.therap.dao;

import net.therap.model.Topic;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author masud.rana
 * @since 1/7/21
 */
@Repository
public class TopicDao {

    @PersistenceContext
    private EntityManager em;

    public Topic find(int id) {
        return em.find(Topic.class, id);
    }

    public Topic findByTopicName(String name) {
        Query topicQuery = em.createNamedQuery("findByTopicName", Topic.class);
        return (Topic) topicQuery.getResultList().get(0);
    }

    public List<Topic> findAll() {
        Query allTopics = em.createNamedQuery("findAllTopics");
        return allTopics.getResultList();
    }

    @Transactional
    public void saveOrUpdate(Topic topic) {
        if (topic.isNew()) {
            em.persist(topic);
        } else {
            em.merge(topic);
        }
    }
}
