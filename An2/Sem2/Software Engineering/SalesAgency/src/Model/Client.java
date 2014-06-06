package Model;

/**
 * Created by mihai on 28.05.2014.
 */
public class Client {

    private String cnp;
    private String name;
    private String address;

    public Client(String cnp, String name, String address) {
        this.cnp = cnp;
        this.name = name;
        this.address = address;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
