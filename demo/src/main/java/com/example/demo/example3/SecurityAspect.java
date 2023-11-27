package com.example.demo.example3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@Aspect
public class SecurityAspect {
	@Autowired
    private Payment paymentComponent;

    @Before(value="execution(* com.example.demo.example3.Payment.payment())")
    public void beforePayment() {
        System.out.println("Before advice for payment()");
    }

    @After(value="execution(* com.example.demo.example3.Payment.validateCC())")
    public void afterValidateCC() {
        System.out.println("After advice for validateCC()");
    }

    @Around(value="execution(* com.example.demo.example3.Payment.process())")
    public void aroundProcess(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before advice for process()");
        joinPoint.proceed();
        System.out.println("After advice for process()");
}
}
