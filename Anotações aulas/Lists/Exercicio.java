import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Exercicio {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        System.out.print("How many employees will be registered? ");
        int n = sc.nextInt();
        
        List<Employee> employees = new ArrayList<>();
        System.out.println();

        //inputs
        for(int i = 0; i < n; i++){
            System.out.println("Employee #" + (i+1));
            System.out.print("Id: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Salary: ");
            double salary = sc.nextDouble();
            Employee employee = new Employee(name, salary, id);
            employees.add(employee);
            System.out.println();
        }

        //Employee id for salary increase
        System.out.print("Enter the employee ID that will have a salary increase: ");
        int id = sc.nextInt();
        Double percentage = null;
        System.out.print("Enter the percentage: ");

        //Stream para verificar se há sallário
        Employee emp = employees.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (emp != null) {
            percentage = sc.nextDouble();
            double salary = emp.getSalary();
            salary = (percentage/100  + 1.0) * salary;
            emp.setSalary(salary); // Não deve existir algo que retorne o salário, mais seguro criar uma função increaseSAlary
            System.out.println();
        }else{
            System.out.println("This id does not exist!");
        }
        
        //output
        System.out.println("List of employees:");
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

        sc.close();
    }    
}

class Employee {
    private String name;
    private Double salary;
    private Integer id;

    public Employee(String name, Double salary, Integer id) {
        this.name = name;
        this.salary = salary;
        this.id = id;
    }

    public int getId(){
        return id;
    }
     public double getSalary(){
        return salary;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }

    public String toString(){
        return id + ", " + name + ", " + String.format("%.2f", salary);
    }
   
}