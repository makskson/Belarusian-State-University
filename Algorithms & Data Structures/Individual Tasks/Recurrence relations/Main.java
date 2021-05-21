import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        long[][] a = new long[n][m]; // матрица
        long[][] columnSum = new long[n + 1][m]; // матрица сумм стоблцов
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                a[i][j] = in.nextLong();
                columnSum[i + 1][j] = columnSum[i][j] + a[i][j];
            }
        }

        long maxSum = 0L;
        for (int rowStart = 0; rowStart < n; ++rowStart) {
            for (int rowEnd = rowStart; rowEnd < n; ++rowEnd) {
                long currentSum = 0L;
                for (int col = 0; col < m; ++col) {
                    currentSum += columnSum[rowEnd + 1][col] - columnSum[rowStart][col];
                    if (currentSum < 0) {
                        currentSum = 0;
                    } else if (maxSum < currentSum) {
                        maxSum = currentSum;
                    }
                }
            }
        }
        System.out.println(maxSum);
    }
}