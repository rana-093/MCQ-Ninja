package net.therap.propertyEditor;

import net.therap.dao.QuestionDao;
import net.therap.dao.SubjectDao;
import net.therap.model.Question;
import net.therap.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * @author masud.rana
 * @since 7/7/21
 */
@Component
public class QuestionEditor extends PropertyEditorSupport {

    @Autowired
    private QuestionDao questionDao;

    @Override
    public String getAsText() {
        return getValue() == null ? "0" : String.valueOf(((Question) getValue()).getId());
    }

    @Override
    public void setAsText(String subjectId) throws IllegalArgumentException {
        int id = Integer.parseInt(subjectId);
        System.out.println("QuestionID: " + id);
        Question question = questionDao.find(id);
        question.setUsed(true);
        setValue(question);
    }
}
