import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int askCount = in.nextInt();
        for (int i = 0; i < askCount; i++) {
            int x = in.nextInt();
            System.out.print(binary_search(a, x) + " " + lower_bound(a, x) + " " + upper_bound(a, x) + "\n");
        }
    }

    static int binary_search(int a[], int x) {
        int l = 0, r = a.length;
        do {
            int k = (r + l) / 2;
            if (x == a[k])
                return 1;
            else if (x < a[k])
                r = k;
            else
                l = k + 1;
        } while (l < r);
        return 0;
    }
    static int lower_bound(int a[], int x) {
        int l = 0, r = a.length;
        do {
            int k = (r + l) / 2;
            if (x <= a[k])
                r = k;
            else
                l = k + 1;
        } while (l < r);
        return l;
    }
    static int upper_bound(int a[], int x) {
        int l = 0, r = a.length;
        do {
            int k = (r + l) / 2;
            if (x < a[k])
                r = k;
            else
                l = k + 1;
        } while (l < r);
        return l;
    }
}