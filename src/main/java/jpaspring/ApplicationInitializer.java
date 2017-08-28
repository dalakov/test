package jpaspring;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*import static org.lightadmin.core.util.LightAdminConfigurationUtils.LIGHT_ADMINISTRATION_BACK_TO_SITE_URL;
import static org.lightadmin.core.util.LightAdminConfigurationUtils.LIGHT_ADMINISTRATION_BASE_PACKAGE;
import static org.lightadmin.core.util.LightAdminConfigurationUtils.LIGHT_ADMINISTRATION_BASE_URL;
*/
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String CHARACTER_ENCODING = "UTF-8";

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { ConfigWeb.class };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {ConfigApplication.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(CHARACTER_ENCODING);
        encodingFilter.setForceEncoding(true);
        return new Filter[] { encodingFilter };
    }

/*    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter(LIGHT_ADMINISTRATION_BASE_URL, "/admin");
        servletContext.setInitParameter(LIGHT_ADMINISTRATION_BACK_TO_SITE_URL, "http://lightadmin.org");
        servletContext.setInitParameter(LIGHT_ADMINISTRATION_BASE_PACKAGE, "org.lightadmin.administration");

        super.onStartup(servletContext);
    }
*/
}

