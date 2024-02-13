package com.actionprime.logging.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import static java.time.Instant.now;

/**
 * Logs the entrance and exit of specified methods with their arguments if debug logging is enabled.
 *
 * @author ActionPrime
 */
@Aspect
public class LoggingAspect {

    /**
     * A pointcut that matches methods within Spring beans.
     */
    @Pointcut(
            "within(@org.springframework.stereotype.Repository *)" +
                    " || within(@org.springframework.stereotype.Service *)" +
                    " || within(@org.springframework.web.bind.annotation.RestController *)"
    )
    public void springBeanPointcut() {
        // method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Returns a logger for the given join point.
     *
     * @param joinPoint the join point
     * @return the logger
     */
    private Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    }

    /**
     * Logs an exception that was thrown in a method matched by the pointcut.
     *
     * @param joinPoint the join point
     * @param e         the exception that was thrown
     */
    @AfterThrowing(pointcut = "springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger(joinPoint).error(
                "Exception in {}() with cause = '{}' and exception = '{}'",
                joinPoint.getSignature().getName(),
                e.getCause() != null ? e.getCause() : "NULL",
                e.getMessage(),
                e
        );
    }

    /**
     * Logs the entrance and exit of a method matched by the pointcut, including its arguments.
     *
     * @param joinPoint the join point
     * @return the result of the method invocation
     * @throws Throwable if the method invocation threw an exception
     */
    @Around("springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = logger(joinPoint);
        Instant startInstant = now();
        log.info("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        try {
            Object result = joinPoint.proceed();
            log.info("Exit: {}() with result = {}, execution ended in {}ms", joinPoint.getSignature().getName(), result, Duration.between(startInstant, now()).toMillis());
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}(), execution ended in {}ms", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName(), Duration.between(startInstant, now()).toMillis());
            throw e;
        }
    }
}
