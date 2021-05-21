import java.util.Scanner;

public class Edin1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        int [][]a = new int[n + 1][n + 1];
        a[0][0] = 1;
        for (int i = 1; i < n + 1; i++) {
            a[i][0] = 1;
            a[i][i] = 1;
            for (int j = 1; j < i; j++) {
                a[i][j] = (int) ((a[i - 1][j] + a[i - 1][j - 1])%(Math.pow(10, 9) + 7));
            }
        }
        System.out.println((int) (a[n][k]%(Math.pow(10, 9) + 7)));
    }
}