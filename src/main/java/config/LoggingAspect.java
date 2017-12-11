package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private static Logger LOG = LogManager.getLogger();
    @Around("bean(*Message*)")
    public Object loggingMessage(ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info("Starting method {} of object {} with arguments {}", joinPoint.getSignature(),
                joinPoint.getTarget(), Arrays.toString(joinPoint.getArgs()));
        Object s = joinPoint.getTarget();
        Object result = joinPoint.proceed();
        LOG.info("The result of the method {} is {}", joinPoint.getSignature(), result);
        return result;
    }
    @Around("bean(*Dao*)")
    public Object loggingDao(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        LOG.info("The result of the method {} is {}", joinPoint.getSignature(), result);
        return result;
    }

}