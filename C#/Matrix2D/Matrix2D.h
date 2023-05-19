#pragma once

#include "Row2.h"

#include <iostream>

class Matrix2D
{
public:
    Matrix2D();
    Matrix2D(const Row2& row1, const Row2& row2);
    Matrix2D(const Matrix2D& other);
    ~Matrix2D();

    Row2& operator[](int index);
    const Row2& operator[](int index) const;
    
    Matrix2D& operator=(const Matrix2D& other);
    Matrix2D& operator+=(const Matrix2D& other);
    Matrix2D& operator-=(const Matrix2D& other);
    Matrix2D operator+(const Matrix2D& other) const;
    Matrix2D operator-(const Matrix2D& other) const;

    Matrix2D operator*(float scalar) const;
    Matrix2D operator*(const Matrix2D& other) const;

    Matrix2D inverse() const;
    
    bool operator==(const Matrix2D& other) const;
    bool operator!=(const Matrix2D& other) const;

    friend std::ostream& operator<<(std::ostream& os, const Matrix2D& matrix);
private:
    Row2 rows[2];
};
