package com.sample.springmvc.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HelloWorldInitializer implements WebApplicationInitializer {
	
	public HelloWorldInitializer(){
		System.out.println("inside web app initializer cons ..");
	}

	@Override
	public void onStartup(ServletContext arg0) throws ServletException {
		
		System.out.println("inside configuring web app");
        AnnotationConfigWebApplicationContext ctx= new AnnotationConfigWebApplicationContext();
        ctx.register(HelloWorldConfiguration.class);
        ctx.setServletContext(arg0);
        
        // Now add Dispatcher servlet
        
        ServletRegistration.Dynamic servlet = arg0.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
	}

}
