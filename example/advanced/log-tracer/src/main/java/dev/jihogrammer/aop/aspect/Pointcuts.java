package dev.jihogrammer.aop.aspect;

import org.aspectj.lang.annotation.Pointcut;

class Pointcuts {

    @Pointcut("execution(* dev.jihogrammer.aop..*(..))")
    void allOrder() {
    }

    @Pointcut("execution(* *..*Service.*(..))")
    void allService() {
    }

    @Pointcut("allOrder() && allService()")
    void allOrderService() {
    }

}
