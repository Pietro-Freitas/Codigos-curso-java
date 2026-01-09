package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.IndividualPayer;
import entities.CompanyPayer;
import entities.Payer;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Payer> payers = new ArrayList<>();

        //Inputs
        System.out.print("Enter the number of the tax payers: ");
        int n = sc.nextInt();

        for(int i = 1; i <= n; i++){
            System.out.println("Tax payer #" + i + " data:");
            System.out.print("Individual or company (i/c)? ");
            char c = sc.next().charAt(0);
            System.out.print("Name: ");
            sc.nextLine(); // Limpa o buffer
            String name = sc.nextLine();
            System.out.print("Anual imcome: ");
            Double income = sc.nextDouble();
            if(c == 'i'){
                System.out.print("Health expenditures: ");
                Double he  = sc.nextDouble();
                Payer payer = new IndividualPayer(name, income, he);
                payers.add(payer);
            }else{
                System.out.println("Number of employees: ");
                Integer ne = sc.nextInt();
                Payer payer = new CompanyPayer(name, income, ne);
                payers.add(payer);
            }
        }

        // output
        Double totalTaxes = 0.0;
        System.out.println("\nTAXES PAID:");
        for(Payer payer : payers){
            System.out.println(payer.toString());
            totalTaxes += payer.tax();
        }
        System.out.println();
        System.out.println("TOTAL TAXES: $" + String.format("%.2f", totalTaxes));


        sc.close();
    }
}