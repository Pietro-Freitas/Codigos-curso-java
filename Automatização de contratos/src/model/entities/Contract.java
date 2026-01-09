package model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contract {
    private Double totalValue;
    private LocalDate date;
    private Integer number;

    private List<Installment> installments = new ArrayList<>();
    
    public Contract(int number, LocalDate date, double totalValue){
        this.totalValue = totalValue;
        this.number = number;
        this.date = date;
    }

    // Getters
    public Double getTotalValue() {
        return totalValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getNumber() {
        return number;
    }
    
    public List<Installment> getInstallments() {
        return installments;
    }

    public void addInstallment(Installment installment){
        installments.add(installment);
    }

}