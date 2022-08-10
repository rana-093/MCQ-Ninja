package net.therap.converter;

import net.therap.model.Topic;
import net.therap.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author masud.rana
 * @since 8/7/21
 */
@Component
public class TopicConverter implements Converter<String, Topic> {

    @Autowired
    private TopicService topicService;

    @Override
    public Topic convert(String source) {
        int id = Integer.parseInt(source);
        return topicService.find(id);
    }
}
