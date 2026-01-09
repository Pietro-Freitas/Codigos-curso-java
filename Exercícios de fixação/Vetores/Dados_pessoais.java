import java.util.Locale;
import java.util.Scanner;

public class Dados_pessoais {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        System.out.print("How many people will be typed? ");
        int n = sc.nextInt();
        
        Pessoa[] vect = new Pessoa[n];

        for(int i = 0; i < vect.length; i++){
            System.out.print("Heigth of the " + (i+1) + "a person: ");
            double heigth = sc.nextDouble();
            System.out.print("Gender of the " + (i+1) + "a person: ");
            char gender = sc.next().charAt(0);
            vect[i] = new Pessoa(heigth, gender);
        }

        double min = 0, max = 0, avgF = 0, cF = 0;
        int nH = 0;
        for(int i = 0; i < vect.length; i++){
            if(i == 0){
                min = max = vect[i].getHeight();
            }else{ 
                if(min > vect[i].getHeight()) min = vect[i].getHeight();
                if(max < vect[i].getHeight()) max = vect[i].getHeight();
            }
            if(vect[i].getGender() == 'F'){
                avgF += vect[i].getHeight();
                cF++;
            }else nH++;
        }
        avgF /= cF;

        System.out.printf("Shortest height: %.2f%n", min);
        System.out.printf("Greater height: %.2f%n", max);

        System.out.printf("Average height of womans: %.2f%n", avgF);

        System.out.println("Mens number: " + nH);

        sc.close();
    }
}

class Pessoa {
    private char gender;
    private double height;
    
    Pessoa(double height,char gender){
        this.height = height;
        this.gender = gender;
    }

    public double getHeight(){
        return height;
    }
    public char getGender(){
        return gender;
    }
    
}
