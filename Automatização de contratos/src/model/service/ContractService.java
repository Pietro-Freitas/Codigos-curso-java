package model.service;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

    private OnlinePaymentService service;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.service = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months){
        Double valuePerMonth = (Double) contract.getTotalValue() / months;
        LocalDate dateNow = contract.getDate();
        for(int i = 1; i <= months; i++){
            Double interest = service.interest(valuePerMonth, i);
            Double fee = service.paymentFee(valuePerMonth + interest);
            Double result = interest + fee + valuePerMonth;
            LocalDate date = dateNow.plusMonths(i);

            Installment installment = new Installment(date, result);
            contract.addInstallment(installment);
        }
    }
}
