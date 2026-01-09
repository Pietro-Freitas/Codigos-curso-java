import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        list.add(new Product("Tv", 900.00));
        list.add(new Product("Mouse", 50.00));
        list.add(new Product("Tablet", 350.50));
        list.add(new Product("HD Case", 80.90));

        // Cenário: Aumentar o preço de todos os produtos em 10%

        // 1. Lambda Inline (Lógica direta no argumento)
        list.forEach(p -> p.setPrice(p.getPrice() * 1.1)); 

        // 2. Method Reference (Referenciando um método estático ou de instância)
        list.forEach(Product::staticUpdatePrice); 

        // 3. Variável de comparação (Declarando explicitamente)
        Consumer<Product> increasePrice = p -> p.setPrice(p.getPrice() * 1.1);
        list.forEach(increasePrice);

        // 4. Classe Anônima (Legado)
        list.forEach(new Consumer<Product>() {
            @Override
            public void accept(Product p) {
                p.setPrice(p.getPrice() * 1.1);
            }
        });

        // Apenas para imprimir e conferir (System.out::println também é um Consumer!)
        list.forEach(System.out::println);
    }
}

class Product {
    private String name;
    private Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    
    public Double getPrice() { return price; }
    
    // Necessário para o exemplo do Consumer
    public void setPrice(Double price) { this.price = price; }

    // Método auxiliar para o exemplo de Consumer com Method Reference
    public static void staticUpdatePrice(Product p) {
        p.setPrice(p.getPrice() * 1.1);
    }

    public boolean isExpensive() { 
        return this.price >= 100.0;
    }
    @Override
    public String toString() {
        return "Product [name=" + name + ", price=" + String.format("%.2f", price) + "]";
    }
}