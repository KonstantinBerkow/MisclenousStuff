package me.konstantinberkow;

import java.util.Arrays;

/**
 * Created by konstantinberkov on 12/27/15.
 */
public class SchenksInd {

    /**
     * Считает дискретный логарифм методом Шенкса, параметры по Белозерову
     * g ^ a = x (mod p)
     * @param x - ???
     * @param g - первообразный корень илви что?
     * @param p - нечетное прсотое
     * @return a - искомая хуйня
     */
    public static long ind(final long x, final long g, final long p) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("calculate ind(%d, %d, %d)%n", x, g, p);
        final int m = (int) (Math.sqrt(p) + 1); //"большие" шаги
        System.out.println("Big steps count: " + m);

        final long step = power(g, m, p);
        System.out.println("Step value: " + step);

        final long[] bigSteps = new long[m];
        bigSteps[0] = step;
        for (int i = 1; i < m; i++) {
            bigSteps[i] = (bigSteps[i - 1] * step) % p;
        }
        System.out.println("Big steps: " + Arrays.toString(bigSteps));

        //small steps
        long currentSmallStep = x; //first step
        for (int j = 0; j <= m; j++) {
            System.out.println("Check small step: " + currentSmallStep);
            final int i = arrayIndexOf(bigSteps, currentSmallStep);
            if (i >= 0) {
                System.out.printf("i = %d%n", i + 1);
                System.out.printf("j = %d%n", j);
                final long result = m * (i + 1) - j;
                System.out.printf("ind(%d, %d, %d) = %d%n", x, g, p, result);
                return result;
            }
            currentSmallStep = (currentSmallStep * g) % p;
        }

        return -1;
    }

    public static long power(final long value, final long power, final long mod) {
        long result = 1;
        for (int i = 0; i < power; i++) {
            result = (result * value) % mod;
        }
        return result;
    }

    public static int arrayIndexOf(final long[] array, final long value) {
        final int length = array.length;
        for (int i = 0; i < length; i++) {
            if (array[i] == value) return i;
        }
        return -1;
    }
}
