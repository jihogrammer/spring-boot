package dev.jihogrammer.members.application.signup;

import dev.jihogrammer.time.StopWatch;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class SignUpLoggingAspect {

    private static final ThreadLocal<StopWatch> STOP_WATCH = ThreadLocal.withInitial(StopWatch::new);

    @Pointcut("execution(* dev.jihogrammer.domain.members.port.in.SignUpUsage.signUp(..))")
    private String signUp() {
        return "Member signUp(SignUpCommand)";
    }

    @Before("signUp()")
    public void beforeSignUp() {
        STOP_WATCH.get().start();
    }

    @AfterReturning(pointcut = "signUp()", returning = "result")
    public void afterSignUp(final JoinPoint joinPoint, final Object result) {
        STOP_WATCH.get().stop();

        log.debug("method=[{}]; args=[{}]; result=[{}]; {}",
                this.signUp(), joinPoint.getArgs(), result, STOP_WATCH.get());
    }

}
