#include "Row3D.h"

Row3D::Row3D()
{
    x = 0;
    y = 0;
    z = 0;
}

Row3D::Row3D(int x, int y, int z)
{
    this->x = x;
    this->y = y;
    this->z = z;
}

Row3D::Row3D(const Row3D& row)
{
    x = row.x;
    y = row.y;
    z = row.z;
}

Row3D::~Row3D()
{
}

Row3D& Row3D::operator=(const Row3D& row)
{
    x = row.x;
    y = row.y;
    z = row.z;
    return *this;
}

Row3D Row3D::operator+(const Row3D& row)
{
    Row3D result;
    result.x = x + row.x;
    result.y = y + row.y;
    result.z = z + row.z;
    return result;
}

Row3D Row3D::operator-(const Row3D& row)
{
    Row3D result;
    result.x = x - row.x;
    result.y = y - row.y;
    result.z = z - row.z;
    return result;
}

Row3D Row3D::operator*(int scalar)
{
    Row3D result;
    result.x = x * scalar;
    result.y = y * scalar;
    result.z = z * scalar;
    return result;
}

Row3D Row3D::operator/(int scalar)
{
    Row3D result;
    result.x = x / scalar;
    result.y = y / scalar;
    result.z = z / scalar;
    return result;
}

Row3D& Row3D::operator+=(const Row3D& row)
{
    x += row.x;
    y += row.y;
    z += row.z;
    return *this;
}

Row3D& Row3D::operator-=(const Row3D& row)
{
    x -= row.x;
    y -= row.y;
    z -= row.z;
    return *this;
}

Row3D& Row3D::operator*=(int scalar)
{
    x *= scalar;
    y *= scalar;
    z *= scalar;
    return *this;
}

Row3D& Row3D::operator/=(int scalar)
{
    x /= scalar;
    y /= scalar;
    z /= scalar;
    return *this;
}

bool Row3D::operator==(const Row3D& row)
{
    return x == row.x && y == row.y && z == row.z;
}

bool Row3D::operator!=(const Row3D& row)
{
    return x != row.x || y != row.y || z != row.z;
}

double Row3D::operator*(const Row3D& row)
{
    return x * row.x + y * row.y + z * row.z;
}

int Row3D::getX() const
{
    return x;
}

int Row3D::getY() const
{
    return y;
}

int Row3D::getZ() const
{
    return z;
}

std::ostream& operator<<(std::ostream& os, const Row3D& row)
{
    os << "[" << row.x << ", " << row.y << ", " << row.z << "]";
    return os;
}
