#include "Example4.h"

#include <iostream>
#include <string>
#include <iomanip>


Example4::Example4()
{
}

Example4::~Example4()
{
}

void Example4::Run()
{
    // print two dimensional multiplication table
    int table[10][10];

    for (int row = 0; row < 10; row++)
    {
        for (int column = 0; column < 10; column++)
        {
            table[row][column] = (row + 1) * (column + 1);
        }
    }

    for (int row = 0; row < 10; row++)
    {
        for (int column = 0; column < 10; column++)
        {
            std::cout << std::setw(4) << table[row][column];
        }
        std::cout << std::endl;
    }

    std::cout << std::endl;
}