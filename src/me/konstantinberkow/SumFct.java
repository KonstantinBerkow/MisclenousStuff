package me.konstantinberkow;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by konstantinberkov on 12/17/15.
 */
public class SumFct {

    private static final List<BigInteger> sCachedResults = new ArrayList<>();

    static {
        sCachedResults.add(BigInteger.ONE);
        sCachedResults.add(BigInteger.ONE);
    }

    public static BigInteger perimeter(final BigInteger n) {
        // your code
        return fibonacchiSum(n).multiply(BigInteger.valueOf(4));
    }

    private static BigInteger fibonacchiSum(BigInteger n) {
        final int intValue = n.intValue();
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i <= intValue; i ++) {
            result = result.add(fibonacchiNumber(i));
        }
        return result;
    }

    private static BigInteger fibonacchiNumber(final int index) {
        final int size = sCachedResults.size();
        if (index < size) {
            return sCachedResults.get(index);
        }

        final BigInteger result = fibonacchiNumber(index - 2).add(fibonacchiNumber(index - 1));
        sCachedResults.add(result);
        return result;
    }
}
