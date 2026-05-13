import java.io.*;
import java.util.*;

class Account implements Serializable {
    int accNo;
    String name;
    double balance;

    Account(int accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
    }
}

public class Main {

    static ArrayList<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        loadData();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. View All");
            System.out.println("6. Exit");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Acc No: ");
                    int acc = sc.nextInt();
                    System.out.print("Name: ");
                    String name = sc.next();

                    accounts.add(new Account(acc, name, 0));
                    saveData();
                    break;

                case 2:
                    System.out.print("Acc No: ");
                    int acc1 = sc.nextInt();
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();

                    for (Account a : accounts) {
                        if (a.accNo == acc1) {
                            a.balance += amt;
                        }
                    }
                    saveData();
                    break;

                case 3:
                    System.out.print("Acc No: ");
                    int acc2 = sc.nextInt();
                    System.out.print("Amount: ");
                    double amt2 = sc.nextDouble();

                    for (Account a : accounts) {
                        if (a.accNo == acc2 && a.balance >= amt2) {
                            a.balance -= amt2;
                        }
                    }
                    saveData();
                    break;

                case 4:
                    System.out.print("Acc No: ");
                    int acc3 = sc.nextInt();

                    for (Account a : accounts) {
                        if (a.accNo == acc3) {
                            System.out.println("Balance: " + a.balance);
                        }
                    }
                    break;

                case 5:
                    for (Account a : accounts) {
                        System.out.println(a.accNo + " " + a.name + " " + a.balance);
                    }
                    break;

                case 6:
                    System.exit(0);
            }
        }
    }

    static void saveData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.txt"));
            oos.writeObject(accounts);
            oos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void loadData() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.txt"));
            accounts = (ArrayList<Account>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            accounts = new ArrayList<>();
        }
    }
}
