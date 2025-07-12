import Menu.addExpenditure;
import Menu.viewExpenditure;

import java.util.Scanner;

public class Expenditure_Management_System {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("------Welcome To Nkwa Tracking App------\n");
        System.out.print("Select an option:\n1. Add Expenditure\n2. View Expenditure\n3. Manage Categories" +
                "\n4. Manage Categories\n5. Bank Overview\n6. Search and Sort Expenditures\n" +
                "7.Generate report\n8. Exit\n:  ");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                addExpenditure.spending();
            case 2:
                viewExpenditure.searchExpenditure();
            case 3:
//                mmww
            case 4:
//                wwww
            case 5:
//                nkwkwk

        }



    }


}