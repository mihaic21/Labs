package UI;
import Controller.Controller;

import java.util.Scanner;

/**
 * Created by mihai on 2/3/14.
 */
public class Console {

    private Controller ctrl;

    private Scanner input = new Scanner(System.in);
    private String command;
    private String menu =
            "\n"
                    + "[1] Add Goodies\n"
                    + "[2] Print All Goodies\n"
                    + "[3] Print with given type of serving and cheaper than\n"
                    + "[4] Save in text file\n"
                    + "\n"
                    + "[x] Exit\n"
                    + "\n";

    private String addGoodiesMenu =
            "\n"
            + "[1] Add Coffee\n"
            + "[2] Add Sandwich\n"
            + "[3] Add Cake\n\n";

    public Console(){}

    public Console(Controller ctrl){
        this.ctrl = ctrl;
        this.run();
    }




    private void run(){

        while (true){
            System.out.println(menu);
            command = input.nextLine();

            if ("1".equals(command)){
                addGoodies();
            } else if ("2".equals(command)){
                printAll();
            } else if ("3".equals(command)){
                printWithCond();
            } else if ("4".equals(command)){
                saveInFile();
            } else if ("x".equals(command)){
                System.exit(0);
            } else {
                System.out.println("Invalid command");
            }


        }

    }

    private void saveInFile() {
        System.out.println(this.ctrl.saveToFile());
    }

    private void printWithCond() {
        System.out.println("type of serving: ");
        String typeOfServing = input.nextLine();
        System.out.println("max price");
        try{
            int price = Integer.parseInt(input.nextLine());
            System.out.println(this.ctrl.viewWithCond(typeOfServing, price));
        } catch (NumberFormatException ex){
            System.out.println("not good");
        }

    }

    private void printAll() {
        System.out.println(this.ctrl.getAllGoodies());
    }

    private void addGoodies() {
        try{
            System.out.println(this.addGoodiesMenu);
            command = input.nextLine();

            if ("1".equals(command)){
                System.out.println("type of coffee: ");
                String typeOfCoffee = input.nextLine();
                System.out.println("type of serving: ");
                String typeOfServing = input.nextLine();
                System.out.println("price: ");
                int price = Integer.parseInt(input.nextLine());
                System.out.println(this.ctrl.addGoodie(typeOfCoffee, typeOfServing, price));

            } else if ("2".equals(command)){
                System.out.println("weight: ");
                int weight = Integer.parseInt(input.nextLine());
                System.out.println("content: ");
                String content = input.nextLine();
                System.out.println("type of serving: ");
                String typeOfServing = input.nextLine();
                System.out.println("price: ");
                int price = Integer.parseInt(input.nextLine());
                System.out.println(this.ctrl.addGoodie(weight, content, typeOfServing, price));
            } else if ("3".equals(command)){
                System.out.println("shape: ");
                String shape = input.nextLine();
                System.out.println("type of cake: ");
                String typeOfCake = input.nextLine();
                System.out.println("price: ");
                int price = Integer.parseInt(input.nextLine());
                String typeOfServing = "";
                System.out.println(this.ctrl.addGoodie(shape, typeOfCake, typeOfServing, price));
            } else System.out.println("Invalid command");
        } catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }

}
