package dev.jihogrammer.servlet.mvc;

import dev.jihogrammer.member.port.out.Members;
import dev.jihogrammer.member.port.out.SingletonInMemoryMemberRepository;
import dev.jihogrammer.servlet.mvc.service.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@ServletComponentScan
public class MVCApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(MVCApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MVCApplication.class);
    }

    /**
     * {@code application.yml} 파일에 설정하여 동일하게 처리할 수 있다.
     * <pre><code>
     * spring.mvc.view.prefix: /WEB-INF/
     * spring.mvc.view.suffix: .jsp
     * </code></pre>
     */
    @Bean
    public org.springframework.web.servlet.ViewResolver internalResourceViewResolver(
        @Value("${spring.mvc.view.prefix}") final String prefix,
        @Value("${spring.mvc.view.suffix}") final String suffix
    ) {
        return new InternalResourceViewResolver(prefix, suffix);
    }

    @Bean
    public dev.jihogrammer.servlet.mvc.ViewResolver viewResolver(
        @Value("${spring.mvc.view.prefix}") final String prefix,
        @Value("${spring.mvc.view.suffix}") final String suffix
    ) {
        return new dev.jihogrammer.servlet.mvc.ViewResolver(prefix, suffix);
    }

    @Bean
    public Members members() {
        return SingletonInMemoryMemberRepository.getInstance();
    }

    @Bean
    public MemberService memberService(final Members members) {
        return new MemberService(members);
    }

}
