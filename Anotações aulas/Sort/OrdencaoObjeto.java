import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class OrdencaoObjeto{
    public static void main(String[] args){
        // Default settings
        Locale.setDefault(Locale.US);
        String path = "in.csv";

        List<Employee> list = new ArrayList<>();

        try(BufferedReader bf = new BufferedReader(new FileReader(path))){
            String employee = bf.readLine();

            while(employee != null){
                String[] fields = employee.split(",");
                list.add(new Employee(fields[0], Double.parseDouble(fields[1])));
                employee = bf.readLine();
            }

            Collections.sort(list);
            for(Employee name : list){
                System.out.println(name);
            }
        }catch(IOException e){
            System.out.println("Houve um erro de: " + e.getMessage());
        }catch(RuntimeException e){
            System.out.println("Erro inesperado - " + e.getMessage());
        }
    }
}

class Employee implements Comparable<Employee>{
    private String name;
    private Double salary;
    public Employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    @Override
    public int compareTo(Employee other) {
        return -this.salary.compareTo(other.salary); //! Ordena por salario, Só trocar salary para name caso queira por nome (Se trocar o menos por mais vira ordem crescente)
    }

    @Override
    public String toString(){
        return name + ", " + salary;
    }
}