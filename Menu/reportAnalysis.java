package Menu;

import DSA.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class reportAnalysis {
    public static void report() {
        Scanner s = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.println("""
            \n📊 Report & Analytics Menu:
            1. Monthly burn‑rate
            2. Profitability forecast
            3. Material/Category price impact
            4. Exit to Main Menu\n: """);

            choice = s.nextLine().trim();

            switch (choice) {
                case "1" -> monthlyReport();
                case "2" -> profitReport();
                case "3" -> categoryPriceReport();
                case "4" -> {
                    System.out.println("↩️ Returning to main menu...");
                    return;
                }
                default -> System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    public static void monthlyReport() {
        myTreeMap tree = new myTreeMap();

        try (BufferedReader br = new BufferedReader(new FileReader("Menu/expenditures.txt"))) {
            String line, code = null;
            double amount = 0;
            LocalDate date = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Code: ")) code = line.substring(6).trim();
                else if (line.startsWith("Amount: ")) amount = Double.parseDouble(line.substring(8).trim());
                else if (line.startsWith("Date: ")) date = LocalDate.parse(line.substring(6).trim());
                else if (line.trim().isEmpty() && code != null && date != null) {
                    YearMonth ym = YearMonth.from(date);
                    tree.put(ym, amount);
                    code = null; amount = 0; date = null;
                }
            }

            System.out.println("\n📅 Monthly Burn-rate:");
            tree.printInOrder();

        } catch (IOException e) {
            System.out.println("⚠️ Error reading file: " + e.getMessage());
        }
    }

    public static void profitReport() {
        System.out.println("💰 Profitability forecasting not yet implemented.");
    }

    public static void categoryPriceReport() {
        hashmap<String, Double> categoryTotals = new hashmap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Menu/expenditures.txt"))) {
            String line, category = null;
            double amount = 0;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Category: ")) category = line.substring(10).trim();
                else if (line.startsWith("Amount: ")) amount = Double.parseDouble(line.substring(8).trim());
                else if (line.trim().isEmpty() && category != null) {
                    Double currentTotal = categoryTotals.get(category);
                    if (currentTotal == null) currentTotal = 0.0;
                    categoryTotals.put(category, currentTotal + amount);
                    category = null; amount = 0;
                }
            }

            System.out.println("\n📊 Category Price Impact:");
            categoryTotals.printAll();

        } catch (IOException e) {
            System.out.println("⚠️ Error reading file: " + e.getMessage());
        }
    }
}
