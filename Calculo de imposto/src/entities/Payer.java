package entities;

public abstract class Payer {
    private String name;
    private Double anualIncome;

    // Getters
    public String getName(){
        return name;
    }
    public Double getanualIncome(){
        return anualIncome;
    }


    public Payer(String name, Double anualIncome) {
        this.name = name;
        this.anualIncome = anualIncome;
    }


    public abstract Double tax();

    @Override
    public String toString(){
        return name + ": $ "+ String.format("%.2f", tax());
    }
}
