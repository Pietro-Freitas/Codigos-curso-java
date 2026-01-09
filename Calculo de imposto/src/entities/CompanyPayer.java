package entities;

public class CompanyPayer extends Payer{
    private Integer numberOfEmployees;

    public CompanyPayer(String name, Double anualIncome, Integer numberOfEmployees) {
        super(name, anualIncome);
        this.numberOfEmployees = numberOfEmployees;
    }

    @Override
    public Double tax() {
        Double tax = 0.16;
        if(numberOfEmployees > 10) tax = 0.14;
        tax *= getanualIncome();
        if(tax < 0.0) return 0.0;
        return tax;
    }
}
