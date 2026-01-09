package entities;

public final class ImportProduct extends Product{
    private Double customsFee;

    public ImportProduct(String name, Double price, Double customsFee) {
        super(name, price);
        this.customsFee = customsFee;
    }

    public Double totalPrice(){
        Double totalPrice = getPrice() + customsFee;
        return totalPrice;
    }

    @Override
    public String priceTag(){
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append(" $ ");
        sb.append(String.format("%.2f", totalPrice()));
        sb.append(" (Customs fee: " );
        sb.append(String.format("%.2f", customsFee));
        sb.append(")");
        return sb.toString();
    }
}
