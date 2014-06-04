package Security;

import javax.swing.JOptionPane;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.authentication.BadCredentialsException;

@Aspect
public abstract class AbstractAuthenticationAspect {
    private AuthenticationSupport authenticationSupport;

    public AbstractAuthenticationAspect(){
    	System.out.println("Constructor");
    }
    @Pointcut
    public abstract void authenticationRequired();

    @Before("authenticationRequired()")
    public void authenticate() {
    	try{
        authenticationSupport.authenticate();
    	}catch(BadCredentialsException ex){
    		JOptionPane.showMessageDialog(null, "Bad credentials!");
    	}
    }

    public void setAuthenticationSupport(
            AuthenticationSupport authenticationSupport) {
        this.authenticationSupport = authenticationSupport;
    }
}