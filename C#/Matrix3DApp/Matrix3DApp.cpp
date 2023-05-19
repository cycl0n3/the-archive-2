#include <iostream>

#include "Matrix3D.h"

int main()
{
    Row3D row1(1, 2, 3);
    Row3D row2(4, 5, 6);
    Row3D row3(7, 8, 9);

    Matrix3D matrix1(row1, row2, row3);

    std::cout << "matrix1 = " << std::endl << matrix1 << std::endl;
    std::cout << "matrix1 inverse = " << std::endl << matrix1.inverse() << std::endl;
    std::cout << "matrix1 transpose = " << std::endl << matrix1.transpose() << std::endl;

    return 0;
}
