package Menu;

import DSA.linkedList;
import DSA.arrayList;
import Menu.addExpenditure.Expenditure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Scanner;

// Remove the import below if not using shared data
// import static Menu.addExpenditure.expenditureMap;
// import static Menu.addExpenditure.historyList;

public class viewExpenditure {

    private static final linkedList historyList = new linkedList();
    private static final MyMap expenditureMap = new MyMap();

    public static void searchExpenditure() {
        loadExpendituresFromFile(); // Load data from txt file

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

    private static void loadExpendituresFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Menu/expenditures.txt"))) {
            String line;
            String code = null, category = null, account = null, phase = null;
            double amount = 0;
            LocalDate date = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("Code:")) {
                    code = line.substring(5).trim();
                } else if (line.startsWith("Amount:")) {
                    amount = Double.parseDouble(line.substring(7).trim());
                } else if (line.startsWith("Date:")) {
                    date = LocalDate.parse(line.substring(5).trim());
                } else if (line.startsWith("Phase:")) {
                    phase = line.substring(6).trim();
                } else if (line.startsWith("Category:")) {
                    category = line.substring(9).trim();
                } else if (line.startsWith("Account:")) {
                    account = line.substring(8).trim();
                    if (code != null) {
                        Expenditure e = new Expenditure(code, amount, date, phase, category, account);
                        expenditureMap.put(code, e);
                        historyList.addLast(code);

                        // reset for next entry
                        code = category = account = phase = null;
                        amount = 0;
                        date = null;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
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
        arrayList<String> sortedCodes = new arrayList<>();

        for (String code : codes) sortedCodes.add(code);

        for (int i = 0; i < sortedCodes.size() - 1; i++) {
            for (int j = i + 1; j < sortedCodes.size(); j++) {
                Expenditure e1 = expenditureMap.get(sortedCodes.get(i));
                Expenditure e2 = expenditureMap.get(sortedCodes.get(j));
                if (e1.category.compareToIgnoreCase(e2.category) > 0) {
                    sortedCodes.swap(i, j);
                }
            }
        }

        System.out.println("\n📂 Sorted by Category:");
        for (int i = 0; i < sortedCodes.size(); i++) {
            System.out.println(expenditureMap.get(sortedCodes.get(i)) + "\n------------------");
        }
    }

    private static void searchByDateRange(Scanner s) {
        try {
            System.out.print("Start Date (YYYY-MM-DD): ");
            LocalDate start = LocalDate.parse(s.nextLine().trim());
            System.out.print("End Date (YYYY-MM-DD): ");
            LocalDate end = LocalDate.parse(s.nextLine().trim());

            System.out.println("\n📆 Expenditures from " + start + " to " + end + ":");
            for (String code : historyList.toArray()) {
                Expenditure e = expenditureMap.get(code);
                if (!e.date.isBefore(start) && !e.date.isAfter(end)) {
                    System.out.println(e + "\n------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Invalid date input.");
        }
    }

    private static void searchByCategory(Scanner s) {
        System.out.print("Enter category: ");
        String input = s.nextLine().trim().toLowerCase();
        for (String code : historyList.toArray()) {
            Expenditure e = expenditureMap.get(code);
            if (e.category.toLowerCase().equals(input)) {
                System.out.println(e + "\n------------------");
            }
        }
    }

    private static void searchByAccount(Scanner s) {
        System.out.print("Enter account number: ");
        String acc = s.nextLine().trim();
        for (String code : historyList.toArray()) {
            Expenditure e = expenditureMap.get(code);
            if (e.accountId.equals(acc)) {
                System.out.println(e + "\n------------------");
            }
        }
    }

    // -------- Custom HashMap implementation (minimal) --------
    private static class MyMap {
        private final arrayList<Entry> entries = new arrayList<>();

        private static class Entry {
            String key;
            Expenditure value;

            Entry(String key, Expenditure value) {
                this.key = key;
                this.value = value;
            }
        }

        void put(String key, Expenditure value) {
            for (int i = 0; i < entries.size(); i++) {
                if (entries.get(i).key.equals(key)) {
                    entries.get(i).value = value;
                    return;
                }
            }
            entries.add(new Entry(key, value));
        }

        Expenditure get(String key) {
            for (int i = 0; i < entries.size(); i++) {
                if (entries.get(i).key.equals(key)) {
                    return entries.get(i).value;
                }
            }
            return null;
        }
    }
}
