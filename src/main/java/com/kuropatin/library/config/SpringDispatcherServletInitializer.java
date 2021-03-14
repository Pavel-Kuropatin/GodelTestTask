package com.kuropatin.library.config;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


public class SpringDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    /** @return returns empty class because root config does not not exist, can also return null as said in the manual */
    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class[] {};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        registerHiddenFieldFilter(servletContext);
    }

    /** Registration of hidden HTTP methods */
    private void registerHiddenFieldFilter(ServletContext servletContextContext) {
        servletContextContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }
}