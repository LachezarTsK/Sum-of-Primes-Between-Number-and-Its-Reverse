
import java.util.Arrays;

public class Solution {

    private static final int SMALLEST_PRIME = 2;

    public int sumOfPrimesInRange(int inputValue) {
        int reversedInputValue = reverseDigits(inputValue);
        int minValue = Math.min(inputValue, reversedInputValue);
        int maxValue = Math.max(inputValue, reversedInputValue);
        boolean[] isPrime = createSieveOfEratosthenes(maxValue + 1);

        int sumPrimeNumbers = 0;
        for (int current = minValue; current <= maxValue; ++current) {
            if (isPrime[current]) {
                sumPrimeNumbers += current;
            }
        }
        return sumPrimeNumbers;
    }

    private int reverseDigits(int value) {
        int reversed = 0;
        while (value > 0) {
            int digit = value % 10;
            reversed = 10 * reversed + digit;
            value /= 10;
        }
        return reversed;
    }

    private boolean[] createSieveOfEratosthenes(int maxValue) {
        boolean[] isPrime = new boolean[maxValue];
        Arrays.fill(isPrime, true);
        for (int value = 0; value < SMALLEST_PRIME; ++value) {
            isPrime[value] = false;
        }

        int upperLimit = (int) Math.sqrt(maxValue);
        for (int value = SMALLEST_PRIME; value <= upperLimit; ++value) {
            if (!isPrime[value]) {
                continue;
            }
            int current = value * value;
            while (current < maxValue) {
                isPrime[current] = false;
                current += value;
            }
        }
        return isPrime;
    }
}
