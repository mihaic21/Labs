package Model;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 11/29/13
 * Time: 6:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class Edge<C,V> {
    private V source;
    private V destination;
    private C cost;

    public Edge(V source, V destination, C cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

    public V getSource(){
        return source;
    }

    public V getDestination(){
        return destination;
    }

    public C getCost(){
        return cost;
    }

    public void setCost(C cost){
        this.cost = cost;
    }

    @Override
    public String toString(){
        return "Edge [source=" + source + ", destination=" + destination + ", cost=" + cost + "]";
    }

    @Override
    public boolean equals(Object object){
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        Edge<C, V> other = (Edge<C, V>) object;
        if (destination == null){
            if (other.destination != null)
                return false;
        } else if (!destination.equals(other.destination))
            return false;
        return true;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }


}
