package dev.jihogrammer.springboot.embed.boot;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Arrays;

public class ApplicationRunner {

    public static void run(final Class<?> aClass, final String... args) {
        System.out.println("ApplicationClass=" + aClass + "; args=" + Arrays.toString(args));

        var tomcat = new Tomcat();

        var connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        var applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(aClass);

        var servletName = "dispatcher";
        var context = tomcat.addContext("", "/");
        tomcat.addServlet("", servletName, new DispatcherServlet(applicationContext));
        context.addServletMappingDecoded("/", servletName);

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }

}
