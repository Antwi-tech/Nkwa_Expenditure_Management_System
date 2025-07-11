import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class addExpenditure {
    public static void spending(){
        Scanner s  = new Scanner(System.in);
        System.out.println("\n-----Fill Up All Field-----");

        System.out.print("Item code: ");
        String code =  s.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(s.nextLine());

        System.out.print("Date of issue (YYYY‑MM‑DD): ");
        LocalDate date = LocalDate.parse(s.nextLine().trim());

        System.out.print("Phase (Construction/Marketing.Sales: ");
        String phase =  s.nextLine();

        System.out.print("Category");
        String category = s.nextLine();

        System.out.print("Bank Account ID: ");
        String account = s.nextLine();







    }





}
