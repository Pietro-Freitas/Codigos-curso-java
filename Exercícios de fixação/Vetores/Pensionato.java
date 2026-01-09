import java.util.Locale;
import java.util.Scanner;

public class Pensionato {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        Pessoa[] rooms = new Pessoa[10];
        System.out.print("How many rooms will be rented? ");
        int n =sc.nextInt();
        for(int i = 0; i < n; i++){
            System.out.println("Rent #" + (i+1) + ":");
            System.out.print("Name: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Room: ");
            int room = sc.nextInt();  
            rooms[room] = new Pessoa(name, email);
            System.out.println();
        }
        
        System.out.println("Busy rooms:");
        for(int i = 0; i < rooms.length; i++){
            if(rooms[i] != null){
                System.out.println(i + ": " + rooms[i].getName() + ", " + rooms[i].getEmail());
            }
        }

        sc.close();
    } 
}

class Pessoa {
    private String name;
    private String email;

    Pessoa(String name, String email){
        this.name = name;
        this.email = email;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }

}