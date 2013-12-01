package UI;

import java.util.Scanner;
import Controller.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: mihai
 * Date: 11/27/13
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
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
    private String addStudentMenu =
            "\n"
            + "[1] Add Regular Student\n"
            + "[2] Add Undergraduate Student\n"
            + "[3] Add Graduate Student \n"
            + "[4] Add PhD Student\n\n";

    public Console(Controller ctrl){
        this.ctrl = ctrl;
        this.run();
    }

    private void run(){
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
        System.out.println(addStudentMenu);
        command = input.nextLine();
        if ("1".equals(command)) this.addRegularStudent();
        else if ("2".equals(command)) this.addUndergraduateStudent();
        else if ("3".equals(command)) this.addGraduateStudent();
        else if ("4".equals(command)) this.addPhDStudent();
        else System.out.println("Invalid command!");
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

    private void addRegularStudent(){
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
        System.out.println(this.ctrl.addStudent(id, name, grade));
    }

    private void addUndergraduateStudent(){
        System.out.print("Student name: ");
        String name = input.nextLine();
        int id,grade,grade2;
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
            System.out.print("Student grade2: ");
            grade2 = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException nfe){
            System.out.println("Invalid grade format!");
            return;
        }

        System.out.println(ctrl.addStudent(id, name, grade, grade2));
    }

    private void addGraduateStudent(){
        System.out.print("Student name: ");
        String name = input.nextLine();
        int id,grade,grade2,grade3;
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
            System.out.print("Student grade2: ");
            grade2 = Integer.parseInt(input.nextLine());
            System.out.print("Student grade3: ");
            grade3 = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException nfe){
            System.out.println("Invalid grade format!");
            return;
        }
        System.out.print("Supervisor name: ");
        String supervisor = input.nextLine();
        System.out.println(ctrl.addStudent(id, name, grade, supervisor, grade2, grade3));
    }

    private void addPhDStudent(){
        System.out.print("Student name: ");
        String name = input.nextLine();
        int id,grade,grade2;
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
            System.out.print("Student grade2: ");
            grade2 = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException nfe){
            System.out.println("Invalid grade format!");
            return;
        }

        System.out.print("Supervisor name: ");
        String supervisor = input.nextLine();
        System.out.print("Thesis name: ");
        String thesis = input.nextLine();

        System.out.println(ctrl.addStudent(id, name, grade, supervisor, thesis, grade2));
    }
}
