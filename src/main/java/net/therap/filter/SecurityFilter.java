package net.therap.filters;

import net.therap.util.Helper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author masud.rana
 * @since 30/6/21
 */
@WebFilter("/*")
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
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}

