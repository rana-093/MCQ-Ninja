package net.therap.service;

import net.therap.dao.UserDao;
import net.therap.model.Admin;
import net.therap.model.Student;
import net.therap.model.User;
import net.therap.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 29/6/21
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Student findStudent(int studentId) {
        return userDao.findStudent(studentId);
    }

    public boolean doesExist(int id) {
        return Objects.nonNull(userDao.findAdmin(id)) ||
                Objects.nonNull(userDao.findStudent(id));
    }

    public boolean isAdmin(int id) {
        return Objects.nonNull(userDao.findAdmin(id));
    }

    public User findByEmail(String email) {
        User adminObj = userDao.findByEmail(email, true);
        if (Objects.nonNull(adminObj)) {
            return adminObj;
        }
        return userDao.findByEmail(email, false);
    }

    public List findAllStudents() {
        return userDao.findAllStudents();
    }

    public void saveOrUpdate(User user) {
        userDao.saveOrUpdate(user);
    }
}
