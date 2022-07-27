package com.example.aop.aop;


import com.example.aop.dto.User;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component

public class DecodeAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    //execution해라 위패키지 하위 메소드들 전부..
    //인터넷 찾아봐라 시간날때 ^^
    private void cut()
    {}


    @Pointcut("@annotation(com.example.aop.annotation.Decode)")
    private void enableDecode()
    {
    }



    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException
    {
        //메소드에 넘어오는 아규먼트
        Object[] args = joinPoint.getArgs();

        for(Object arg : args)
        {
            if(arg instanceof User) //
            {
                User user = User.class.cast(arg);
                String base64Email = user.getEmail();
                String email = new String(Base64.getDecoder().decode(base64Email),"UTF-8");
                user.setEmail(email);
            }

        }


    }
    @AfterReturning(value = "cut() && enableDecode()" , returning = "returnobj")
    public void afterReturn(JoinPoint joinPoint , Object returnobj)
    {
        if(returnobj instanceof User) //
        {
            User user = User.class.cast(returnobj);
            String email = user.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes());
            user.setEmail(base64Email);
        }


    }



}
