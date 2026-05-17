
package main
import "math"

const SMALLEST_PRIME = 2

func sumOfPrimesInRange(inputValue int) int {
    reversedInputValue := reverseDigits(inputValue)
    minValue := min(inputValue, reversedInputValue)
    maxValue := max(inputValue, reversedInputValue)
    isPrime := createSieveOfEratosthenes(maxValue + 1)

    sumPrimeNumbers := 0
    for current := minValue; current <= maxValue; current++ {
        if isPrime[current] {
            sumPrimeNumbers += current
        }
    }
    return sumPrimeNumbers
}

func reverseDigits(value int) int {
    reversed := 0
    for value > 0 {
        digit := value % 10
        reversed = 10 * reversed + digit
        value /= 10
    }
    return reversed
}

func createSieveOfEratosthenes(maxValue int) []bool {
    isPrime := make([]bool, maxValue)
    for value := range isPrime {
        isPrime[value] = true
    }
    for value := range SMALLEST_PRIME {
        isPrime[value] = false
    }

    upperLimit := int(math.Sqrt(float64(maxValue)))
    for value := SMALLEST_PRIME; value <= upperLimit; value++ {
        if !isPrime[value] {
            continue
        }
        var current = value * value
        for current < maxValue {
            isPrime[current] = false
            current += value
        }
    }
    return isPrime
}
