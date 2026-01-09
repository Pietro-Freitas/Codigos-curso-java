import java.util.*;

public class Negativos{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many digits will you type? ");
        int n = scanner.nextInt();
        int[] vect = new int[n];

        for(int i = 0; i < vect.length; i++){
            System.out.print("type a number: ");
            vect[i] = scanner.nextInt();
        }

        System.out.println("NEGATIVE NUMBERS: ");
        for(int i = 0; i < vect.length; i++){
            if (vect[i] < 0) System.out.println(vect[i]);
        }


        scanner.close();
    }   
}