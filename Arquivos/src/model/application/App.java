package model.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("De o caminho do arquivo: ");
        // String path = sc.nextLine();
        String path = "src/model/application/arquivo.csv";

        File file = new File(path);
        
        try(BufferedReader bf = new BufferedReader(new FileReader(file))){
            String line = bf.readLine();
            while(line != null){
                String[] datas = line.split(",");
                try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
                    bw.write(datas[0]);
                    bw.newLine();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch(IOException e){
            System.out.println("Error: " + e);
        }


        sc.close();
    }
}
