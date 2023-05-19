#include <iostream>

#include "Matrix2D.h"

int main()
{
    Matrix2D matrix1(Row2(1.0f, 2.0f), Row2(3.0f, 4.0f));
    Matrix2D matrix2(Row2(5.0f, 6.0f), Row2(7.0f, 8.0f));

    std::cout << "matrix1:\n" << matrix1 << "\n";
    std::cout << "matrix2:\n" << matrix2 << "\n";

    std::cout << "matrix1 + matrix2:\n" << matrix1 + matrix2 << "\n";
    std::cout << "matrix1 - matrix2:\n" << matrix1 - matrix2 << "\n";

    std::cout << "matrix1 * 2.0f:\n" << matrix1 * 2.0f << "\n";
    std::cout << "matrix2 * 3.0f:\n" << matrix2 * 3.0f << "\n";

    std::cout << "matrix1 == matrix2: " << (matrix1 == matrix2) << "\n";
    std::cout << "matrix1 != matrix2: " << (matrix1 != matrix2) << "\n";

    std::cout << "matrix1 * matrix2:\n" << matrix1 * matrix2 << "\n";

    std::cout << "matrix1.inverse():\n" << matrix1.inverse() << "\n";
    std::cout << "matrix2.inverse():\n" << matrix2.inverse() << "\n";

    return 0;
}
