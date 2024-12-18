package ma.enset.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LogAspect {
    Logger logger = Logger.getLogger(LogAspect.class.getName());
    @Around("@annotation(ma.enset.aspects.annotations.Log)")
    public Object logTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Time taken by " + proceedingJoinPoint.getSignature().getName() + " is " + (end - start) + "ms");
        logger.info("Time taken by " + proceedingJoinPoint.getSignature().getName() + " is " + (end - start) + "ms");
        return result;
    }
}
