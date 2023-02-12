package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before("execution(* ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.*.*(..))")
    void domainMethodsCallLogging() {
        logger.info("Database has started");
    }

    @Before("execution(* ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.*.*(..))")
    void daoMethodsCallLogging(JoinPoint joinPoint) {
        logger.info("DAO Method | {} | had made request to DB, with arguments: {}", joinPoint, joinPoint.getArgs());
    }
    
    @AfterReturning(
            pointcut = "execution(* ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.*.*(..))", 
            returning = "resultValue")
    void daoMethodsReturnLogging(JoinPoint joinPoint, Object resultValue) {
        logger.info("DAO Method | {} | has returned: {}", joinPoint, resultValue);
    }
    
    @AfterThrowing(
            pointcut = "execution(* ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.*.*(..))", 
            throwing = "exception")
    void daoMethodsExceptionLogging(JoinPoint joinPoint, Exception exception) {
        logger.error("DAO Method | {} | has thrown an exception: ", joinPoint, exception);
    }

}