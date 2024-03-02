package dev.jihogrammer.front_controller;

import dev.jihogrammer.front_controller.adapter.ModelViewAdapter;
import dev.jihogrammer.front_controller.adapter.ViewNameAdapter;
import dev.jihogrammer.front_controller.controller.MemberFormController;
import dev.jihogrammer.front_controller.controller.MemberListController;
import dev.jihogrammer.front_controller.controller.MemberSaveController;
import dev.jihogrammer.front_controller.model.Adapter;
import dev.jihogrammer.front_controller.model.Controller;
import dev.jihogrammer.front_controller.service.MemberService;
import dev.jihogrammer.front_controller.service.MemberSignUpService;
import dev.jihogrammer.front_controller.utils.AdapterMapper;
import dev.jihogrammer.front_controller.utils.ControllerResolver;
import dev.jihogrammer.front_controller.utils.SignUpRequestParameterParser;
import dev.jihogrammer.front_controller.utils.ViewResolver;
import dev.jihogrammer.member.port.out.Members;
import dev.jihogrammer.member.port.out.SingletonInMemoryMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static dev.jihogrammer.front_controller.FrontControllerServlet.URI_PREFIX;

@SpringBootApplication
@ServletComponentScan
@Slf4j
public class FrontControllerApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(FrontControllerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(FrontControllerApplication.class);
    }

    @Bean
    public Members members() {
        return SingletonInMemoryMemberRepository.getInstance();
    }

    @Bean
    public MemberFormController memberFormController(
            @Value("${service.sign-up-form.uri}") final String signUpFormURI,
            @Value("${service.sign-up-form.view}") final String signUpFormView
    ) {
        var uri = URI_PREFIX + signUpFormURI;
        return new MemberFormController(uri, signUpFormView);
    }

    @Bean
    public MemberSaveController memberSaveController(
            final Members members,
            @Value("${service.sign-up.uri}") final String signUpURI,
            @Value("${service.sign-up.view}") final String signUpView,
            @Value("${service.sign-up.parameters.name}") final String nameKey,
            @Value("${service.sign-up.parameters.age}") final String ageKey,
            @Value("${service.sign-up.model.member}") final String memberKey
    ) {
        var uri = URI_PREFIX + signUpURI;
        var parameterParser = new SignUpRequestParameterParser(nameKey, ageKey);
        var service = new MemberSignUpService(members, memberKey);
        return new MemberSaveController(uri, signUpView, parameterParser, service);
    }

    @Bean
    public MemberListController memberListController(
            @Value("${service.members.uri}") final String membersURI,
            @Value("${service.members.view}") final String membersView,
            @Value("${service.members.model.members}") final String membersModelKey,
            final Members members
    ) {
        var uri = URI_PREFIX + membersURI;
        var service = new MemberService(members, membersModelKey);
        return new MemberListController(uri, membersView, service);
    }

    @Bean
    public ControllerResolver controllerResolver(final Controller... controllers) {
        log.info("register {} controllers", controllers.length);
        Map<String, Controller> controllerMap = Stream.of(controllers)
                .peek(controller -> log.info("controller name={}, uri={}, view={}", controller.getClass().getSimpleName(), controller.uri(), controller.view()))
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
