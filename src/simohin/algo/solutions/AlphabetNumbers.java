package simohin.algo.solutions;

import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

/**
 * Encrypt message by switching letters on their position in alphabet. Spaces are ignored, there is punctuation in input, case insensitive
 * Input: string for encryption with length bellow 10000, latin. Example: MR robot
 * Output: positions in alphabet, separated by comma. Example: 13,18,18,15,2,15,20
 */
public class AlphabetNumbers {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final IntPredicate FILTER_INT_PREDICATE = x -> x <= 'z' && x >= 'a';
    private static final int BOUND = '`';
    private static final String DELIMITER = ",";

    public static void main(String[] args) {
        var input = SCANNER.nextLine().trim().toLowerCase();

        var result = input.chars()
                .filter(it -> ((char) it) != ' ')
                .filter(FILTER_INT_PREDICATE)
                .map(it -> it - BOUND)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(DELIMITER));

        System.out.println(result);
    }
}
