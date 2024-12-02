package dev.jihogrammer.springboot.container;

import jakarta.servlet.ServletContext;

interface ApplicationInitializer {

    void onStartup(ServletContext servletContext);

}
