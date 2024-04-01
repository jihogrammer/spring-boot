package dev.jihogrammer.members.application.signup;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class SignUpLoggingAspect {

    @Pointcut("execution(* dev.jihogrammer.domain.members.port.in.SignUpUsage.signUp(..))")
    private String signUp() {
        return "Member signUp(SignUpCommand)";
    }

    @AfterReturning(pointcut = "signUp()", returning = "result")
    public void afterSignUp(final JoinPoint joinPoint, final Object result) {
        log.debug("method=[{}]; args=[{}]; result=[{}];", this.signUp(), joinPoint.getArgs(), result);
    }

}
