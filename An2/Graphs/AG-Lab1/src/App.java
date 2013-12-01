/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mihai
 */

import Model.MyException;
import Service.DIGraphService;

public class App {

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DIGraphService service = new DIGraphService();
	try{
            service.readGraph("graph.txt");
            service.testApp();
	}catch(MyException ex){
            System.err.println(ex.getMessage());
	}
    }
}
