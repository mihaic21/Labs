/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author mihai
 */
public class Lab1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int n = Integer.parseInt(args[0]);
        int i = n+1;
        //Prime theNumber = new Prime();
        while (true){
            if (Prime.isPrime(i)){
                break;
            }
            i++;
        }
        System.out.println(i);
    }
}


