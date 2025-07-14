package Menu;
import java.util.Scanner;

public class bankAccount {
    public static void account(){
        System.out.println("1. List account" +
                "2. Add Account" +
                "3. Top-up ");

        Scanner s = new Scanner(System.in);
        String choice  = s.nextLine();
        switch (choice) {
            case "1":
                listaccount();
            case "2":
                addacount();

            case "3":
                topup();
        }

    }

    public static void listaccount(){

    }
    public static void addacount(){

    }
    public static void topup(){

    }
}
