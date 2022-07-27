package com.example.aop.aop;

import com.example.aop.annotation.Timer;
import jdk.jshell.MethodSnippet;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.lang.reflect.Method;

@Aspect //aop를위해서 추가
@Component //aop는 스프링 컴포넌트로 활용
public class PrameterAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    //execution해라 위패키지 하위 메소드들 전부..
    //인터넷 찾아봐라 시간날때 ^^
    private void cut()
    {


    }
    //execution해라 위패키지 하위 메소드들 전부.. 실행전 아래 메소드탐
    @Before("cut()")
    //cut메소드 실행시키겟따.
    public void before(JoinPoint joinPoint)
    {

        //메소드 이름
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs();//메소드들의 아규먼트 배열
        for (Object obj : args)
        {
            System.out.println(" type :" + obj.getClass().getSimpleName());
            System.out.println(" value" + obj);
        }
    }
    //execution해라 위패키지 하위 메소드들 전부.. 실행후 아래 메소드 탐

    @AfterReturning(value = "cut()" , returning = "returnobj")
    //조인포인터 포인터 객체가있고 오브젝트로 반환될꺼니까
    // returning = "returnobj" 하위 메소드랑 매칭 되야됨
    public void afterReturn(JoinPoint joinPoint,Object returnobj)
    {
        System.out.println("return object");
        System.out.println(returnobj);

    }


}
