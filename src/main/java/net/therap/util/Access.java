package net.therap.util;

import net.therap.dao.UserDao;
import net.therap.exception.WebSecurityException;
import net.therap.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 12/7/21
 */
public class Access {

    public static void checkAccessWithId(int id, HttpServletRequest request) throws WebSecurityException {
        HttpSession session = request.getSession(false);
        Object sessionId = session.getAttribute("userId");
        int userId = Integer.parseInt(sessionId.toString());
        if(id!=userId) {
            throw  new WebSecurityException("Violation of Web security..");
        }
    }

//    public static boolean checkAccessWithObject(int id, Object obj, HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        Object sessionId = session.getAttribute("userId");
//        int userId = Integer.parseInt(sessionId.toString());
//        userDao = new UserDao();
//        Student student = userDao.findStudent(userId);
//        return Objects.equals(obj, student);
//    }
}
