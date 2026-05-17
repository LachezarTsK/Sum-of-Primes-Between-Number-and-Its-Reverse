
import kotlin.math.sqrt

class Solution {

    private companion object {
        const val SMALLEST_PRIME = 2
    }

    fun sumOfPrimesInRange(inputValue: Int): Int {
        val reversedInputValue = reverseDigits(inputValue)
        val minValue = min(inputValue, reversedInputValue)
        val maxValue = max(inputValue, reversedInputValue)
        val isPrime = createSieveOfEratosthenes(maxValue + 1)

        var sumPrimeNumbers = 0
        for (current in minValue..maxValue) {
            if (isPrime[current]) {
                sumPrimeNumbers += current
            }
        }
        return sumPrimeNumbers
    }

    private fun reverseDigits(value: Int): Int {
        var value = value
        var reversed = 0
        while (value > 0) {
            val digit = value % 10
            reversed = 10 * reversed + digit
            value /= 10
        }
        return reversed
    }

    private fun createSieveOfEratosthenes(maxValue: Int): BooleanArray {
        val isPrime = BooleanArray(maxValue) { true }
        for (value in 0..<SMALLEST_PRIME) {
            isPrime[value] = false
        }

        val upperLimit = sqrt(maxValue.toDouble()).toInt()
        for (value in SMALLEST_PRIME..upperLimit) {
            if (!isPrime[value]) {
                continue
            }
            var current = value * value
            while (current < maxValue) {
                isPrime[current] = false
                current += value
            }
        }
        return isPrime
    }
}
