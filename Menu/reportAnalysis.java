package Menu;
import java.util.Scanner;

public class reportAnalysis {
    public static void report(){
        System.out.println("""
        1. Monthly burn‑rate
        2. Profitability forecast
        3. Material/Category price impact""");

        Scanner s = new Scanner(System.in);
        String choice = s.nextLine();
        switch (choice){
            case "1":
                monthlyReport();

            case "2":
                profitReport();

            case "3":
                categoryPriceReport();

        }
    }

    public static void monthlyReport(){

    }

    public static void profitReport(){

    }

    public static void categoryPriceReport(){


    }
}

