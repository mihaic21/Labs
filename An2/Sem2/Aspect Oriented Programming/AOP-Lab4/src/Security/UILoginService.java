package Security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.swing.*;

/**
 * Created by mihai on 02.06.2014.
 */
public class UILoginService implements LoginService{


    @Override
    public Authentication getAuthenticationToken() {
        String userName= JOptionPane.showInputDialog("Username:");
        String password=JOptionPane.showInputDialog("Password:");

        return new UsernamePasswordAuthenticationToken(userName, password);
    }
}
