package dev.jihogrammer.filestorage;

import dev.jihogrammer.web.core.WebCoreApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileStorageApplication extends WebCoreApplication {

    public static void main(final String[] args) {
        SpringApplication.run(FileStorageApplication.class, args);
    }

}
