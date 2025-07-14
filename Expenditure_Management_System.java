import Menu.addExpenditure;
import Menu.bankAccount;
import Menu.manageCategories;
import Menu.viewExpenditure;

import java.util.Scanner;

public class Expenditure_Management_System {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n------Welcome To Nkwa Tracking App------\n");
            System.out.print("Select an option:\n" +
                    "1. Add Expenditure\n" +
                    "2. View Expenditure\n" +
                    "3. Manage Categories\n" +
                    "4. Bank Accounts\n" +
                    "5. Reports & Analytics\n" +
                    "6. Save & Exit\n: ");

            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    addExpenditure.spending();
                    break;
                case "2":
                    viewExpenditure.searchExpenditure();
                    break;
                case "3":
                    manageCategories.categories();
                    break;
                case "4":
                    bankAccount.account();
                    break;
                case "5":
                    System.out.println("📊 Reports & Analytics feature coming soon!");
                    break;
                case "6":
                    System.out.println("✅ Progress saved. Exiting...");
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }
}
