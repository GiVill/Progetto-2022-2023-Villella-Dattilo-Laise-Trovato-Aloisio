package it.unical.ea.VintedProject.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CentralizeControllerLogging {

    @Before("execution(* it.unical.ea.VintedProject.controller.*.*(..))")
    public void logController(JoinPoint joinPoint) {

        // Get the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // Get the method's declaring class
        String className = methodSignature.getDeclaringType().getSimpleName();

        // Get the method name
        String methodName = methodSignature.getName();

        // Get the method parameters' names
        String[] parameterNames = methodSignature.getParameterNames();

        // Get the method parameters' types
        Class<?>[] parameterTypes = methodSignature.getParameterTypes();

        // Get the method arguments
        Object[] args = joinPoint.getArgs();

        // Create a log message
        StringBuilder logMessage = new StringBuilder();

        logMessage.append("["+className+"] . Method: " +methodName+"(");

        for (int i = 0; i < parameterNames.length; i++) {
            logMessage.append(parameterTypes[i].getSimpleName()).append(" ");
            logMessage.append(parameterNames[i]).append(" = ").append(args[i]);
            if (i < parameterNames.length - 1) {
                logMessage.append(", ");
            }
        }

        log.info(logMessage + ")");
    }
}
