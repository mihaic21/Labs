package Model;

/**
 * Created by mihai on 19.05.2014.
 */
public class Section {

    private String name;
    private int positions;

    public Section(String name, int positions) {
        this.name = name;
        this.positions = positions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPositions() {
        return positions;
    }

    public void setPositions(int positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return name + ";" + positions;
    }
}
