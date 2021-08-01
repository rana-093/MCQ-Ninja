package net.therap.filter;

import net.therap.util.Helper;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static net.therap.util.Helper.ALL_URLS;

/**
 * @author masud.rana
 * @since 30/6/21
 */
//@WebFilter("/*")
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURL().toString();
        System.out.println("URL: " + url);

        res.setHeader("Cache-control", "no-cache, no-store, must-revalidate");

        if (Helper.ALLOWED_URLS.contains(url)) {
            chain.doFilter(req, res);
        } else {
            HttpSession session = req.getSession(false);
            Object sessionName = session.getAttribute("userId");
            if (Objects.isNull(sessionName)) {
                res.sendRedirect("/login");
                return;
            }
            Helper.Role role = (Helper.Role) session.getAttribute("role");
            boolean isAdmin = Helper.ADMIN_URLS.contains(url);
            boolean isStudent = Helper.STUDENT_URLS.contains(url);
            if (isAdmin && isStudent) {
                chain.doFilter(request, response);
            } else if (isAdmin && role != Helper.Role.ADMIN) {
                res.sendRedirect("/restricted");
            } else if (isStudent && role != Helper.Role.STUDENT) {
                res.sendRedirect("/restricted");
            } else if (!ALL_URLS.contains(url)) {
                res.sendRedirect("/fourOfour");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }
}

