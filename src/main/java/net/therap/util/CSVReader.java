package net.therap.util;

import net.therap.dao.TopicDao;
import net.therap.model.Option;
import net.therap.model.Question;
import net.therap.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.therap.util.Helper.fileURL;

/**
 * @author masud.rana
 * @since 29/7/21
 */
@Component
public class CSVReader {

    @Autowired
    private TopicDao topicDao;

    public List<Question> fetchQuestionsFromCSV(String fileName)
            throws IOException {
        String filePath = fileURL + "/" + fileName;
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        List<Question> questionList = new ArrayList<>();
        String line = "";

        while ((line = reader.readLine()) != null) {
            String[] elements = line.split(";");
            Question question = new Question();
            question.setContent(elements[0]);

            List<Option> optionList = new ArrayList<>();
            Option A = new Option(); A.setContent(elements[1]);
            Option B = new Option(); B.setContent(elements[2]);
            Option C = new Option(); C.setContent(elements[3]);
            Option D = new Option(); D.setContent(elements[4]);
            optionList.add(A);
            optionList.add(B);
            optionList.add(C);
            optionList.add(D);

            question.setCorrectOption(Integer.parseInt(elements[5]));

            Topic topic = topicDao.findByTopicName(elements[6]);
            question.setTopic(topic);

            question.setExplanation(elements[7]);

            questionList.add(question);
        }
        return questionList;
    }
}
