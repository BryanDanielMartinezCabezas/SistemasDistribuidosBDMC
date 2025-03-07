
public class Operaciones {
    private int n;

    public void setN(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public int fibonacci() {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int a = 0, b = 1, res = 0;
        for (int i = 2; i <= n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }

    public int factorial() {
        if (n < 0) throw new IllegalArgumentException("n debe ser >= 0");
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    public int sumatoria() {
        return (n * (n + 1)) / 2;
    }
}