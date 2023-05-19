#include "Example1.h"

#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <algorithm>
#include <numeric>

Example1::Example1()
{
}

Example1::~Example1()
{
}

void Example1::Run()
{
    auto vectorToString = [](const std::vector<double>& array) -> std::string {
        std::stringstream ss;
        ss << "[";
        for (int i = 0; i < array.size(); ++i)
        {
            ss << array[i];
            if (i < array.size() - 1)
            {
                ss << ", ";
            }
        }
        ss << "]";
        return ss.str();
    };

    std::vector<double> rainfallVector{ 1.1, 2.8, 3.4, 3.7, 2.1, 2.3, 1.8, 0.0, 0.3, 0.9, 0.7, 0.5 };
    double averageRainfall = std::accumulate(rainfallVector.begin(), rainfallVector.end(), 0.0) / rainfallVector.size();

    std::cout << vectorToString(rainfallVector) << std::endl;
    std::cout << "Average rainfall: " << averageRainfall << std::endl;
}