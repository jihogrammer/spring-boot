package dev.jihogrammer.springboot.embed;

import dev.jihogrammer.springboot.embed.servlet.HelloServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class EmbedTomcatApplication {

    public static void main(String[] args) throws LifecycleException {
        System.out.println("EmbedTomcatApplication.main");

        var tomcat = new Tomcat();

        var connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        var servletName = "helloServlet";
        var context = tomcat.addContext("", "/");
        tomcat.addServlet("", servletName, new HelloServlet());
        context.addServletMappingDecoded("/hello-servlet", servletName);

        tomcat.start();
    }

}
