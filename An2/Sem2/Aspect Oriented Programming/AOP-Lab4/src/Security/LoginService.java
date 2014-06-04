package Security;


import org.springframework.security.core.Authentication;

public interface LoginService {

    public Authentication getAuthenticationToken();

}