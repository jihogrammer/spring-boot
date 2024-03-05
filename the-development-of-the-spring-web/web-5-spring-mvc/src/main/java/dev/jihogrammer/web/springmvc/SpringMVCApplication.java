package dev.jihogrammer.web.springmvc;

import dev.jihogrammer.member.port.out.Members;
import dev.jihogrammer.member.port.out.SingletonInMemoryMemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class SpringMVCApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringMVCApplication.class, args);
    }

    /**
     * {@code application.yml} 파일에 설정하여 동일하게 처리할 수 있다.
     * <pre><code>
     * spring.mvc.view.prefix: /WEB-INF/
     * spring.mvc.view.suffix: .jsp
     * </code></pre>
     */
    @Bean
    public ViewResolver internalResourceViewResolver(
        @Value("${spring.mvc.view.prefix}") final String prefix,
        @Value("${spring.mvc.view.suffix}") final String suffix
    ) {
        return new InternalResourceViewResolver(prefix, suffix);
    }

    @Bean
    public Members members() {
        return SingletonInMemoryMemberRepository.getInstance();
    }

}
