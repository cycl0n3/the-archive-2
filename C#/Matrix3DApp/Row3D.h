#pragma once

#include <iostream>

class Row3D
{
public:
    Row3D();
    Row3D(int x, int y, int z);
    Row3D(const Row3D& row);
    ~Row3D();

    Row3D& operator=(const Row3D& row);
    Row3D operator+(const Row3D& row);
    Row3D operator-(const Row3D& row);

    Row3D operator*(int scalar);
    Row3D operator/(int scalar);

    Row3D& operator+=(const Row3D& row);
    Row3D& operator-=(const Row3D& row);

    Row3D& operator*=(int scalar);
    Row3D& operator/=(int scalar);

    bool operator==(const Row3D& row);
    bool operator!=(const Row3D& row);

    double operator*(const Row3D& row);

    int getX() const;
    int getY() const;
    int getZ() const;

    friend std::ostream& operator<<(std::ostream& os, const Row3D& row);
private:
    int x;
    int y;
    int z;
};
