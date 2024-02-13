package com.actionprime.logging.config;

import com.actionprime.logging.aop.LoggingAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * This class is used to configure the AspectJ aspects for the logging.
 *
 * @author ActionPrime
 */
@AutoConfiguration
@EnableAspectJAutoProxy
public class LoggingAspectConfig {

    /**
     * This method creates an instance of the LoggingAspect class.
     *
     * @return an instance of the LoggingAspect class
     */
    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
