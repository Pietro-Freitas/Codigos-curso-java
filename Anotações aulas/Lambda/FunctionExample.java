import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionExample {
    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        list.add(new Product("Tv", 900.00));
        list.add(new Product("Mouse", 50.00));
        list.add(new Product("Tablet", 350.50));
        list.add(new Product("HD Case", 80.90));

        // Cenário: Criar uma NOVA lista contendo apenas os nomes dos produtos em CAIXA ALTA.

        // 1. Lambda Inline
        List<String> names1 = list.stream().map(p -> p.getName().toUpperCase()).collect(Collectors.toList());

        // 2. Method Reference ( mais cleanCode)
        List<String> names2 = list.stream().map(Product::nonStaticUpperCaseName).collect(Collectors.toList());

        // 3. Variável declarada
        Function<Product, String> func = p -> p.getName().toUpperCase();
        List<String> names3 = list.stream().map(func).collect(Collectors.toList());

        // 4. Classe Anônima (Legado)
        List<String> names4 = list.stream().map(new Function<Product, String>() {
            @Override
            public String apply(Product p) {
                return p.getName().toUpperCase();
            }
        }).collect(Collectors.toList());
            
        names1.forEach(System.out::println);
        names2.clear();
        names3.clear(); // Só para o compilador n reclamar que n foi utilizada
        names4.clear();
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

    // Método para o exemplo de Function com Method Reference
    public String nonStaticUpperCaseName() {
        return this.name.toUpperCase();
    }

    public boolean isExpensive() { 
        return this.price >= 100.0;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", price=" + String.format("%.2f", price) + "]";
    }
}