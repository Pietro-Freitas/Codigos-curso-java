package model.application;

import java.util.Locale;
import java.util.Scanner;

import model.Exceptions.drawLimitExceeded;
import model.Exceptions.noBalance;
import model.entities.Account;

public class Program {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        try{
            // input
            System.out.println("Enter account data:");
            System.out.print("Number: ");
            Integer number = sc.nextInt();
            System.out.print("Holder: ");
            sc.nextLine(); // Limpa o buffer
            String holder = sc.nextLine();
            System.out.print("Initial balance: ");
            Double balance = sc.nextDouble();
            System.out.print("Withdraw limit: ");
            Double limit = sc.nextDouble();

            System.out.println();
            System.out.print("Enter Amount for withdraw: ");
            Double withdraw = sc.nextDouble();

            //Processing
            Account account = new Account(number, holder, balance, limit);
            account.withdraw(withdraw);
            System.out.println(account.toString());
        }catch(drawLimitExceeded e){
            System.out.println("Withdraw error: " + e.getMessage());
        }catch(noBalance e){
            System.out.println("Withdraw error: " + e.getMessage());

        }catch(RuntimeException e){
            System.out.println("Unexpected error");
        }

        sc.close();
    }
}
