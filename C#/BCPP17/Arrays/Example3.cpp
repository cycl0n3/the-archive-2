#include "Example3.h"

#include <iostream>
#include <string>
#include <vector>
#include <functional>

typedef unsigned long long int int64;

Example3::Example3()
{
}

Example3::~Example3()
{
}

void Example3::Run()
{
    std::function<int(int64)> fibonacci;

    // memoized fibonacci function
    fibonacci = [&](int n) -> int64
    {
        static std::vector<int64> cache = { 0, 1 };
        if (n < cache.size())
        {
            return cache[n];
        }
        else
        {
            int64 result = fibonacci(n - 1) + fibonacci(n - 2);

            cache.push_back(result);
            
            return result;
        }
    };


    // first 100 fibonacci numbers
    std::vector<int64> results;
    for (int i = 0; i < 200; i++)
    {
        results.push_back(fibonacci(i));
    }

    // print the results
    for (int i = 0; i < results.size(); i++)
    {
        std::cout << results[i] << ", ";
    }
    std::cout << std::endl;
}