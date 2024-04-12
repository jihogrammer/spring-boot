package dev.jihogrammer.member.adaptor.in.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig {

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

}
