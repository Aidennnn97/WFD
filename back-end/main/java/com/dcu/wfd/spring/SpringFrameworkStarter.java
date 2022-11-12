package com.dcu.wfd.spring;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.dcu.wfd.util.CrawlingThread;

/**
 * SpringFrameWork context 실행을 위한 class
 * 
 * @author KGW
 * @since 2022.10.20
 *
 */

@Configuration
public class SpringFrameworkStarter implements WebApplicationInitializer{
	
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootContext.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // Web(Servlet) Context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(com.dcu.wfd.spring.servlet.ServletContext.class);

        // Dispatcher Servlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        // Filter
        FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        filter.setInitParameter("encoding","utf-8");
        filter.addMappingForServletNames(null,false,"dispatcher");
        
        //Scheduler
        CrawlingThread ct = new CrawlingThread();
        ct.start();
	}
	
}










