import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        HashSet <Long> tree = new HashSet<>();
        while (in.hasNextLong()) {
            tree.add(in.nextLong());
        }
        Long sum = 0L;
        for (Long item: tree) {
            sum += item;
        }
        PrintWriter out = new PrintWriter("output.txt");
        out.println(sum);
        out.close();
    }
}