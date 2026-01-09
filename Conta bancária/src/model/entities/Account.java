package model.entities;

import model.Exceptions.drawLimitExceeded;
import model.Exceptions.noBalance;

public class Account {
    private Integer number;
    private String holder;
    private Double balance;
    private Double withdrawLimit;

    public Account(Integer number, String holder, Double balance, Double withdrawLimit) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
    }

    public void deposit(Double amount){
        balance += amount;
    }

    public void withdraw(Double amount) throws drawLimitExceeded, noBalance{
        if(amount > withdrawLimit){
            throw new drawLimitExceeded("The amount exceeds withdraw limit");
        }else if (balance < amount){
            throw new noBalance("Not enough balance");
        }else{
        balance -= amount;
        }
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("New balance: ");
        sb.append(String.format("%.2f", balance));

        return sb.toString();
    }
}