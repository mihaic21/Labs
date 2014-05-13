package Model;

/**
 * Created by mihai on 29.04.2014.
 */
public class Node implements Comparable<Node>{

    private int missionaries;
    private int cannibals;
    private int boat;

    private Node parent;
    private Node otherBank;

    private String action;
    private String nextArithmetic;

    public Node(int missionaries, int cannibals, int boat, String action, String nextArithmetic, Node otherBank) {
        this.missionaries = missionaries;
        this.cannibals = cannibals;
        this.boat = boat;
        this.action = action;
        this.nextArithmetic = nextArithmetic;
        this.otherBank = otherBank;
        this.parent = null;
    }

    public int getMissionaries() {
        return missionaries;
    }

    public void setMissionaries(int missionaries) {
        this.missionaries = missionaries;
    }

    public int getCannibals() {
        return cannibals;
    }

    public void setCannibals(int cannibals) {
        this.cannibals = cannibals;
    }

    public int getBoat() {
        return boat;
    }

    public void setBoat(int boat) {
        this.boat = boat;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getOtherBank() {
        return otherBank;
    }

    public void setOtherBank(Node otherBank) {
        this.otherBank = otherBank;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNextArithmetic() {
        return nextArithmetic;
    }

    public void setNextArithmetic(String nextArithmetic) {
        this.nextArithmetic = nextArithmetic;
    }

    @Override
    public String toString() {
        return "Missionaries: " + missionaries +
               " Cannibals: " + cannibals +
               " ... River ... " +
               "Missionaries: " + otherBank.getMissionaries() +
               " Cannibals: " + otherBank.getCannibals() + "\n";
    }

    @Override
    public int compareTo(Node node) {
        return this.getCannibals() - node.getCannibals();
    }
}
