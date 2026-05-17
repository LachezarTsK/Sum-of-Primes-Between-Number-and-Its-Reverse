
using System;

public class Solution
{
    private static readonly int SMALLEST_PRIME = 2;

    public int SumOfPrimesInRange(int inputValue)
    {
        int reversedInputValue = ReverseDigits(inputValue);
        int minValue = Math.Min(inputValue, reversedInputValue);
        int maxValue = Math.Max(inputValue, reversedInputValue);
        bool[] isPrime = CreateSieveOfEratosthenes(maxValue + 1);

        int sumPrimeNumbers = 0;
        for (int current = minValue; current <= maxValue; ++current)
        {
            if (isPrime[current])
            {
                sumPrimeNumbers += current;
            }
        }
        return sumPrimeNumbers;
    }

    private int ReverseDigits(int value)
    {
        int reversed = 0;
        while (value > 0)
        {
            int digit = value % 10;
            reversed = 10 * reversed + digit;
            value /= 10;
        }
        return reversed;
    }

    private bool[] CreateSieveOfEratosthenes(int maxValue)
    {
        bool[] isPrime = new bool[maxValue];
        Array.Fill(isPrime, true);
        for (int value = 0; value < SMALLEST_PRIME; ++value)
        {
            isPrime[value] = false;
        }

        int upperLimit = (int)Math.Sqrt(maxValue);
        for (int value = SMALLEST_PRIME; value <= upperLimit; ++value)
        {
            if (!isPrime[value])
            {
                continue;
            }
            int current = value * value;
            while (current < maxValue)
            {
                isPrime[current] = false;
                current += value;
            }
        }
        return isPrime;
    }
}
