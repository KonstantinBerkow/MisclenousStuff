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
     * @param g - первообразный корень или что?
     * @param p - нечетное прсотое
     * @return a - искомая хуйня
     */
    public static int ind(final int x, final int g, final int p) {
        final int m = (int) (Math.sqrt(p) + 1); //"большие" шаги
        System.out.println("Big steps count: " + m);

        final int step = power(g, m, p);
        System.out.println("Step value: " + step);

        final int[] bigSteps = new int[m];
        bigSteps[0] = step;
        for (int i = 1; i < m; i++) {
            bigSteps[i] = (bigSteps[i - 1] * step) % p;
        }
        System.out.println("Big steps: " + Arrays.toString(bigSteps));

        //small steps
        int currentSmallStep = x; //first step
        for (int j = 0; j <= m; j++) {
            final int i = arrayIndexOf(bigSteps, currentSmallStep) + 1;
            if (i >= 0) {
                System.out.printf("i = %d%n", i);
                System.out.printf("j = %d%n", j);
                final int result = m * i - j;
                System.out.printf("ind(%d, %d, %d) = %d%n", x, g, p, result);
                return result;
            }
            currentSmallStep = (currentSmallStep * g) % p;
        }

        return -1;
    }

    public static int power(final int value, final int power, final int mod) {
        int result = 1;
        for (int i = 0; i < power; i++) {
            result = (result * value) % mod;
        }
        return result;
    }

    public static int arrayIndexOf(final int[] array, final int value) {
        final int length = array.length;
        for (int i = 0; i < length; i++) {
            if (array[i] == value) return i;
        }
        return -1;
    }
}
