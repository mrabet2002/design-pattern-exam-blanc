package ma.enset.aspects;

import ma.enset.entities.Agent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CacheableAspect {
    Map<String, Object> cache = new HashMap<>();
    @Around("@annotation(ma.enset.aspects.annotations.Cacheable) && execution(* ma.enset.entities.Agent.getTransactionWithMaxAmount())")
    public Object cacheMaxTransaction(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String agentName = ((Agent) proceedingJoinPoint.getTarget()).getName();
        if (cache.containsKey(agentName)) {
            return cache.get(agentName);
        }
        Object result = proceedingJoinPoint.proceed();
        cache.put(agentName, result);
        return result;
    }

    @Around("@annotation(ma.enset.aspects.annotations.Cacheable) && execution(* ma.enset.entities.Agent.addTransaction(..))")
    public Object clearCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String agentName = ((Agent) proceedingJoinPoint.getTarget()).getName();
        cache.remove(agentName);
        return proceedingJoinPoint.proceed();
    }

}
