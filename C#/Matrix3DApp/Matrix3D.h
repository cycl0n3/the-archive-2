#pragma once

#include <iostream>

#include "Row3D.h"

class Matrix3D
{
public:
    Matrix3D();
    Matrix3D(const Row3D& row1, const Row3D& row2, const Row3D& row3);
    Matrix3D(const Matrix3D& matrix);
    ~Matrix3D();

    Matrix3D& operator=(const Matrix3D& matrix);
    Matrix3D operator+(const Matrix3D& matrix);
    Matrix3D operator-(const Matrix3D& matrix);

    Matrix3D operator*(int scalar);
    Matrix3D operator/(int scalar);
    
    Matrix3D& operator+=(const Matrix3D& matrix);
    Matrix3D& operator-=(const Matrix3D& matrix);
    
    Matrix3D& operator*=(int scalar);
    Matrix3D& operator/=(int scalar);

    Matrix3D operator*(const Matrix3D& matrix);
    Matrix3D operator/(const Matrix3D& matrix);

    Matrix3D inverse() const;
    Matrix3D transpose() const;
    
    bool operator==(const Matrix3D& matrix);
    bool operator!=(const Matrix3D& matrix);
    
    Row3D getRow1() const;
    Row3D getRow2() const;
    Row3D getRow3() const;
    
    friend std::ostream& operator<<(std::ostream& os, const Matrix3D& matrix);
private:
    Row3D row1;
    Row3D row2;
    Row3D row3;
};
