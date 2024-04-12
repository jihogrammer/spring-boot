package dev.jihogrammer.members.adaptor.in.web.session;

import dev.jihogrammer.members.adaptor.in.web.entity.SignedInMember;
import dev.jihogrammer.web.core.session.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class SignInArgumentResolver implements HandlerMethodArgumentResolver {

    private final Session<SignedInMember> memberSession;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(SignIn.class);
        boolean hasParameter = SignedInMember.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && hasParameter;
    }

    @Override
    public Object resolveArgument(
            @NonNull final MethodParameter parameter,
            final ModelAndViewContainer mavContainer,
            @NonNull final NativeWebRequest webRequest,
            final WebDataBinderFactory binderFactory
    ) {
        return this.memberSession.fetch().orElse(null);
    }

}
