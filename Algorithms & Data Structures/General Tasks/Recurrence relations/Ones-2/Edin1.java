import java.util.Scanner;

public class Edin1 {
    private static final long p = 1000000007;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(C(in.nextLong(), in.nextLong()));
    }

    static long pow(long n, long k) {
        long res = 1l, lim = k + 2;
        while (k != 0) {
            if (k % 2 == 1) {
                res = (res * (n % lim)) % lim;
                --k;
            } else {
                n = ((n % lim) * (n % lim)) % lim;
                k /= 2;
            }
        }
        return res;
    }

    static long znamenatel (long n) {
        long res = 1l;
        for (int i = 1; i <= n; ++i) {
            res = (res * (i % p)) % p;
        }
        return pow(res, p - 2) % p;
    }

    static long C (long n, long k) {
        long zn = 1l;
        long res = 1l;

        if (k > n - k) {
            for (long i = k + 1; i <= n; ++i)
                res = (res * (i % p)) % p;
            zn = znamenatel(n - k);
        } else {
            for (long i = n - k + 1; i <= n; ++i)
                res = (res * (i % p)) % p;
            zn = znamenatel(k);
        }
        return (res * (zn % p)) % p;
    }
}