import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] mat = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                mat[i][j] = sc.nextInt();
            }
        }

        int negative = 0;

        System.out.println("Main diagonal:");
        for(int i = 0; i < n; i++){
            for(int j = 0; j< n; j++){
                if(mat[i][j] < 0) negative++;
                if(i == j){
                    System.out.print(mat[i][j] + " ");
                }
            }
        }
        System.out.println();
        System.out.println("Negative numbers: " + negative);

        sc.close();
    }
}
