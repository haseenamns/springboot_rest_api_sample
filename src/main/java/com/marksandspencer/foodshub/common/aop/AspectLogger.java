package com.marksandspencer.foodshub.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogger {

    /** The logger. */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Before.
     *
     * @param joinPoint the join point
     */
    @Before("execution(* com.marksandspencer.foodshub.common.service.*.*(..))")
    public void before(JoinPoint joinPoint){
        logger.info("Before execution of {}", joinPoint);
    }

    /**
     * After.
     *
     * @param joinPoint the join point
     */
    @After(value = "execution(* com.marksandspencer.foodshub.common.service.*.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info("After execution of {}", joinPoint);
    }

}
