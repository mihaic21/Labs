package Security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class SysoutLoginService implements LoginService {
	public Authentication getAuthenticationToken() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("Username: ");
			String userName = in.readLine().trim();
			System.out.print("Password: ");
			String password = in.readLine().trim();
			return new UsernamePasswordAuthenticationToken(userName, password);
		} catch (IOException ex) {
			return null;
		}
	}
}

