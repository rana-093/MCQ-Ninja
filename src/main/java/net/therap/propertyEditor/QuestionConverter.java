package net.therap.propertyEditor;

import net.therap.model.Question;
import net.therap.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author masud.rana
 * @since 7/7/21
 */
@Component
public class QuestionConverter implements Converter<String, Question> {

    @Autowired
    private QuestionService questionService;

    @Override
    public Question convert(String source) {
        System.out.println("Source id: " + source);
        int id = Integer.parseInt(source);
        return questionService.find(id);
    }
}
