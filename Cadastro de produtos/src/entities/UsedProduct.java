package entities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class UsedProduct extends Product{
    private LocalDate manufacturedTime;
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.systemDefault());

    public UsedProduct(String name, Double price, String manufacturedTime) {
        super(name, price);
        this.manufacturedTime = LocalDate.parse(manufacturedTime, fmt);
    }

    @Override
    public String priceTag(){
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append(" $ ");
        sb.append(String.format("%.2f", getPrice()));
        sb.append(" (Manufacture date: ");
        sb.append(manufacturedTime.format(fmt));
        sb.append(")");
        return sb.toString();
    }
}
