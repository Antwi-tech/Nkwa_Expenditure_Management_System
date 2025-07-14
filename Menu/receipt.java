package Menu;

import DSA.stack;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class receipt {
    static class Receipt {
        String expenditureCode;
        String fileName;
        String uploader;
        String status; // "Pending", "Reviewed"

        public Receipt(String code, String fileName, String uploader) {
            this.expenditureCode = code;
            this.fileName = fileName;
            this.uploader = uploader;
            this.status = "Pending";
        }

        @Override
        public String toString() {
            return "Expenditure: " + expenditureCode + "\nReceipt: " + fileName + "\nUploader: " + uploader + "\nStatus: " + status;
        }
    }

    static stack<Receipt> uploadQueue = new stack<>();

    public static void loadReceipt() {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("\n📎 Receipt & Invoice Menu:");
            System.out.println("1. Upload Receipt");
            System.out.println("2. Review Receipt");
            System.out.println("3. Show Queue");
            System.out.println("0. Back");

            System.out.print("Choose: ");
            String choice = s.nextLine().trim();

            switch (choice) {
                case "1":
                    uploadReceipt(s);
                    break;
                case "2":
                    reviewReceipt();
                    break;
                case "3":
                    showQueue();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void uploadReceipt(Scanner s) {
        System.out.print("Enter Expenditure Code: ");
        String code = s.nextLine().trim();

        System.out.print("Enter Receipt Filename (e.g. receipt123.pdf): ");
        String file = s.nextLine().trim();

        System.out.print("Enter Uploader Name: ");
        String uploader = s.nextLine().trim();

        Receipt r = new Receipt(code, file, uploader);
        uploadQueue.push(r);
        saveReceiptToFile(r);

        System.out.println("✅ Receipt uploaded and added to queue.");
    }

    private static void reviewReceipt() {
        if (uploadQueue.isEmpty()) {
            System.out.println("📭 No receipts to review.");
            return;
        }

        Receipt r = uploadQueue.pop();
        r.status = "Reviewed";

        System.out.println("\n🧾 Reviewing Receipt:");
        System.out.println(r);
    }

    private static void showQueue() {
        System.out.println("\n📥 Upload Queue:");
        uploadQueue.printStack();
    }

    private static void saveReceiptToFile(Receipt r) {
        try (FileWriter fw = new FileWriter("Menu/receipts.txt", true)) {
            fw.write(r.toString() + "\n---\n");
        } catch (IOException e) {
            System.out.println("⚠️ Failed to save receipt: " + e.getMessage());
        }
    }
}
