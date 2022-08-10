package net.therap.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * @author masud.rana
 * @since 27/7/21
 */
public class CustomizedSitemeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.setCustomDecoratorSelector(new UserDecoratorSelector());
    }
}
