package model.application;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.service.ContractService;
import model.service.PayPalService;

public class App {
    public static void main(String[] args){
        // default Settings
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Inputs
        System.out.println("Entre com os dados do contrato:");
        System.out.print("Número: ");
        Integer number = sc.nextInt();
        System.out.print("Data (dd:MM:yyyy): ");
        sc.nextLine(); // Limppa o Buffer
        LocalDate date = LocalDate.parse(sc.nextLine(), fmt);
        System.out.print("Valor do contrato: ");
        Double price = sc.nextDouble();
        System.out.print("Entre com o número de parcelas: ");
        Integer months = sc.nextInt();

        //Creating contract and service
        Contract contract = new Contract(number, date, price);
        ContractService service = new ContractService(new PayPalService());
        service.processContract(contract, months);

        //Output
        System.out.println();
        System.out.println("Parcelas:");
        for(Installment installment : contract.getInstallments()){
            System.out.println(installment);
        }

        sc.close();
    }
}
