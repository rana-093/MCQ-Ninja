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

    List<String> ALLOWED_URLS = Arrays.asList(
            "http://localhost:8080/login",
            "http://localhost:8080/logout",
            "http://localhost:8080/registerAdmin",
            "http://localhost:8080/registerStudent",
            "http://localhost:8080/",
            "http://localhost:8080/fourOfour"
    );
}

