#include "Example2.h"

#include <iostream>
#include <iomanip>
#include <string>
#include <vector>
#include <algorithm>
#include <numeric>
#include <cmath>

Example2::Example2()
{
}

Example2::~Example2()
{
}

void Example2::Run()
{
    const double PI = 3.14159;
    const double E = 2.71828;
    const double SQRT2 = 1.41421;

    std::cout << std::setw(10) << std::left << "Radius"
        << std::setw(10) << std::left << "Area"
        << std::setw(20) << std::left << "Circumference" 
        << std::setw(10) << std::left << "e^R"
        << std::setw(10) << std::left << "ln(R)"
        << std::endl;

    for (double radius{ 0.2 }; radius <= 3.0; radius += 0.2)
    {
        std::cout << std::setw(10) << std::left << radius 
            << std::setw(10) << std::left << PI * radius * radius 
            << std::setw(20) << std::left << 2 * PI * radius 
            << std::setw(10) << std::left << std::exp(radius)
            << std::setw(10) << std::left << std::log(radius)
            << std::endl;
    }

    // factorial using algorithms
    auto factorial = [](int n) {
        std::vector<int> v(n);
        std::iota(v.begin(), v.end(), 1);
        return std::accumulate(v.begin(), v.end(), 1, std::multiplies<int>());
    };

    std::cout << "Factorial of 5 is " << factorial(5) << std::endl;

    std::vector<long> primes;

    // find n prime numbers using algorithms
    auto fillPrimes = [&primes](int n) {
        primes.clear();
        primes.push_back(2);
        
        for (long i{ 3 }; primes.size() < n; i += 2)
        {
            if (std::all_of(primes.begin(), primes.end(), [i](long p) { return i % p != 0; }))
            {
                primes.push_back(i);
            }
        }
    };

    fillPrimes(100);

    std::cout << "First 100 prime numbers are: ";
    for (auto p : primes)
    {
        std::cout << p << " ";
    }
    std::cout << std::endl;
}
