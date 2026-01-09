import java.util.Locale;
import java.util.Scanner;

public class Soma_vetor {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        System.out.print("How many digits will you tipe? ");
        int n = sc.nextInt();
        double sum=0, average;

        double[] vect = new double[n];
        for(int i = 0; i< n; i++){
            System.out.print("Type a number: ");
            vect[i] = sc.nextDouble();
            sum += vect[i];
        }
        average = sum / n;
        
        System.out.print("Values = ");
        for(int i = 0; i < vect.length; i++){
            System.out.print(vect[i] + " ");
        }
        System.out.printf("%nSum = %.2f%n", sum);
        System.out.printf("Average = %.2f", average);

        sc.close();
    }
}
