#pragma once

#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <functional>
#include <tuple>
#include <iomanip>
#include <fstream>

void runExample3()
{
    auto dot = [](auto x, auto y) -> double {
        return std::inner_product(x.begin(), x.end(), y.begin(), 0.0);
    };

    auto cross = [](auto x, auto y) {
        return std::vector<double>{
            x[1] * y[2] - x[2] * y[1],
            x[2] * y[0] - x[0] * y[2],
            x[0] * y[1] - x[1] * y[0]
        };
    };

    auto norm = [&dot](auto x) {
        return std::sqrt(dot(x, x));
    };

    auto angle = [&dot, &norm](auto x, auto y) {
        return std::acos(dot(x, y) / (norm(x) * norm(y)));
    };

    auto print = [](auto x) {
        std::cout << std::fixed << std::setprecision(3);
        for (auto i : x)
            std::cout << i << " ";
        std::cout << std::endl;
    };

    auto vec1 = std::vector<double>{ 1, 2, 3 };
    auto vec2 = std::vector<double>{ 4, 5, 6 };

    std::cout << "vec1: ";
    print(vec1);

    std::cout << "vec2: ";
    print(vec2);

    std::cout << "dot(vec1, vec2): " << dot(vec1, vec2) << std::endl;
    
    std::cout << "cross(vec1, vec2): ";
    print(cross(vec1, vec2));

    std::cout << "norm(vec1): " << norm(vec1) << std::endl;

    std::cout << "angle(vec1, vec2): " << angle(vec1, vec2) << std::endl;
    std::cout << "angle(vec1, vec2) * 180 / pi: " << angle(vec1, vec2) * 180 / 3.14159265358979323846 << std::endl;
}