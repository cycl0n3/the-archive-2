#include "Matrix2D.h"

#include <iostream>

Matrix2D::Matrix2D()
    : rows{ Row2(), Row2() }
{
}

Matrix2D::Matrix2D(const Row2& row1, const Row2& row2)
    : rows{ row1, row2 }
{
}

Matrix2D::Matrix2D(const Matrix2D& other)
    : rows{ other.rows[0], other.rows[1] }
{
}

Matrix2D::~Matrix2D()
{
}

Row2& Matrix2D::operator[](int index)
{
    if (index == 0)
    {
        return rows[0];
    }
    else if (index == 1)
    {
        return rows[1];
    }
    else
    {
        std::cout << "Error: index out of range\n";
        return rows[0];
    }
}

const Row2& Matrix2D::operator[](int index) const
{
    if (index == 0)
    {
        return rows[0];
    }
    else if (index == 1)
    {
        return rows[1];
    }
    else
    {
        std::cout << "Error: index out of range\n";
        return rows[0];
    }
}

Matrix2D& Matrix2D::operator=(const Matrix2D& other)
{
    rows[0] = other.rows[0];
    rows[1] = other.rows[1];
    return *this;
}

Matrix2D& Matrix2D::operator+=(const Matrix2D& other)
{
    rows[0] += other.rows[0];
    rows[1] += other.rows[1];
    return *this;
}

Matrix2D& Matrix2D::operator-=(const Matrix2D& other)
{
    rows[0] -= other.rows[0];
    rows[1] -= other.rows[1];
    return *this;
}

Matrix2D Matrix2D::operator+(const Matrix2D& other) const
{
    return Matrix2D(rows[0] + other.rows[0], rows[1] + other.rows[1]);
}

Matrix2D Matrix2D::operator-(const Matrix2D& other) const
{
    return Matrix2D(rows[0] - other.rows[0], rows[1] - other.rows[1]);
}

Matrix2D Matrix2D::operator*(float scalar) const
{
    return Matrix2D(rows[0] * scalar, rows[1] * scalar);
}

Matrix2D Matrix2D::operator*(const Matrix2D& other) const
{
    return Matrix2D(
        Row2(rows[0][0] * other.rows[0][0] + rows[0][1] * other.rows[1][0],
                       rows[0][0] * other.rows[0][1] + rows[0][1] * other.rows[1][1]),
        Row2(rows[1][0] * other.rows[0][0] + rows[1][1] * other.rows[1][0],
                       rows[1][0] * other.rows[0][1] + rows[1][1] * other.rows[1][1]));
}

Matrix2D Matrix2D::inverse() const
{
    float determinant = rows[0][0] * rows[1][1] - rows[0][1] * rows[1][0];

    if (determinant == 0.0f)
    {
        std::cout << "Error: determinant is 0\n";
        return Matrix2D();
    }
    else
    {
        return Matrix2D(
            Row2(rows[1][1] / determinant, -rows[0][1] / determinant),
            Row2(-rows[1][0] / determinant, rows[0][0] / determinant));
    }
}

bool Matrix2D::operator==(const Matrix2D& other) const
{
    return rows[0] == other.rows[0] && rows[1] == other.rows[1];
}

bool Matrix2D::operator!=(const Matrix2D& other) const
{
    return rows[0] != other.rows[0] || rows[1] != other.rows[1];
}

std::ostream& operator<<(std::ostream& os, const Matrix2D& matrix)
{
    os << matrix[0] << '\n' << matrix[1];
    return os;
}

