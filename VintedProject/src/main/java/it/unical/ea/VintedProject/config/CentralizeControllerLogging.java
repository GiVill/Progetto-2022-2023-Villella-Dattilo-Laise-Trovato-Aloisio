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

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        String[] parameterNames = methodSignature.getParameterNames();
        Class<?>[] parameterTypes = methodSignature.getParameterTypes();
        Object[] args = joinPoint.getArgs();

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
