package dev.jihogrammer.springboot.container;

import dev.jihogrammer.springboot.servlet.HelloServlet;
import jakarta.servlet.ServletContext;

public class V1ServletApplicationInitializer implements ApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("V1ApplicationInitializer.onStartUp");

        servletContext
                .addServlet("helloServlet", new HelloServlet())
                .addMapping("/hello");
    }

}
