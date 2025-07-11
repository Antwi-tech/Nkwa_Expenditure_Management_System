import java.time.LocalDate;
import java.util.Scanner;
import DSA.hashmap;
import DSA.linkedList;

public class addExpenditure {
    // Static so we can store between calls
    static hashmap<String, Expenditure> expenditureMap = new hashmap<>();
    static linkedList historyList = new linkedList();

    public static void spending() {
        Scanner s = new Scanner(System.in);
        System.out.println("\n----- Fill Up All Fields -----");

        System.out.print("Item code: ");
        String code = s.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(s.nextLine());

        System.out.print("Date of issue (YYYY‑MM‑DD): ");
        LocalDate date = LocalDate.parse(s.nextLine().trim());

        System.out.print("Phase (Construction/Marketing/Sales): ");
        String phase = s.nextLine();

        System.out.print("Category: ");
        String category = s.nextLine();

        System.out.print("Bank Account ID: ");
        String account = s.nextLine();

        Expenditure e = new Expenditure(code, amount, date, phase, category, account);

        expenditureMap.put(code, e);
        historyList.addLast(code);

        System.out.println("\n✅ Expenditure Added Successfully!\n");

        // Optional: Show the current list
        expenditureMap.printAll();
        historyList.printList();
    }
}
