package SearchMethods;

import Model.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by mihai on 30.04.2014.
 */
public class DepthFirstSearch extends UninformedSearchMethod {

    private Map<String, Boolean> explored = new HashMap<String, Boolean>();

    private Node otherBank;
    private Node initialState;

    private ArrayList<String> actions = new ArrayList<String>();

    private Boolean goalReached = false;
    private Node finalState = null;

    private Stack<Node> stack = new Stack<Node>();

    public DepthFirstSearch() {

        otherBank = new Node(0, 0, 0, null, null, null);
        initialState = new Node(3, 3, 1, null, "subtract", otherBank);

        actions.add("101");
        actions.add("201");
        actions.add("111");
        actions.add("011");
        actions.add("021");

    }

    public Node solve(){

        stack.push(initialState);

        while (stack.size() != 0 && !goalReached){

            Node node = stack.pop();

            for (String currentAction : actions){

                if (node.getNextArithmetic().equalsIgnoreCase("subtract")){

                    int missionaries = node.getMissionaries() - Integer.parseInt(currentAction.charAt(0) + "");
                    int cannibals = node.getCannibals() - Integer.parseInt(currentAction.charAt(1) + "");
                    int boat = node.getBoat() - Integer.parseInt(currentAction.charAt(2) + "");

                    int otherMissionaries = node.getOtherBank().getMissionaries() + Integer.parseInt(currentAction.charAt(0) + "");
                    int otherCannibals = node.getOtherBank().getCannibals() + Integer.parseInt(currentAction.charAt(1) + "");

                    if (missionaries == 0 && cannibals == 0){
                        Node otherBankNode = new Node(3, 3, 1, null, null, null);
                        Node newNode = new Node(0, 0, 0, currentAction, "add", otherBankNode);

                        newNode.setParent(node);
                        stack.push(newNode);

                        finalState = newNode;
                        goalReached = true;
                        break;
                    }

                    if (missionaries >= 0 && cannibals >= 0 && (missionaries > 0 && (missionaries - cannibals >= 0)) || (missionaries == 0 && cannibals >= 0) && otherMissionaries <= 3 && otherCannibals <= 3){
                        if ((otherMissionaries > 0 && (otherMissionaries - otherCannibals >= 0)) || (otherMissionaries == 0 && otherCannibals >= 0)){
                            String newState = "" + missionaries + cannibals;
                            if (!explored.containsKey(newState)){
                                Node otherSideNode = new Node(otherMissionaries,otherCannibals,1, null, null, null);
                                Node newNode = new Node(missionaries,cannibals,boat,currentAction,"add",otherSideNode);
                                newNode.setParent(node);
                                stack.push(newNode);
                            }
                        }
                    }
                } else if (node.getNextArithmetic().equalsIgnoreCase("add")){

                    int missionaries = node.getMissionaries() + Integer.parseInt(currentAction.charAt(0) + "");
                    int cannibals = node.getCannibals() + Integer.parseInt(currentAction.charAt(1) + "");
                    int boat = node.getBoat() + Integer.parseInt(currentAction.charAt(2) + "");

                    int otherMissionaries = node.getOtherBank().getMissionaries() - Integer.parseInt(currentAction.charAt(0) + "");
                    int otherCannibals = node.getOtherBank().getCannibals() - Integer.parseInt(currentAction.charAt(1) + "");

                    if (missionaries == 0 && cannibals == 0){
                        Node otherBank = new Node(3, 3, 1, null, null, null);
                        Node newNode = new Node(0, 0, 0, currentAction, "add", otherBank);

                        newNode.setParent(node);
                        stack.push(newNode);

                        finalState = newNode;
                        goalReached = true;
                        break;
                    }

                    if (missionaries <= 3 && cannibals <= 3 && (missionaries > 0 && (missionaries - cannibals >= 0)) || (missionaries == 0 && cannibals >= 0) && otherMissionaries >= 0 && otherCannibals >= 0){
                        if ((otherMissionaries > 0 && (otherMissionaries - otherCannibals >= 0)) || (otherMissionaries == 0 && otherCannibals >= 0)){
                            String newState = "" + missionaries + cannibals;
                            if (!explored.containsKey(newState)){
                                explored.put(newState, true);
                                Node otherSideNode = new Node(otherMissionaries, otherCannibals, 0, null, null, null);
                                Node newNode = new Node(missionaries, cannibals, boat, currentAction, "subtract",otherSideNode);
                                newNode.setParent(node);
                                stack.push(newNode);
                            }
                        }
                    }
                }
            }
        }

        return finalState;

    }

}
