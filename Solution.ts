
function sumOfPrimesInRange(inputValue: number): number {
    const reversedInputValue = reverseDigits(inputValue);
    const minValue = Math.min(inputValue, reversedInputValue);
    const maxValue = Math.max(inputValue, reversedInputValue);
    const isPrime = createSieveOfEratosthenes(maxValue + 1);

    let sumPrimeNumbers = 0;
    for (let current = minValue; current <= maxValue; ++current) {
        if (isPrime[current]) {
            sumPrimeNumbers += current;
        }
    }
    return sumPrimeNumbers;
};

function reverseDigits(value: number): number {
    let reversed = 0;
    while (value > 0) {
        const digit = value % 10;
        reversed = 10 * reversed + digit;
        value = Math.floor(value / 10);
    }
    return reversed;
}

function createSieveOfEratosthenes(maxValue: number): boolean[] {
    const SMALLEST_PRIME = 2;
    const isPrime: boolean[] = new Array(maxValue).fill(true);
    for (let value = 0; value < SMALLEST_PRIME; ++value) {
        isPrime[value] = false;
    }

    const upperLimit = Math.floor(Math.sqrt(maxValue));
    for (let value = SMALLEST_PRIME; value <= upperLimit; ++value) {
        if (!isPrime[value]) {
            continue;
        }
        let current = value * value;
        while (current < maxValue) {
            isPrime[current] = false;
            current += value;
        }
    }
    return isPrime;
}
