package dev.jihogrammer.springboot.container;

import jakarta.servlet.ServletContext;

interface ApplicationInitializer {

    void onStartUp(ServletContext servletContext);

}
