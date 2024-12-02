package dev.jihogrammer.springboot.container;

import dev.jihogrammer.springboot.webmvc.HelloConfig;
import jakarta.servlet.ServletContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class V2SpringApplicationInitializer implements ApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("V2SpringApplicationInitializer.onStartUp");

        var applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(HelloConfig.class);

        var dispatcherServlet = new DispatcherServlet(applicationContext);
        servletContext
                .addServlet("dispatcherV2", dispatcherServlet)
                .addMapping("/spring/*");
    }

}
