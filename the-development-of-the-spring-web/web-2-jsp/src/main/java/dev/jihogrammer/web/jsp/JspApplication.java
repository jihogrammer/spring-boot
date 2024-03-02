package dev.jihogrammer.web.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan
public class JspApplication extends SpringBootServletInitializer {

    /**
     * <h1>Run with Gradle</h1>
     * IntelliJ 실행 시 동작 실패 (원인: 아마도 tomcat 설정이 달라서)
     */
    public static void main(final String[] args) {
        SpringApplication.run(JspApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(JspApplication.class);
    }

}
