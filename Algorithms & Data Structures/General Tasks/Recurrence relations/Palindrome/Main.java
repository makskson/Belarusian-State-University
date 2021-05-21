import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(new File("input.txt"));
            PrintWriter out = new PrintWriter("output.txt");
            String word = in.next();
            StringBuilder palindrome = new StringBuilder();
            int size = word.length();
            int[][] a = new int[size][size];
            for (int i = 0; i < size; i++) {
                a[i][i] = 1;
            }
            for (int l = 1; l < size; ++l) {
                if (l == 1) {
                    for (int i = 0; i < size - l; ++i) {
                        int j = i + l;
                        if (word.charAt(i) == word.charAt(j)) {
                            a[i][j] = 2;
                        } else {
                            a[i][j] = 1;
                        }
                    }
                } else {
                    for (int i = 0; i < size - l; ++i) {
                        int j = i + l;
                        if (word.charAt(i) == word.charAt(j)) {
                            a[i][j] = a[i + 1][j - 1] + 2;
                        } else {
                            a[i][j] = Math.max(a[i + 1][j], a[i][j - 1]);
                        }
                    }
                }
            }
            out.println(a[0][size - 1]);
            int i = 0, j = size - 1;
            while (i < size && j > -1 && a[i][j] > 0) {
                if (word.charAt(i) == word.charAt(j)) {
                    palindrome.append(word.charAt(i));
                    ++i;
                    --j;
                } else {
                    if (a[i][j - 1] >= a[i + 1][j])
                        --j;
                    else
                        ++i;
                    }
                }
                out.print(palindrome);
                if (a[0][size - 1] % 2 == 1) {
                    palindrome.deleteCharAt(palindrome.length() - 1);
                }
                out.print(palindrome.reverse().toString());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}