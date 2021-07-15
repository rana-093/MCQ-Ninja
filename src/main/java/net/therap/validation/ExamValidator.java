package net.therap.validation;

import net.therap.command.ExamCommand;
import net.therap.model.Exam;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        return ExamCommand.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ExamCommand examCommand = (ExamCommand) target;
        if (Objects.isNull(examCommand.getExam().getStartTime())) {
            errors.rejectValue("startTime", "NOT NULL", "Date should not be null");
        }
        if (Objects.isNull(examCommand.getExam().getEndTime())) {
            errors.rejectValue("endTime", "NOT NULL", "Date should not be null");
        } else {
         //   if (examCommand.getExam().getEndTime().before(examCommand.getExam().getStartTime())) {
          //      errors.rejectValue("endTime", "DOES NOT EXIST", "End date should be greater than Start Date");
         //   }
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//            simpleDateFormat.setLenient(false);
//            try {
//                Date res = simpleDateFormat.parse(new Date().toString());
//                if (examCommand.getExam().getStartTime().before(res)) {
//                    errors.rejectValue("startTime", "INVALID", "Start Time should be >= than Current Time!");
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        }
    }
}
