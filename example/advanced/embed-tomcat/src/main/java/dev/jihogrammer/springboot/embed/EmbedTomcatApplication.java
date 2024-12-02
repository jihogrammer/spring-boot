package dev.jihogrammer.springboot.embed;

import dev.jihogrammer.springboot.embed.boot.ApplicationBooting;
import dev.jihogrammer.springboot.embed.boot.ApplicationRunner;

@ApplicationBooting
public class EmbedTomcatApplication {

    public static void main(String[] args) {
        ApplicationRunner.run(EmbedTomcatApplication.class, args);
    }

}
