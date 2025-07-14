package Menu;

import DSA.arrayList;
import DSA.hashmap;
import DSA.myTreeMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class reportAnalysis {
    public static void report() {
        System.out.println("""
            1. Monthly burn‑rate
            2. Profitability forecast
            3. Material/Category price impact""");

        Scanner s = new Scanner(System.in);
        String choice = s.nextLine();
        switch (choice) {
            case "1" -> monthlyReport();
            case "2" -> profitReport();
            case "3" -> categoryPriceReport();
            default -> System.out.println("Invalid choice.");
        }
    }

    static class ExpenditureEntry {
        double amount;
        String category;
        LocalDate date;

        ExpenditureEntry(double amount, String category, LocalDate date) {
            this.amount = amount;
            this.category = category;
            this.date = date;
        }
    }

    private static arrayList<ExpenditureEntry> readExpenditures() {
        arrayList<ExpenditureEntry> entries = new arrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Menu/expenditures.txt"))) {
            String line;
            double amount = 0;
            String category = null;
            LocalDate date = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Amount: ")) {
                    amount = Double.parseDouble(line.substring(8).trim());
                } else if (line.startsWith("Category: ")) {
                    category = line.substring(10).trim();
                } else if (line.startsWith("Date: ")) {
                    date = LocalDate.parse(line.substring(6).trim());
                } else if (line.trim().isEmpty() && category != null && date != null) {
                    entries.add(new ExpenditureEntry(amount, category, date));
                    amount = 0;
                    category = null;
                    date = null;
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error reading expenditures file: " + e.getMessage());
        }

        return entries;
    }

    public static void monthlyReport() {
        arrayList<ExpenditureEntry> entries = readExpenditures();
        myTreeMap tree = new myTreeMap();
        double total = 0;

        for (int i = 0; i < entries.size(); i++) {
            ExpenditureEntry entry = entries.get(i);
            YearMonth ym = YearMonth.from(entry.date);
            tree.put(ym, entry.amount);
            total += entry.amount;
        }

        System.out.println("\n📅 Monthly Burn-rate:");
        tree.printInOrder();
        System.out.printf("\n🔎 Total Burn: GHS %.2f\n", total);
        double avg = total / entries.size();
        System.out.printf("📉 Average Spend per Entry: GHS %.2f\n", avg);

        if (avg > 200) {
            System.out.println("⚠️ High burn rate detected. Consider budgeting strategies.");
        } else {
            System.out.println("✅ Burn rate is within a manageable range.");
        }
    }

    public static void profitReport() {
        arrayList<ExpenditureEntry> entries = readExpenditures();
        double total = 0;

        for (int i = 0; i < entries.size(); i++) {
            total += entries.get(i).amount;
        }

        double assumedIncome = 2000.00;
        System.out.printf("\n📈 Assumed Monthly Income: GHS %.2f\n", assumedIncome);
        System.out.printf("📉 Total Expenses: GHS %.2f\n", total);

        if (total > assumedIncome) {
            System.out.println("❌ Expenses exceed income. You are operating at a loss.");
        } else {
            System.out.println("✅ You're saving money monthly. Good job!");
        }
    }

    public static void categoryPriceReport() {
        arrayList<ExpenditureEntry> entries = readExpenditures();
        hashmap<String, Double> categoryTotals = new hashmap<>();

        for (int i = 0; i < entries.size(); i++) {
            ExpenditureEntry e = entries.get(i);
            Double current = categoryTotals.get(e.category);
            if (current == null) current = 0.0;
            categoryTotals.put(e.category, current + e.amount);
        }

        System.out.println("\n📊 Category Price Impact:");
        categoryTotals.printAll();
    }
}
