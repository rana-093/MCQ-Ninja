package net.therap.validation;

import net.therap.model.Exam;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 4/7/21
 */
@Component
public class ExamValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Exam.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Exam exam = (Exam) target;
        if (Objects.nonNull(exam)) {
            if (Objects.isNull(exam.getStartTime())) {
                errors.rejectValue("startTime", "NOT NULL", "Date should not be null");
            }
            if (Objects.isNull(exam.getEndTime())) {
                errors.rejectValue("endTime", "NOT NULL", "Date should not be null");
            } else {
                if (exam.getEndTime().before(exam.getStartTime())) {
                    errors.rejectValue("endTime", "DOES NOT EXIST", "End date should be greater than Start Date");
                }
                if (exam.getStartTime().before(new Date())) {
                    errors.rejectValue("startTime", "INVALID", "Start Time should be >= than Current Time!");
                }
            }
        }
    }
}
