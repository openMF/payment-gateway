package org.apache.payment.gateway.config.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.apache.payment.gateway.utils.Utility;

/**
 * @author Rahul Goel created on 2/6/18
 */

@Aspect
@Component
public class LoggingAspect {

    private static Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Before("@annotation(org.apache.payment.gateway.config.aspect.Loggable)")
    public void annotatedBeforeLoggingAdvice(JoinPoint joinPoint) throws Throwable{
        logger.info("[" + joinPoint.getSignature().getDeclaringTypeName() + "]" +
                "[" + MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName() + "] " +
                "Input Params : " + Utility.objectToJson(joinPoint.getArgs()));
    }

}
