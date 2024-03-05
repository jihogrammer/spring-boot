package dev.jihogrammer.web.frontcontroller.controller;

import dev.jihogrammer.web.frontcontroller.model.ModelView;
import dev.jihogrammer.web.frontcontroller.model.ModelViewController;
import dev.jihogrammer.web.frontcontroller.service.MemberSignUpService;
import dev.jihogrammer.web.frontcontroller.utils.SignUpRequestParameterParser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberSaveController extends ModelViewController {

    private final String uri;

    private final String viewName;

    private final SignUpRequestParameterParser parameterParser;

    private final MemberSignUpService service;

    @Override
    public String uri() {
        return this.uri;
    }

    @Override
    public String view() {
        return this.viewName;
    }

    @Override
    public ModelView process(final HttpServletRequest request) {
        var name = this.parameterParser.parseName(request);
        var age = this.parameterParser.parseAge(request);
        var model = this.service.register(name, age);

        return new ModelView(this.viewName, model);
    }

}
