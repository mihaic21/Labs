package Aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import Security.AbstractAuthenticationAspect;

@Aspect
public class AuthenticationAspect extends
        AbstractAuthenticationAspect {
    @Pointcut("execution(* controller.Controller.order*(..))")
    public void authenticationRequired() {
    }
}