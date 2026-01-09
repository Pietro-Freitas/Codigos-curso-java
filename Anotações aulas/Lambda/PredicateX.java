import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateX{ //Só para n dar conflito com o Predicate
    public static void main(String[] args)

    { 
        List<Product> list = new ArrayList<>();
        list.add(new Product("Tv", 900.00));
        list.add(new Product("Mouse", 50.00));
        list.add(new Product("Tablet", 350.50));
        list.add(new Product("HD Case", 80.90));

        list.removeIf(p -> p.getPrice() >= 100); // Lambda inline (Bom se a lógica for simples)

        list.removeIf(Product::isExpensive); // Method Reference (Mais cleanCode)

        // Váriavel de comparação para se precisar utilizar diversar vezes a mesma
        // comparação
        Predicate<Product> priceHigherThan100 = p -> p.getPrice() >= 100.0;
        list.removeIf(priceHigherThan100);// Depois você só usa a variável

        list.removeIf(new Predicate<Product>() { //Classe anômima (usada em sistemas legados)
            @Override
            public boolean test(Product p) {
                return p.getPrice() >= 100.0;
            }
        });

        for (var p : list)
            System.out.println(p);
    }
}

class Product {
    private String name;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public boolean isExpensive() { // Method reference
        return this.price >= 100.0;
    }

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", price=" + price + "]";
    }

}
