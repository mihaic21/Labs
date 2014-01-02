package Model;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 11/29/13
 * Time: 6:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vertex implements Comparable<Vertex> {

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    private Integer value;

    private Integer distance;

    public Vertex(Integer value) {
        this.value = value;
    }

    public Vertex(Vertex vertex) {
        value = new Integer(vertex.value);
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        Vertex other = (Vertex) object;
        if (value == null){
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Vertex [" + value + "]";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(Vertex vertex) {
        return this.getDistance() - vertex.getDistance();
    }
}
