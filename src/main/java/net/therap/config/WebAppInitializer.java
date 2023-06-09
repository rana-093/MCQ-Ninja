package net.therap.config;

import net.therap.filter.CustomizedSitemeshFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
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
        AnnotationConfigWebApplicationContext root =
                new AnnotationConfigWebApplicationContext();
        root.register(WebConfig.class);

        FilterRegistration.Dynamic filter =  sc.addFilter("prerender", CustomizedSitemeshFilter.class);
        filter.addMappingForUrlPatterns(null , true, "/*");

        DispatcherServlet dispatcherServlet = new DispatcherServlet(root);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        sc.addListener(new ContextLoaderListener(root));
        ServletRegistration.Dynamic servletRegistration = sc.addServlet("dispatcher", dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");
    }
}
