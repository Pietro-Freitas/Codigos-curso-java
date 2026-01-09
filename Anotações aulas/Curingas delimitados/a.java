import java.util.LinkedHashSet;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

public class a {
    public static void main(String[] args) {
        Set<Integer> myset = new HashSet<>();
        myset.add(2);
        myset.add(12);
        myset.add(8);
        myset.add(4);
        myset.add(12);
        myset.add(43);
        myset.add(13);
        myset.add(3);
        for (var num : myset)
            System.out.println(num);
    }
}
