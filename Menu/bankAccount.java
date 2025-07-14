package Menu;

import DSA.hashmap;
import DSA.priorityqueue;
import DSA.linkedList;

import java.io.*;
import java.util.Scanner;

public class bankAccount {

    static hashmap<String, BankAccount> accounts = new hashmap<>();
    static priorityqueue<BankAccount> queue = new priorityqueue<>();
    static final String FILE_NAME = "Menu/accounts.txt";

    static {
        loadAccountsFromFile();
    }

    public static void account() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n==== Bank Account Menu ====");
            System.out.println("1. List Accounts");
            System.out.println("2. Add Account");
            System.out.println("3. Top-up");
            System.out.println("4. Show Lowest Balance");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listAccounts();
                    break;
                case "2":
                    addAccount();
                    break;
                case "3":
                    topUp();
                    break;
                case "4":
                    showLowestBalance();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void listAccounts() {
        System.out.println("\n=== Account List ===");
        accounts.printAll();
    }

    static void addAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Account ID: ");
        String id = scanner.nextLine();

        if (accounts.get(id) != null) {
            System.out.println("Account already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance;
        try {
            balance = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid balance.");
            return;
        }

        BankAccount acc = new BankAccount(id, name, balance);
        accounts.put(id, acc);
        queue.add(acc);
        writeAllAccountsToFile();

        System.out.println("Account added.");
    }

    static void topUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Account ID: ");
        String id = scanner.nextLine();

        BankAccount acc = accounts.get(id);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter top-up amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }

        acc.balance += amount;
        acc.history.addLast("Top-up: +" + amount);
        writeAllAccountsToFile();

        System.out.println("Top-up successful. New balance: " + acc.balance);
        System.out.print("History: ");
        acc.history.printList();
    }

    static void showLowestBalance() {
        BankAccount lowest = queue.peek();
        if (lowest == null) {
            System.out.println("No accounts.");
            return;
        }

        System.out.println("Lowest balance:");
        System.out.println("ID: " + lowest.id + ", Name: " + lowest.name + ", Balance: " + lowest.balance);
    }

    static void loadAccountsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Format: id,name,balance
                String[] parts = line.split(",");
                if (parts.length != 3) continue;

                String id = parts[0];
                String name = parts[1];
                double balance = Double.parseDouble(parts[2]);

                BankAccount acc = new BankAccount(id, name, balance);
                accounts.put(id, acc);
                queue.add(acc);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    static void writeAllAccountsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < 16; i++) {
                DSA.hashmap.Node<String, BankAccount> node = accounts.buckets[i];
                while (node != null) {
                    bw.write(((hashmap.Node<String, BankAccount>) node).value.toFileString());
                    bw.newLine();
                    node = ((hashmap.Node<String, BankAccount>) node).next;
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // 🟡 BankAccount INNER CLASS (Self-contained)
    public static class BankAccount implements Comparable<BankAccount> {
        String id;
        String name;
        double balance;
        linkedList history = new linkedList();

        public BankAccount(String id, String name, double balance) {
            this.id = id;
            this.name = name;
            this.balance = balance;
        }

        @Override
        public int compareTo(BankAccount o) {
            return Double.compare(this.balance, o.balance);
        }

        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Balance: " + balance;
        }

        public String toFileString() {
            return id + "," + name + "," + balance;
        }
    }
}
