
#include <cmath>
#include <vector>
#include <algorithm>
using namespace std;

class Solution {

    static const int SMALLEST_PRIME = 2;

public:
    int sumOfPrimesInRange(int inputValue) {
        int reversedInputValue = reverseDigits(inputValue);
        int minValue = min(inputValue, reversedInputValue);
        int maxValue = max(inputValue, reversedInputValue);
        vector<bool> isPrime = createSieveOfEratosthenes(maxValue + 1);

        int sumPrimeNumbers = 0;
        for (int current = minValue; current <= maxValue; ++current) {
            if (isPrime[current]) {
                sumPrimeNumbers += current;
            }
        }
        return sumPrimeNumbers;
    }

private:
    int reverseDigits(int value) {
        int reversed = 0;
        while (value > 0) {
            int digit = value % 10;
            reversed = 10 * reversed + digit;
            value /= 10;
        }
        return reversed;
    }

    vector<bool> createSieveOfEratosthenes(int maxValue) {
        vector<bool> isPrime(maxValue, true);
        for (int value = 0; value < SMALLEST_PRIME; ++value) {
            isPrime[value] = false;
        }

        int upperLimit = sqrt(maxValue);
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
};
