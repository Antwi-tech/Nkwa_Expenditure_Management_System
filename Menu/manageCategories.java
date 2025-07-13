package Menu;
import java.util.Scanner;

public class manageCategories {
    public static void categories(){
        Scanner s = new Scanner(System.in);
        System.out.println("1. List Categories" +
                "2. Add Category" +
                "3. Delete Category");


        String choice = s.nextLine();

        switch (choice){
            case "1" :
                listcategories();
            case "2":
                addcategory();
            case "3":
                deletecategory();
        }
    }

    public static void listcategories(){



    }

    public static void addcategory(){


    }

    public static void deletecategory(){

    }

}
