package dev.jihogrammer.springboot.container;

import jakarta.servlet.ServletContext;

import java.util.Set;

public class HelloServletContainerInitializer implements jakarta.servlet.ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) {
        System.out.println("HelloServletContainerInitializer.onStartup");
        System.out.println("HelloServletContainerInitializer c = " + c);
        System.out.println("HelloServletContainerInitializer ctx = " + ctx);
    }

}
