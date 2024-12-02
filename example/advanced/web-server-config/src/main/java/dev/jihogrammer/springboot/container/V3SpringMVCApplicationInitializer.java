package dev.jihogrammer.springboot.container;

import dev.jihogrammer.springboot.webmvc.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class V3SpringMVCApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("V3SpringMVCApplicationInitializer.onStartup");

        var applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(HelloConfig.class);

        var dispatcherServlet = new DispatcherServlet(applicationContext);
        servletContext
                .addServlet("dispatcherV3", dispatcherServlet)
                .addMapping("/");
    }

}
