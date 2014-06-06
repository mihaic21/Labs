package Model;

/**
 * Created by mihai on 22.05.2014.
 */
public class Salesman {

    private String username;
    private String password;

    public Salesman(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Salesman() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
