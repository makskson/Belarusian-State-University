import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        int n = in.nextInt();
        PrintWriter out = new PrintWriter("output.txt");
        if (n == 1) {
            out.println(in.nextInt());
        } else if (n < 3) {
            out.println(-1);
        } else {
            int[] kuvsh = new int[n];
            kuvsh[0] = in.nextInt();
            in.nextInt();
            kuvsh[1] = -1;
            kuvsh[2] = in.nextInt() + kuvsh[0];
            for (int i = 3; i < n; i++) {
                kuvsh[i] = in.nextInt() + Math.max(kuvsh[i - 2], kuvsh[i - 3]);
            }
            out.println(kuvsh[n - 1]);
        }
        out.close();
    }
}