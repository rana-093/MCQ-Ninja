package net.therap.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author masud.rana
 * @since 29/6/21
 */
public interface Helper {

    enum Role {
        ADMIN,
        STUDENT
    }

    String fileURL = "/home/masud.rana/Desktop/Final-Project of Ashraf Vai/src/main/resources/AllFiles";

    List<String> ALLOWED_URLS = Arrays.asList(
            "http://localhost:8080/login",
            "http://localhost:8080/logout",
            "http://localhost:8080/registerStudent",
            "http://localhost:8080/",
            "http://localhost:8080/fourOfour",
            "http://localhost:8080/home",
            "http://localhost:8080/showProfile"
    );

    List<String> ADMIN_URLS = Arrays.asList(
            "http://localhost:8080/questionList",
            "http://localhost:8080/question",
            "http://localhost:8080/subjectList",
            "http://localhost:8080/subject",
            "http://localhost:8080/topicList",
            "http://localhost:8080/topic",
            "http://localhost:8080/examList",
            "http://localhost:8080/exam",
            "http://localhost:8080/questionRemove",
            "http://localhost:8080/deleteExam",
            "http://localhost:8080/userList",
            "http://localhost:8080/user",
            "http://localhost:8080/registerAdmin",
            "http://localhost:8080/examTopic"
    );

    List<String> STUDENT_URLS = Arrays.asList(
            "http://localhost:8080/mcq",
            "http://localhost:8080/userExamList",
            "http://localhost:8080/examList",
            "http://localhost:8080/result",
            "http://localhost:8080/showDetails",
            "http://localhost:8080/regExam"
    );

    List<String> ALL_URLS = Arrays.asList(
            "http://localhost:8080/questionList",
            "http://localhost:8080/question",
            "http://localhost:8080/subjectList",
            "http://localhost:8080/subject",
            "http://localhost:8080/topicList",
            "http://localhost:8080/topic",
            "http://localhost:8080/examList",
            "http://localhost:8080/exam",
            "http://localhost:8080/questionRemove",
            "http://localhost:8080/deleteExam",
            "http://localhost:8080/userList",
            "http://localhost:8080/user",
            "http://localhost:8080/mcq",
            "http://localhost:8080/userExamList",
            "http://localhost:8080/restricted",
            "http://localhost:8080/login",
            "http://localhost:8080/logout",
            "http://localhost:8080/register",
            "http://localhost:8080/result",
            "http://localhost:8080/showDetails",
            "http://localhost:8080/registerAdmin",
            "http://localhost:8080/registerStudent",
            "http://localhost:8080/examTopic",
            "http://localhost:8080/regExam",
            "http://localhost:8080/showProfile",
            "http://localhost:8080/editProfile"
    );

    String LOG_IN = "auth/login";
    String RESTRICTED = "warnings/restricted";
    String FOUR_O_FOUR = "warnings/404";
    String REGISTERED = "warnings/registered";
    String NOT_REGISTERED = "warnings/notRegistered";

    String EXAM_LIST = "exam/examList";
    String EXAM = "exam/exam";

    String CHOOSE_QUESTIONS = "exam/chooseQuestions";

    String MCQ = "mcq/mcq";

    String QUESTIONLIST = "question/questionList";
    String QUESTION = "question/question";

    String HOME = "home";

    String STUDENT_REGISTER = "auth/studentRegister";
    String ADMIN_REGISTER = "auth/adminRegister";

    String USER_EXAM_LIST = "result/userExamList";
    String RESULT_DETAILS = "result/showDetails";

    String SHOW_PROFILE = "profile/show";
    String EDIT_PROFILE = "profile/edit";

    String SUBJECT_LIST = "subject/subjectList";
    String SUBJECT = "subject/subject";

    String TOPIC_LIST = "topic/topicList";
    String TOPIC = "topic/topic";

    String USER_STUDENT = "user/student";
    String STUDENT_LIST = "user/studentList";

}

