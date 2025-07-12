package Menu;

import java.time.LocalDate;
import java.util.Scanner;
import Menu.addExpenditure;
import Menu.addExpenditure.Expenditure;

import static Menu.addExpenditure.expenditureMap;
import static Menu.addExpenditure.historyList;

public class viewExpenditure {

    public static void searchExpenditure() {
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("\n🔍 View/Search Expenditure Menu:");
            System.out.println("1. List all (chronological)");
            System.out.println("2. Sort by category (A‑Z)");
            System.out.println("3. Search by date range");
            System.out.println("4. Search by category");
            System.out.println("5. Search by bank account");
            System.out.println("0. Exit");

            System.out.print("Select option: ");
            String choice = s.nextLine().trim();

            switch (choice) {
                case "1":
                    listChronological();
                    break;
                case "2":
                    sortByCategory();
                    break;
                case "3":
                    searchByDateRange(s);
                    break;
                case "4":
                    searchByCategory(s);
                    break;
                case "5":
                    searchByAccount(s);
                    break;
                case "0":
                    System.out.println("📤 Returning to main menu...");
                    return;
                default:
                    System.out.println("❌ Invalid input. Try again.");
            }
        }
    }

    private static void listChronological() {
        System.out.println("\n📅 Expenditure in Chronological Order:");
        for (String code : historyList.toArray()) {
            Expenditure e = expenditureMap.get(code);
            if (e != null) {
                System.out.println(e + "\n------------------");
            }
        }
    }

    private static void sortByCategory() {
        String[] codes = historyList.toArray();
        for (int i = 0; i < codes.length - 1; i++) {
            for (int j = i + 1; j < codes.length; j++) {
                Expenditure e1 = expenditureMap.get(codes[i]);
                Expenditure e2 = expenditureMap.get(codes[j]);
                if (e1.category.compareToIgnoreCase(e2.category) > 0) {
                    String temp = codes[i];
                    codes[i] = codes[j];
                    codes[j] = temp;
                }
            }
        }
        System.out.println("\n📂 Sorted by Category:");
        for (String code : codes) {
            System.out.println(expenditureMap.get(code) + "\n------------------");
        }
    }

    private static void searchByDateRange(Scanner s) {
        try {
            System.out.print("Start Date (YYYY-MM-DD): ");
            LocalDate start = LocalDate.parse(s.nextLine().trim());
            System.out.print("End Date (YYYY-MM-DD): ");
            LocalDate end = LocalDate.parse(s.nextLine().trim());

            System.out.println("\n📆 Expenditure from " + start + " to " + end + ":");
            for (String code : historyList.toArray()) {
                Expenditure e = expenditureMap.get(code);
                if (!e.date.isBefore(start) && !e.date.isAfter(end)) {
                    System.out.println(e + "\n------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Invalid date format.");
        }
    }

    private static void searchByCategory(Scanner s) {
        System.out.print("Enter category to search: ");
        String cat = s.nextLine().trim().toLowerCase();
        for (String code : historyList.toArray()) {
            Expenditure e = expenditureMap.get(code);
            if (e.category.toLowerCase().equals(cat)) {
                System.out.println(e + "\n------------------");
            }
        }
    }

    private static void searchByAccount(Scanner s) {
        System.out.print("Enter account ID to search: ");
        String acc = s.nextLine().trim();
        for (String code : historyList.toArray()) {
            Expenditure e = expenditureMap.get(code);
            if (e.accountId.equals(acc)) {
                System.out.println(e + "\n------------------");
            }
        }
    }
}
