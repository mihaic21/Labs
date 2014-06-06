package ESDomain;

/**
 * Created by mihai on 05.06.2014.
 */
public class Fact {

    private String id;
    private String statement;
    private boolean state;

    public Fact(String id, String statement) {
        this.id = id;
        this.statement = statement;
        this.state = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Fact{" +
                "id='" + id + '\'' +
                ", statement='" + statement + '\'' +
                ", state=" + state +
                '}';
    }
}
