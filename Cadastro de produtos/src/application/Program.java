package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ImportProduct;
import entities.Product;
import entities.UsedProduct;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Product> products = new ArrayList<>();

        // Input
        System.out.print("Enter the number of products: ");
        int n = sc.nextInt();
        for(int i = 1; i <= n; i++){
            System.out.println("Product #" + i + " data:");
            System.out.print("Common, used or imported (c/u/i)? ");
            char c = sc.next().charAt(0);
            System.out.print("Name: ");
            sc.nextLine(); // Limpa o buffer
            String name = sc.nextLine();
            System.out.print("Price: ");
            Double price = sc.nextDouble();
            // Used product
            if(c == 'u'){
                System.out.print("Manufacture date (dd/MM/yyyy): ");
                String date = sc.next();
                Product product = new UsedProduct(name, price, date);
                products.add(product);
            }else if (c == 'i'){ // Import product
                System.out.print("Customs fee: ");
                Double cf = sc.nextDouble();
                Product product = new ImportProduct(name, price, cf);
                products.add(product);
            }else{ // Normal product
                Product product = new Product(name, price);
                products.add(product);
            }
        }

        // Output
        System.out.println(); // pula uma linha
        System.out.println("PRICE TAGS:");
        for(Product product : products){
            System.out.println(product.priceTag());
        }

        sc.close();
    }
}
