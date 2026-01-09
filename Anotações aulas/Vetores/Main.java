import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        System.out.println("DIgite um n: ");
        System.out.flush();
        int n = sc.nextInt();
        double[] vect = new double[n];
        double sum = 0.0, average = 0.0;

        for (int i = 0; i < n; i++){
            vect[i] = sc.nextDouble();
            sum += vect[i];
        }
        
        average = sum / n;
        System.out.printf("Average: %.2f%n", average);

        sc.close();
    }
}
