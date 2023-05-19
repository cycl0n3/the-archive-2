#include "Matrix3D.h"

#include <iostream>

#include "Row3D.h"

Matrix3D::Matrix3D()
{
    row1 = Row3D();
    row2 = Row3D();
    row3 = Row3D();
}

Matrix3D::Matrix3D(const Row3D& row1, const Row3D& row2, const Row3D& row3)
{
    this->row1 = row1;
    this->row2 = row2;
    this->row3 = row3;
}

Matrix3D::Matrix3D(const Matrix3D& matrix)
{
    row1 = matrix.row1;
    row2 = matrix.row2;
    row3 = matrix.row3;
}

Matrix3D::~Matrix3D()
{
}

Matrix3D& Matrix3D::operator=(const Matrix3D& matrix)
{
    row1 = matrix.row1;
    row2 = matrix.row2;
    row3 = matrix.row3;
    return *this;
}

Row3D Matrix3D::getRow1() const
{
    return row1;
}

Row3D Matrix3D::getRow2() const
{
    return row2;
}

Row3D Matrix3D::getRow3() const
{
    return row3;
}

Matrix3D Matrix3D::operator+(const Matrix3D& matrix)
{
    Matrix3D result;
    result.row1 = row1 + matrix.row1;
    result.row2 = row2 + matrix.row2;
    result.row3 = row3 + matrix.row3;
    return result;
}

Matrix3D Matrix3D::operator-(const Matrix3D& matrix)
{
    Matrix3D result;
    result.row1 = row1 - matrix.row1;
    result.row2 = row2 - matrix.row2;
    result.row3 = row3 - matrix.row3;
    return result;
}

Matrix3D Matrix3D::operator*(int scalar)
{
    Matrix3D result;
    result.row1 = row1 * scalar;
    result.row2 = row2 * scalar;
    result.row3 = row3 * scalar;
    return result;
}

Matrix3D Matrix3D::operator/(int scalar)
{
    Matrix3D result;
    result.row1 = row1 / scalar;
    result.row2 = row2 / scalar;
    result.row3 = row3 / scalar;
    return result;
}

Matrix3D& Matrix3D::operator+=(const Matrix3D& matrix)
{
    row1 += matrix.row1;
    row2 += matrix.row2;
    row3 += matrix.row3;
    return *this;
}

Matrix3D& Matrix3D::operator-=(const Matrix3D& matrix)
{
    row1 -= matrix.row1;
    row2 -= matrix.row2;
    row3 -= matrix.row3;
    return *this;
}

Matrix3D& Matrix3D::operator*=(int scalar)
{
    row1 *= scalar;
    row2 *= scalar;
    row3 *= scalar;
    return *this;
}

Matrix3D& Matrix3D::operator/=(int scalar)
{
    row1 /= scalar;
    row2 /= scalar;
    row3 /= scalar;
    return *this;
}

// inverse function
Matrix3D Matrix3D::inverse() const
{
    Matrix3D result;
    result.row1 = Row3D(row2.getY() * row3.getZ() - row2.getZ() * row3.getY(), row1.getZ() * row3.getY() - row1.getY() * row3.getZ(), row1.getY() * row2.getZ() - row1.getZ() * row2.getY());
    result.row2 = Row3D(row2.getZ() * row3.getX() - row2.getX() * row3.getZ(), row1.getX() * row3.getZ() - row1.getZ() * row3.getX(), row1.getZ() * row2.getX() - row1.getX() * row2.getZ());
    result.row3 = Row3D(row2.getX() * row3.getY() - row2.getY() * row3.getX(), row1.getY() * row3.getX() - row1.getX() * row3.getY(), row1.getX() * row2.getY() - row1.getY() * row2.getX());
    return result;
}

// transpose function
Matrix3D Matrix3D::transpose() const
{
    Matrix3D result;
    result.row1 = Row3D(row1.getX(), row2.getX(), row3.getX());
    result.row2 = Row3D(row1.getY(), row2.getY(), row3.getY());
    result.row3 = Row3D(row1.getZ(), row2.getZ(), row3.getZ());
    return result;
}

// matrix multiplication function using getRow
Matrix3D Matrix3D::operator*(const Matrix3D& matrix)
{
    Matrix3D result;
    result.row1 = Row3D(row1 * matrix.getRow1(), row1 * matrix.getRow2(), row1 * matrix.getRow3());
    result.row2 = Row3D(row2 * matrix.getRow1(), row2 * matrix.getRow2(), row2 * matrix.getRow3());
    result.row3 = Row3D(row3 * matrix.getRow1(), row3 * matrix.getRow2(), row3 * matrix.getRow3());
    return result;
}

// division function using inverse
Matrix3D Matrix3D::operator/(const Matrix3D& matrix)
{
    Matrix3D result;
    result = *this * matrix.inverse();
    return result;
}

bool Matrix3D::operator==(const Matrix3D& matrix)
{
    return row1 == matrix.row1 && row2 == matrix.row2 && row3 == matrix.row3;
}

bool Matrix3D::operator!=(const Matrix3D& matrix)
{
    return row1 != matrix.row1 || row2 != matrix.row2 || row3 != matrix.row3;
}

std::ostream& operator<<(std::ostream& os, const Matrix3D& matrix)
{
    os << matrix.row1 << std::endl << matrix.row2 << std::endl << matrix.row3;
    return os;
}