package dev.jihogrammer.springboot.container;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.HandlesTypes;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@HandlesTypes(ApplicationInitializer.class)
public class ApplicationServletContainerInitializer implements jakarta.servlet.ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) {
        System.out.println("ApplicationServletContainerInitializer.onStartup");
        System.out.println("ApplicationServletContainerInitializer c = " + c);
        System.out.println("ApplicationServletContainerInitializer ctx = " + ctx);

        for (Class<?> aClass : c) {
            try {
                ((ApplicationInitializer) aClass.getDeclaredConstructor().newInstance()).onStartUp(ctx);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
