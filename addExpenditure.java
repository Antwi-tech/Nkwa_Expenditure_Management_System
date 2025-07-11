import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class addExpenditure {
    public static void spending(){
        Scanner s  = new Scanner(System.in);
        System.out.println("\n-----Fill Up All Fileds");

        System.out.println("Item code: ");
        String code =  s.nextLine();

        System.out.println("Amount: ");
        double amount = Double.parseDouble(s.nextLine());

        System.out.println("Date of issue: ");
        LocalDate date = LocalDate.parse(s.nextLine().trim());

        System.out.println("Phase (Construction/Marketing.Sales: ");
        String phase =  s.nextLine();

        System.out.println("Category");
        String category = s.nextLine();

        System.out.println("Bank Account ID: ");
        String account = s.nextLine();









    }





}
