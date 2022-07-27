package com.example.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE , ElementType.METHOD})
//타겟은 2개 타입이랑 메소드에사용
@Retention(RetentionPolicy.RUNTIME)
//런타임에 사용할거임
public @interface Timer {

}
