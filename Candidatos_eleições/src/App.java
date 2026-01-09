import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
public class App {
    public static void main(String[] args) throws Exception {
        String path = "votos.csv";

        Map<String, Integer> candidates = new LinkedHashMap<>();

        try {
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String line = bf.readLine();
            while (line != null) {
                String[] datas = line.split(",");
                if (candidates.containsKey(datas[0])) {
                    Integer Votes = candidates.get(datas[0]);
                    candidates.put(datas[0], Votes + Integer.parseInt(datas[1]));
                } else {
                    candidates.put(datas[0], Integer.parseInt(datas[1]));
                }
                line = bf.readLine();
            }
            bf.close();
            for (String key : candidates.keySet()) {
                System.out.println(key + ": " + candidates.get(key));
            }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
