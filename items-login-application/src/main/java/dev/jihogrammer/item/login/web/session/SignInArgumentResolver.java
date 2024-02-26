package dev.jihogrammer.item.login.web.session;

import dev.jihogrammer.member.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class SignInArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(@NonNull final MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(SignIn.class);
        boolean hasParameter = Member.class.isAssignableFrom(parameter.getParameterType());
        log.debug("sign-in argument supports - hasAnnotation: {}, hasParameter: {}", hasAnnotation, hasParameter);
        return hasAnnotation && hasParameter;
    }

    @Override
    public Object resolveArgument(
        @NonNull final MethodParameter parameter,
        final ModelAndViewContainer mavContainer,
        @NonNull final NativeWebRequest webRequest,
        final WebDataBinderFactory binderFactory
    ) {
        return SessionHandler.findMember((HttpServletRequest) webRequest.getNativeRequest()).orElse(null);
    }
}
