package Application;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter full file path: ");
        String path = sc.next();

        try(BufferedReader bf = new BufferedReader(new FileReader(path))){
            List<Employee> list = new ArrayList<>();
            String line = bf.readLine();
            while(line != null){
                String[] fields = line.split(",");
                Employee employee = new Employee(fields[0], fields[1], Double.parseDouble(fields[2]));
                list.add(employee);
                line = bf.readLine();
            }

            System.out.print("Enter salary: ");
            Double valor = sc.nextDouble();

            List<String> names = list.stream().filter(e -> e.getSalary() > valor).map(e -> e.getEmail()).sorted().collect(Collectors.toList());

            names.forEach(System.out::println);

            Double sum = list.stream().filter(e -> e.getName().charAt(0) == 'M').map(e -> e.getSalary()).reduce(0.0, (x, y) -> x + y);
            
            System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));

        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
