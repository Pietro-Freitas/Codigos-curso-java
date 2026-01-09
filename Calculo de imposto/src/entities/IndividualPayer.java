package entities;

public class IndividualPayer extends Payer{
    private Double healthExpenditures;


    public IndividualPayer(String name, Double anualIncome, Double healthExpenditures) {
        super(name, anualIncome);
        this.healthExpenditures = healthExpenditures;
    }


    @Override
    public Double tax(){
        if(getanualIncome() < 20000.00){
            Double tax = getanualIncome()* 0.15;
            if(healthExpenditures > 0){
                tax -= (healthExpenditures*0.50);
                return tax;
            }
        }
        Double tax = getanualIncome()* 0.25;
        if(healthExpenditures > 0){
            tax -= (healthExpenditures*0.50);
        }
        if(tax < 0.0) return 0.0;
        return tax;
    }
}