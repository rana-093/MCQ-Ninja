package net.therap.filter;

import org.sitemesh.DecoratorSelector;
import org.sitemesh.content.Content;
import org.sitemesh.webapp.WebAppContext;
import net.therap.util.Helper;

import java.io.IOException;

/**
 * @author masud.rana
 * @since 27/7/21
 */
public class UserDecoratorSelector implements DecoratorSelector<WebAppContext> {

    @Override
    public String[] selectDecoratorPaths(Content content, WebAppContext context) {
        String url = context.getRequest().getRequestURL().toString();
        if (Helper.ALLOWED_URLS.contains(url)) {
            return new String[]{};
        }
        return new String[]{"/WEB-INF/decorators/decorator.jsp"};
    }
}