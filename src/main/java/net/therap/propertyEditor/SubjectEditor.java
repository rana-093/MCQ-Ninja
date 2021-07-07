package net.therap.propertyEditor;

import net.therap.dao.SubjectDao;
import net.therap.model.Subject;
import net.therap.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * @author masud.rana
 * @since 1/7/21
 */
@Component
public class SubjectEditor extends PropertyEditorSupport {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public String getAsText() {
        return getValue() == null ? "0" : String.valueOf(((Subject) getValue()).getId());
    }

    @Override
    public void setAsText(String subjectId) throws IllegalArgumentException {
        int id = Integer.parseInt(subjectId);
        Subject subject = subjectDao.find(id);
        setValue(subject);
    }
}
