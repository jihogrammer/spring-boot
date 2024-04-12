package dev.jihogrammer.member.adaptor.in.web;

import dev.jihogrammer.member.FrontControllerApplication;
import dev.jihogrammer.member.adaptor.in.web.controller.MemberFormController;
import dev.jihogrammer.member.adaptor.in.web.controller.MemberListController;
import dev.jihogrammer.member.adaptor.in.web.controller.MemberSaveController;
import dev.jihogrammer.member.adaptor.in.web.frontcontroller.*;
import dev.jihogrammer.member.adaptor.in.web.model.Adapter;
import dev.jihogrammer.member.adaptor.in.web.model.Controller;
import dev.jihogrammer.member.application.port.in.MemberQuery;
import dev.jihogrammer.member.application.port.in.MemberSignUpUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@ServletComponentScan
@Slf4j
public class WebConfig extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(FrontControllerApplication.class);
    }

    @Bean
    public MemberFormController memberFormController() {
        return new MemberFormController();
    }

    @Bean
    public MemberSaveController memberSaveController(final MemberSignUpUseCase memberSignUpUseCase) {
        return new MemberSaveController(memberSignUpUseCase);
    }

    @Bean
    public MemberListController memberListController(final MemberQuery memberQuery) {
        return new MemberListController(memberQuery);
    }

    @Bean
    public ControllerResolver controllerResolver(final Controller... controllers) {
        log.info("register {} controllers", controllers.length);
        Map<String, Controller> controllerMap = Stream.of(controllers)
            .peek(controller -> log.info("controller {}", controller))
            .collect(Collectors.toUnmodifiableMap(Controller::uri, controller -> controller));
        return new ControllerResolver(controllerMap);
    }

    @Bean
    public ViewResolver viewResolver(
        @Value("${spring.mvc.view.prefix}") final String prefix,
        @Value("${spring.mvc.view.suffix}") final String suffix
    ) {
        log.info("register view resolver: prefix={}, suffix={}", prefix, suffix);
        return new ViewResolver(prefix, suffix);
    }

    @Bean
    public AdapterMapper adapterMapper() {
        Set<Adapter> adapters = Set.of(new ModelViewAdapter(), new ViewNameAdapter());
        log.info("register adapters={}", adapters);
        return new AdapterMapper(adapters);
    }

}
