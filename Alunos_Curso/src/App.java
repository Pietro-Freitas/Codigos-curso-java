import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
        //Default Settings
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);


        Set<Integer> students = new HashSet<>();

        //Input
        System.out.print("How manny students for course A? ");
        Integer studentsA = sc.nextInt();
        for(int i = 0; i < studentsA; i++){
            Integer studentCode = sc.nextInt();
            students.add(studentCode);
        }
        System.out.print("How manny students for course B? ");
        Integer studentsB = sc.nextInt();
        for(int i = 0; i < studentsB; i++){
            Integer studentCode = sc.nextInt();
            students.add(studentCode);
        }

        System.out.print("How manny students for course C? ");
        Integer studentsC = sc.nextInt();
        for(int i = 0; i < studentsC; i++){
            Integer studentCode = sc.nextInt();
            students.add(studentCode);
        }

        Integer totalStudent = students.size();
        System.out.println("Total students: " + totalStudent);

        sc.close();
    }
}
