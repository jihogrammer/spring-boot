package dev.jihogrammer.springboot.embed;

import dev.jihogrammer.springboot.embed.servlet.HelloServlet;
import dev.jihogrammer.springboot.embed.spring.HelloConfig;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class EmbedTomcatApplication {

    public static void main(String[] args) throws LifecycleException {
        springTomcat().start();
    }

    private static Tomcat servletTomcat() {
        var tomcat = new Tomcat();
        tomcat.setConnector(connector());

        var servletName = "helloServlet";
        var context = tomcat.addContext("", "/");
        tomcat.addServlet("", servletName, new HelloServlet());
        context.addServletMappingDecoded("/hello-servlet", servletName);

        return tomcat;
    }

    private static Tomcat springTomcat() {
        var tomcat = new Tomcat();
        tomcat.setConnector(connector());

        var applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(HelloConfig.class);

        var servletName = "dispatcher";
        var context = tomcat.addContext("", "/");
        tomcat.addServlet("", servletName, new DispatcherServlet(applicationContext));
        context.addServletMappingDecoded("/", servletName);

        return tomcat;
    }

    private static Connector connector() {
        var connector = new Connector();
        connector.setPort(8080);
        return connector;
    }

}
