package net.therap.propertyEditor;

import net.therap.dao.TopicDao;
import net.therap.model.Subject;
import net.therap.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * @author masud.rana
 * @since 3/7/21
 */
@Component
public class TopicEditor extends PropertyEditorSupport {

    @Autowired
    private TopicDao topicDao;

    @Override
    public String getAsText() {
        return getValue() == null ? "0" : String.valueOf(((Topic) getValue()).getId());
    }

    @Override
    public void setAsText(String topicId) throws IllegalArgumentException {
        int id = Integer.valueOf(topicId);
        Topic topic = topicDao.find(id);
        setValue(topic);
    }
}
