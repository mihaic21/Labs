/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author mihai
 */
public class Prime {
    public static boolean isPrime(int n) {
        if (n == 1) return false;
        if (n == 2) return true;
        if (n%2 == 0) return false;
        for (int i=3; i*i <= n; i+=2){
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }
}
