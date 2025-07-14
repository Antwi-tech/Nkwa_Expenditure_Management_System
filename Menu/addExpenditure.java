package Menu;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import DSA.hashmap;
import DSA.linkedList;

public class addExpenditure {

    static hashmap<String, Expenditure> expenditureMap = new hashmap<>();
    static linkedList historyList = new linkedList();

    static class Expenditure {
        String code;
        double amount;
        LocalDate date;
        String phase;
        String category;
        String accountId;

        public Expenditure(String code, double amount, LocalDate date, String phase, String category, String accountId) {
            this.code = code;
            this.amount = amount;
            this.date = date;
            this.phase = phase;
            this.category = category;
            this.accountId = accountId;
        }

        @Override
        public String toString() {
            return "Code: " + code + "\nAmount: " + amount + "\nDate: " + date +
                    "\nPhase: " + phase + "\nCategory: " + category + "\nAccount: " + accountId;
        }

        public String toCSV() {
            return code + "," + amount + "," + date + "," + phase + "," + category + "," + accountId;
        }
    }

    public static void spending() {
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("\n----- Fill Up All Fields -----");

            String code = getValidInput(s, "Item code", input -> !input.isEmpty());

            double amount = getValidDouble(s, "Amount", input -> input >= 0);

            LocalDate date = getValidDate(s, "Date of issue (YYYY‑MM‑DD)");

            String phase = getValidInput(s, "Phase (Construction/Marketing/Sales)", input -> {
                String p = input.toLowerCase();
                return p.equals("construction") || p.equals("marketing") || p.equals("sales");
            });

            String category = getValidInput(s, "Category", input -> !input.isEmpty());

            String account = getValidInput(s, "Bank Account ID", input -> !input.isEmpty());

            // 🔎 Check if the account exists in hashmap
            bankAccount.BankAccount acc = bankAccount.accounts.get(account);

            if (acc == null) {
                System.out.println("❌ Account ID not found in accounts.txt.");
                System.out.print("➕ Do you want to create it now? (yes/no): ");
                String addNow = s.nextLine().trim().toLowerCase();

                if (!addNow.equals("yes")) {
                    System.out.println("⛔ Cannot proceed without a valid account.");
                    return;
                }

                System.out.print("Enter Account Name: ");
                String name = s.nextLine();

                double initialBalance;
                while (true) {
                    try {
                        System.out.print("Enter Initial Balance: ");
                        initialBalance = Double.parseDouble(s.nextLine().trim());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid number. Try again.");
                    }
                }

                acc = new bankAccount.BankAccount(account, name, initialBalance);
                bankAccount.accounts.put(account, acc);
                bankAccount.queue.add(acc);
                bankAccount.writeAllAccountsToFile();
                System.out.println("✅ Account created with balance: " + initialBalance);
            }


            // 💰 Deduct balance or confirm if insufficient
            if (acc.balance < amount) {
                System.out.println("⚠️ Warning: Insufficient funds. Proceed with negative balance? (yes/no): ");
                String proceed = s.nextLine().trim().toLowerCase();
                if (!proceed.equals("yes")) {
                    System.out.println("❌ Expenditure not recorded.");
                    return;
                }
            }

            acc.balance -= amount;
            acc.history.addLast("Expenditure: -" + amount + " (" + code + ")");
            bankAccount.writeAllAccountsToFile();

            // 📝 Create and log expenditure
            Expenditure e = new Expenditure(
                    code,
                    amount,
                    date,
                    capitalize(phase),
                    capitalize(category),
                    account
            );

            expenditureMap.put(code, e);
            historyList.addLast(code);

            saveToFile(e);

            System.out.println("\n✅ Expenditure Added Successfully!\n");

            expenditureMap.printAll();
            historyList.printList();

            System.out.print("\n➕ Add another expenditure? (yes/no): ");
            String again = s.nextLine().trim().toLowerCase();
            if (!again.equals("yes")) {
                System.out.println("👋 Exiting. Goodbye!");
                break;
            }
        }
    }

    // Save in readable format for .txt
    private static void saveToFile(Expenditure e) {
        try (FileWriter writer = new FileWriter("Menu/expenditures.txt", true)) {
            writer.write(e.toString() + "\n\n");
        } catch (IOException ex) {
            System.out.println("⚠️ Failed to write to file: " + ex.getMessage());
        }
    }

    // Input validators
    private static String getValidInput(Scanner s, String prompt, java.util.function.Predicate<String> isValid) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print(prompt + ": ");
            String input = s.nextLine().trim();
            if (isValid.test(input)) return input;
            System.out.println("❌ Invalid input. Try again.");
            attempts++;
        }
        System.out.println("❌ Too many invalid attempts. Exiting.");
        System.exit(0);
        return null;
    }

    private static double getValidDouble(Scanner s, String prompt, java.util.function.DoublePredicate isValid) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print(prompt + ": ");
            try {
                double value = Double.parseDouble(s.nextLine().trim());
                if (isValid.test(value)) return value;
            } catch (NumberFormatException e) {
                // fall through
            }
            System.out.println("❌ Invalid number. Try again.");
            attempts++;
        }
        System.out.println("❌ Too many invalid attempts. Exiting.");
        System.exit(0);
        return -1;
    }

    private static LocalDate getValidDate(Scanner s, String prompt) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print(prompt + ": ");
            try {
                return LocalDate.parse(s.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("❌ Invalid date format. Use YYYY-MM-DD.");
                attempts++;
            }
        }
        System.out.println("❌ Too many invalid attempts. Exiting.");
        System.exit(0);
        return null;
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
