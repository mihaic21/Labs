/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.UI;

import java.util.*;

import lab2.Controller.*;

/**
 *
 * @author mihai
 */
public class Console {
    private Controller ctrl;
    private Scanner input = new Scanner(System.in);
    private String command;
    private String menu =
            "\n"
            + "[1] Add student\n"
            + "[2] Delete students until grade 10 is found\n"
            + "[3] Print students\n"
            + "[4] Print number of students\n"
            + "\n"
            + "[x] Exit\n"
            + "\n";
    
    public Console(Controller ctrl){
        this.ctrl = ctrl;
    }
    
    public void run(){
        while (true){
            System.out.println(menu);
            command = input.nextLine();
            if ("1".equals(command)) addStudent();
            else if ("2".equals(command)) deleteStudents();
            else if ("3".equals(command)) printStudents();
            else if ("4".equals(command)) printNoOfStudents();
            else if ("x".equals(command)) System.exit(0);
            else System.out.println("Invalid command");
        }
    }
    
    private void addStudent(){
        System.out.print("Student name: ");
        String name = input.nextLine();
        int id,grade;
        try{
            System.out.print("Student id: ");
            id = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException nfe){
            System.out.println("Invalid id format!");
            return;
        }
        try{
            System.out.print("Student grade: ");
            grade = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException nfe){
            System.out.println("Invalid grade format!");
            return;
        }
        System.out.println(this.ctrl.addStudent(name, id, grade));
    }
    
    private void deleteStudents(){
        System.out.println(this.ctrl.deleteStudentsUntilGrade(10));
    }
    
    private void printStudents(){
        System.out.println(this.ctrl.getAllStudents());
    }
    
    private void printNoOfStudents(){
        System.out.println(this.ctrl.getNoOfStudents());
    }
}
