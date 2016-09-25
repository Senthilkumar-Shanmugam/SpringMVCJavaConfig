package com.sample.springmvc.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class HelloWorldInitializer extends AbstractAnnotationConfigDispatcherServletInitializer { // implements
																									// WebApplicationInitializer

	public HelloWorldInitializer() {
		System.out.println("inside web app initializer cons ..");
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { HelloWorldConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		 return new String[] { "/" };
	}

	/*
	 * @Override public void onStartup(ServletContext arg0) throws
	 * ServletException {
	 * 
	 * System.out.println("inside configuring web app");
	 * AnnotationConfigWebApplicationContext ctx= new
	 * AnnotationConfigWebApplicationContext();
	 * ctx.register(HelloWorldConfiguration.class); ctx.setServletContext(arg0);
	 * 
	 * // Now add Dispatcher servlet
	 * 
	 * ServletRegistration.Dynamic servlet = arg0.addServlet("dispatcher", new
	 * DispatcherServlet(ctx)); servlet.setLoadOnStartup(1);
	 * servlet.addMapping("/"); }
	 */

}
