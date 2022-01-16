package simohin.algo.solutions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

/**
 * count a harmonic average for a subarray
 *
 * input:
 * first row - number of elements in array - n
 * second row - array of n float numbers divided with a space - a
 * third rows - number of invocations of calculate method - q
 * next q rows - invocation parameters l and r divided with a space - l,r
 */
class HarmonicAverage {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ON_LINE_PARAMS_SEPARATOR = " ";

    public static void main(String[] args) {
        int n = Integer.parseInt(SCANNER.nextLine());
        var a = new BigDecimal[n];
        var numbers = SCANNER.nextLine().split(ON_LINE_PARAMS_SEPARATOR);
        for (int i = 0; i < numbers.length; i++) {
            a[i] = new BigDecimal(numbers[i]);
        }
        var q = Integer.parseInt(SCANNER.nextLine());

        for (int i = 0; i < q; i++) {
            var params = SCANNER.nextLine().split(ON_LINE_PARAMS_SEPARATOR);
            System.out.println(calculate(Integer.parseInt(params[0]), Integer.parseInt(params[1]), a));
        }
    }

    private static double calculate(int l, int r, BigDecimal[] a) {

        var top = BigDecimal.valueOf(r - l + 1);
        var bottom = BigDecimal.valueOf(Arrays.stream(Arrays.copyOfRange(a, l, r + 1))
                .mapToDouble(it -> BigDecimal.ONE.divide(it, 7, RoundingMode.HALF_UP).doubleValue())
                .sum());
        return top.divide(bottom, 6, RoundingMode.HALF_UP).doubleValue();
    }
}