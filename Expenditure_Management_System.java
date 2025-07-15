import Menu.*;

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
                    "6. Receipt\n" +
                    "7. Performance\n" +
                    "8. Save & Exit\n: ");

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
                    reportAnalysis.report();
                    break;
                case "6":
                    System.out.println("📎 Launching Receipt/Invoice Manager...");
                    receipt.loadReceipt();
                    break;
                case "7":
                    DSAPerformanceTest.run();
                    break;

                case "8":
                    System.out.println("✅ Progress saved. Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }


        }
    }
}
