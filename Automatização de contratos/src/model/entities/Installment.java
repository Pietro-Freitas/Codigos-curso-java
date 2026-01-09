package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Installment {
    private LocalDate dueDate;
    private Double amount;

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Installment(LocalDate dueDate, Double amount) {
        this.dueDate = dueDate;
        this.amount = amount;
    }

    // Getters
    public LocalDate getDueDate() {
        return dueDate;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(dueDate.format(fmt));
        sb.append(" - ");
        sb.append(String.format("%.2f", amount));
        return sb.toString(); 
    }
}
