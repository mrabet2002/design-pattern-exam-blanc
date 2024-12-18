package ma.enset.aspects;

import ma.enset.aspects.annotations.SecuredBy;
import ma.enset.security.SecurityContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAspect {
    private final SecurityContext securityContext = SecurityContext.getInstance();
    @Around(value = "@annotation(securedBy)", argNames = "proceedingJoinPoint,securedBy")
    public Object checkRoles(ProceedingJoinPoint proceedingJoinPoint, SecuredBy securedBy) throws Throwable {
        String[] roles = securedBy.roles();
        // Check if the user has the required roles
        if (!securityContext.hasRoles(roles)) {
            throw new RuntimeException("You don't have the required roles to access this resource");
        }

        return proceedingJoinPoint.proceed();
    }
}
