package Security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationSupport {
    private AuthenticationManager authenticationManager;
    private LoginService loginUI;

    public void authenticate() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication != null) {
            return;
        }
        Authentication authenticationToken = loginUI.getAuthenticationToken();
        authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void setAuthenticationManager(AuthenticationManager am) {
        this.authenticationManager = am;
    }

    public void setLoginUI(LoginService loginUI) {
        this.loginUI = loginUI;
    }
}