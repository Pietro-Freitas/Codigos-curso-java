import java.util.Scanner;
import java.util.Locale;

public class VetorClasse {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double sum = 0;
        sc.nextLine();

        Product[] vect = new Product[n];
        for(int i =0; i < vect.length; i++){
            String name = sc.nextLine(); Double price = sc.nextDouble();
            vect[i] = new Product(name, price);
            sc.nextLine();
        }

        for(int i = 0; i < vect.length; i++){
            sum += vect[i].getPrice();
        }

        double average = (double) sum / n;
        System.out.printf("AVERAGE PRICE: %.2f", average);

        sc.close();
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public double getPrice(){
        return price;
    }
}