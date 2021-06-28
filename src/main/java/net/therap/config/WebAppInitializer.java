package net.therap.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author masud.rana
 * @since 12/05/2021
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(final ServletContext sc) throws ServletException {
        System.out.println("Here I am!");
        AnnotationConfigWebApplicationContext root =
                new AnnotationConfigWebApplicationContext();
        root.register(WebConfig.class);
        root.setServletContext(sc);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(root);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        sc.addListener(new ContextLoaderListener(root));
        ServletRegistration.Dynamic servletRegistration = sc.addServlet("dispatcher", dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");
    }
}
