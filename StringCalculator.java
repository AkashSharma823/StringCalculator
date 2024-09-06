import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DEFAULT_DELIMITERS = ",|\n";

    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        String delimiter = extractCustomDelimiter(numbers);
        String numbersToProcess = extractNumbers(numbers, delimiter);

        List<Integer> numList = parseNumbers(numbersToProcess, delimiter);
        checkForNegativeNumbers(numList);

        return numList.stream().mapToInt(Integer::intValue).sum();
    }

    private String extractCustomDelimiter(String numbers) {
        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf('\n');
            return numbers.substring(2, delimiterEndIndex);
        }
        return DEFAULT_DELIMITERS;
    }

    private String extractNumbers(String numbers, String delimiter) {
        if (delimiter.equals(DEFAULT_DELIMITERS)) {
            return numbers;
        }
        int delimiterEndIndex = numbers.indexOf('\n');
        return numbers.substring(delimiterEndIndex + 1);
    }

    private List<Integer> parseNumbers(String numbers, String delimiter) {
        String regex = "[" + delimiter + "]";
        String[] numberArray = numbers.split(regex);
        List<Integer> numList = new ArrayList<>();
        for (String number : numberArray) {
            if (!number.isEmpty()) {
                numList.add(Integer.parseInt(number));
            }
        }
        return numList;
    }

    private void checkForNegativeNumbers(List<Integer> numList) {
        List<Integer> negatives = new ArrayList<>();
        for (int num : numList) {
            if (num < 0) {
                negatives.add(num);
            }
        }
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negative numbers not allowed: " + String.join(", ", negatives.toString()));
        }
    }
}
