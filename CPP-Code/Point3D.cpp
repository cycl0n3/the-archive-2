#include "Point3D.h"

#include <math.h>
#include <sstream>
#include <iostream>

Point3D::Point3D() : x(0), y(0), z(0)
{
}

Point3D::Point3D(double x, double y, double z) : x(x), y(y), z(z)
{
}

Point3D::Point3D(const Point3D& other) : x(other.x), y(other.y), z(other.z)
{
}

Point3D::~Point3D()
{
}

Point3D& Point3D::operator=(const Point3D& other)
{
    if (this != &other)
    {
        x = other.x;
        y = other.y;
        z = other.z;
    }
    return *this;
}

Point3D Point3D::operator+(const Point3D& other) const
{
    return Point3D(x + other.x, y + other.y, z + other.z);
}

Point3D Point3D::operator-(const Point3D& other) const
{
    return Point3D(x - other.x, y - other.y, z - other.z);
}

Point3D Point3D::operator*(double scalar) const
{
    return Point3D(x * scalar, y * scalar, z * scalar);
}

Point3D Point3D::operator/(double scalar) const
{
    return Point3D(x / scalar, y / scalar, z / scalar);
}

double Point3D::getX() const
{
    return x;
}

double Point3D::getY() const
{
    return y;
}

double Point3D::getZ() const
{
    return z;
}

double Point3D::distance(const Point3D& other) const
{
    return sqrt(pow(x - other.x, 2) + pow(y - other.y, 2) + pow(z - other.z, 2));
}

double Point3D::distance(const Point3D& p1, const Point3D& p2) const
{
    return p1.distance(p2);
}

bool Point3D::operator==(const Point3D& other) const
{
    return x == other.x && y == other.y && z == other.z;
}

bool Point3D::operator!=(const Point3D& other) const
{
    return !(*this == other);
}

std::string Point3D::toString() const
{
    std::ostringstream oss;
    oss << "(" << x << ", " << y << ", " << z << ")";
    return oss.str();
}

std::ostream& operator<<(std::ostream& os, const Point3D& p)
{
    os << p.toString();
    return os;
}