/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.*;

/**
 *
 * @author mihai
 */
public class Graph {
    
    private int n; //no of vertices
    private int m; //no of edges
    private Hashtable<Integer, Hashtable<Integer, Integer>> inEdges;
    private Hashtable<Integer, Hashtable<Integer, Integer>> outEdges;
    
    public Graph(){
        this.n = 0;
        this.m = 0;
        this.inEdges = new Hashtable<Integer, Hashtable<Integer, Integer>>();
        this.outEdges = new Hashtable<Integer, Hashtable<Integer, Integer>>();
    }
    
    public Graph(int n, int m){
        this.n = n;
        this.m = m;
        this.inEdges = new Hashtable<Integer, Hashtable<Integer, Integer>>();
        this.outEdges = new Hashtable<Integer, Hashtable<Integer, Integer>>();
    }
    
    public void addEntry(int x, int y, int cost){
        Hashtable<Integer, Integer> inEntry = new Hashtable<Integer, Integer>();
        Hashtable<Integer, Integer> outEntry = new Hashtable<Integer, Integer>();
        
        inEntry.put(x, cost);
        outEntry.put(y, cost);
        
        if (inEdges.containsKey(y))
            inEdges.get(y).put(x, cost);
        else
            inEdges.put(y, inEntry);
        
        if (outEdges.containsKey(x))
            outEdges.get(x).put(y, cost);
        else
            outEdges.put(x, outEntry);
    }
    
    public void displayGraph(){
        for (Integer key: inEdges.keySet())
            for (Integer key2: inEdges.get(key).keySet())
                System.out.println("To "+key+" from "+key2+" with cost "+ inEdges.get(key).get(key2));
        
        for (Integer key: outEdges.keySet())
            for (Integer key2: outEdges.get(key).keySet())
                System.out.println("From "+key+" to "+key2+" with cost "+ outEdges.get(key).get(key2));
    }
    
    public int getNoOfVertices(){
        return n;
    }
    
    public int getNoOfEdges(){
        return m;
    }
    
    public void setN(int n){
        this.n = n;
    }
    
    public void setM(int m){
        this.m = m;
    }
    
    public int getCost(int x, int y){
        return outEdges.get(x).get(y);
    }
    
    public void setCost(int x, int y, int cost){
        outEdges.get(x).remove(y);
        inEdges.get(y).remove(x);
        
        Hashtable<Integer, Integer> inEntry = new Hashtable<Integer, Integer>();
        Hashtable<Integer, Integer> outEntry = new Hashtable<Integer, Integer>();
        
        inEntry.put(x, cost);
        outEntry.put(y, cost);
        
        if (inEdges.containsKey(y))
            inEdges.get(y).put(x, cost);
        else
            inEdges.put(y, inEntry);
        
        if (outEdges.containsKey(x))
            outEdges.get(x).put(y, cost);
        else
            outEdges.put(x, outEntry);
    }
    
    public boolean checkEdge(Integer x, Integer y){
        return (outEdges.containsKey(x) && outEdges.get(x).containsKey(y));
    }
    
    public int getInDegree(int x){
        try{
            return inEdges.get(x).keySet().size();
        }catch (NullPointerException ex){
            return 0;
        }
    }
    
    public int getOutDegree(int x){
        try{
            return outEdges.get(x).keySet().size();
        }catch (NullPointerException ex){
            return 0;
        }
    }
    
    public Set<Integer> getOutEdges(int x){
        try{
            return outEdges.get(x).keySet();
        }catch (NullPointerException ex){
            return null;
        }
    }
    
    public Set<Integer> getInEdges(int x){
        try{
            return inEdges.get(x).keySet();
        }catch (NullPointerException ex){
            return null;
        }
    }
    
    public String addEdge(int x, int y, int cost){
        m++;
        addEntry(x, y, cost);
        return "Edge ("+x+","+y+") added!";
    }
    
    public String deleteEdge(int x, int y){
        m--;
        outEdges.get(x).remove(y);
        inEdges.get(y).remove(x);
        return "Edge ("+x+","+y+") deleted!";
    }
    
    public String removeVertex(int x){
        outEdges.remove(x);
        inEdges.remove(x);
        
        for (Integer key: inEdges.keySet())
            if (inEdges.get(key).containsKey(x))
                inEdges.get(key).remove(x);
        
        for (Integer key: outEdges.keySet())
            if (outEdges.get(key).containsKey(x))
                outEdges.get(key).remove(x);
        
        n--;
        return "Vertex "+x+" removed!";
    }
    
    public int addVertex(){
        n++;
        
        Hashtable<Integer, Integer> inEntry = new Hashtable<Integer,Integer>();
        Hashtable<Integer, Integer> outEntry = new Hashtable<Integer,Integer>();
        
        inEdges.put(n-1, inEntry);
        outEdges.put(n-1, outEntry);
        
        return n-1;
    }
    
}
