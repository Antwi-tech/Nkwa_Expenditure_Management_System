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
    }

    public static void spending() {
        Scanner s = new Scanner(System.in);
        System.out.println("\n----- Fill Up All Fields -----");

        String code = getValidInput(s, "Item code", input -> !input.isEmpty());

        double amount = getValidDouble(s, "Amount", input -> input >= 0);

        LocalDate date = getValidDate(s, "Date of issue (YYYY‑MM‑DD)");

        String phase = getValidInput(s, "Phase (Construction/Marketing/Sales)", input ->
                input.equalsIgnoreCase("construction") ||
                        input.equalsIgnoreCase("marketing") ||
                        input.equalsIgnoreCase("sales"));

        String category = getValidInput(s, "Category", input -> !input.isEmpty());

        String account = getValidInput(s, "Bank Account ID", input -> !input.isEmpty());

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

        System.out.println("\n✅ Expenditure Added Successfully!\n");
        expenditureMap.printAll();
        historyList.printList();
    }

    // Utility to retry string input
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
        return null; // unreachable
    }

    // Utility to retry double input
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
        return -1; // unreachable
    }

    // Utility to retry date input
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
        return null; // unreachable
    }

    // Capitalize helper
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
