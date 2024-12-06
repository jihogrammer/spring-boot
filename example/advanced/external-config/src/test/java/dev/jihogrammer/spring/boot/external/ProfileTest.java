package dev.jihogrammer.spring.boot.external;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;

class ProfileTest {

    @Test
    void testLocal() {
        // given
        var profile = "default";
        var commandLineArg = String.format("--spring.profiles.active=%s", profile);

        // when
        var context = SpringApplication.run(ExternalConfigApplication.class, commandLineArg);
        var environment = context.getBean(Environment.class);

        // then
        assertThat(environment.getProperty("app.username")).isEqualTo("local_username");
        assertThat(environment.getProperty("app.username")).isEqualTo("local_username");
        assertThat(environment.getProperty("app.common.user")).isEqualTo("jihogrammer");
        assertThat(environment.getProperty("app.common.hello")).isEqualTo("world");
    }

    @Test
    void testDev() {
        // given
        var profile = "dev";
        var commandLineArg = String.format("--spring.profiles.active=%s", profile);

        // when
        var context = SpringApplication.run(ExternalConfigApplication.class, commandLineArg);
        var environment = context.getBean(Environment.class);

        // then
        assertThat(environment.getProperty("app.username")).isEqualTo("dev_username");
        assertThat(environment.getProperty("app.username")).isEqualTo("dev_username");
        assertThat(environment.getProperty("app.common.user")).isEqualTo("jihogrammer");
        assertThat(environment.getProperty("app.common.hello")).isEqualTo("world");
    }

    @Test
    void testPrd() {
        // given
        var profile = "prd";
        var profileVMOptionKey = "spring.profiles.active";
        System.getProperties().put(profileVMOptionKey, profile);

        // when
        var context = SpringApplication.run(ExternalConfigApplication.class);
        var environment = context.getBean(Environment.class);

        // then
        assertThat(environment.getProperty("app.username")).isEqualTo("prd_username");
        assertThat(environment.getProperty("app.password")).isEqualTo("prd_password");
        assertThat(environment.getProperty("app.common.user")).isEqualTo("jihogrammer");
        assertThat(environment.getProperty("app.common.hello")).isEqualTo("world");
    }

}
