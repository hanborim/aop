package com.example.aop.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
//method Timer logic
public class TimerAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    //execution해라 위패키지 하위 메소드들 전부..
    //인터넷 찾아봐라 시간날때 ^^
    private void cut()
    {}


    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private void enableTimer()
    {

    }

    //앞뒤 시간 내게 해주는 어노테이션
    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        System.out.println("total item : " + stopWatch.getTotalTimeSeconds());
    }


}
