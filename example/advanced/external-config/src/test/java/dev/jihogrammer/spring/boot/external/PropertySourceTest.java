package dev.jihogrammer.spring.boot.external;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(
        properties = """
                spring.profiles.active: test
                app.common.user: anonymous
                app.common.hello: spring
                """)
class PropertySourceTest {

    /**
     * 아래로 갈수록 우선순위 높음
     * <ul>
     *  <li>PropertySource(application.properties)</li>
     *  <li>OS environment</li>
     *  <li>JVM Options</li>
     *  <li>Command Line Arguments</li>
     *  <li>{@code @TestPropertySource}</li>
     * </ul>
     *
     * 설정파일(application.properties) - 아래로 갈수록 우선순위 높음
     * <ul>
     *  <li>jar 내부 application.properties</li>
     *  <li>jar 내부 application-{profile}.properties</li>
     *  <li>jar 외부 application.properties</li>
     *  <li>jar 외부 application-{profile}.properties</li>
     * </ul>
     */
    @Test
    void testPriority(@Autowired Environment environment) {
        assertThat(environment.getProperty("app.username")).isEqualTo("local_username");
        assertThat(environment.getProperty("app.password")).isEqualTo("local_password");
        assertThat(environment.getProperty("app.common.user")).isEqualTo("anonymous");
        assertThat(environment.getProperty("app.common.hello")).isEqualTo("spring");
    }

}
